
package com.appd.RestServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.appd.pojo.Node;
import com.appd.pojo.Person;
import com.appd.service.PersonService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.StringReader;
import java.lang.reflect.Type;


/** Example resource class hosted at the URI path "/myresource"
 */
@Path("/person")
public class MyResource {
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET 
    @Path("/json")
    @Produces("application/json")
    public List<Person> getPersons() {
    	//System.out.println("Inside person api");
    	List<Person> p = new PersonService().getPersonList();
    	
    	for(int i=0;i<4;i++) {
			try {
				if(i == 2) {
					int a = i/0;
				}
				System.out.println(i);
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("exception occured");
			}
			System.out.println("starting for loop again");
		}
    	
    	//for (Person person : p) {
		//	System.out.println(person);
		//}
        return p;
    }
    
	/*
	 * @POST
	 * 
	 * @Path("/recieveFromController")
	 * 
	 * @Consumes("application/json")
	 * 
	 * @Produces("text/plain") public String recieveFromController(String msg) {
	 * System.out.println(msg); return msg +"recieved Successfully"; }
	 */
    
    
    @POST
    @Path("/recieveFromController")
       public String recieveFromController(String msg) {
    	
    	
    	System.out.println(msg);
    	return msg +"recieved Successfully"; }
	
    	
    @POST
    @Path("/recieveFromController/healthRule")
       public String recieveFromControllerHelathRule(String msg) throws IOException {
 
    	System.out.println(msg);
    	String nodeId = getNodeId(msg);
    	
    	if(nodeId != null)
    	return markNodeHistorical(nodeId);
    	
    	return "No node present";
    		
    }
			
	private String markNodeHistorical(String nodeId) throws IOException {
		String baseUrl = "http://localhost:8090/controller/rest/mark-nodes-historical?application-component-node-ids="+nodeId;
		URL markNodeHistorical = new URL(baseUrl);
		String name = "admin@customer1";
		String password = "admin";
		String authString = name + ":" + password;
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		String authStringEnc = new String(authEncBytes);
		System.out.println("Base64 encoded auth string: " + authStringEnc);
		URLConnection urlConnection = markNodeHistorical.openConnection();
		HttpURLConnection urlCon = (HttpURLConnection) urlConnection;
		urlCon.setRequestMethod("POST");
		urlCon.setRequestProperty("Authorization", "Basic " + authStringEnc);
		InputStream is = urlConnection.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		int numCharsRead;
		char[] charArray = new char[1024];
		StringBuffer sb = new StringBuffer();
		while ((numCharsRead = isr.read(charArray)) > 0) {
			sb.append(charArray, 0, numCharsRead);
		}
		String result = sb.toString();
		System.out.println("After node historical API"+result);
		return result;
	}

	private String getNodeId(String msg) throws IOException {
		
		  
	      String[] splitSummary = msg.split(" ");
	      
	      String nodeName = "";
	      	if(splitSummary.length >= 8)
	      		nodeName = splitSummary[8];
	      	
	    		System.out.println("NodeName : "+nodeName);
	    	
	        		if(nodeName.equals("")) {
	        			return null;
	        		}
	        			String baseUrl = "http://localhost:8090/controller/rest/applications/20/nodes/"+nodeName+"?output=json";
		        		URL getNodeInfoUrl = new URL(baseUrl);
		        		String name = "admin@customer1";
		    			String password = "admin";
		    			String authString = name + ":" + password;
		    			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		    			String authStringEnc = new String(authEncBytes);
		    			System.out.println("Base64 encoded auth string: " + authStringEnc);
		    			URLConnection urlConnection = getNodeInfoUrl.openConnection();
		    			urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
		    			InputStream is = urlConnection.getInputStream();
		    			InputStreamReader isr = new InputStreamReader(is);
		    			int numCharsRead;
		    			char[] charArray = new char[1024];
		    			StringBuffer sb = new StringBuffer();
		    			while ((numCharsRead = isr.read(charArray)) > 0) {
		    				sb.append(charArray, 0, numCharsRead);
		    			}
		    			String result = sb.toString();
		    			System.out.println(result);
					  Gson gson = new Gson();
					  
					  Type collectionType = new TypeToken<List<Node>>(){}.getType();
					  List<Node> nodes  = gson.fromJson( result , collectionType); 
					  String nodeId = "";
					  for (Node node : nodes) {
						  System.out.println(node.getName()+" "+node.getId());
						  nodeId = node.getId();
					
					  }
					  return nodeId;
	        		
				
}
 
    
    @GET
    @Path("/xml")
    @Produces(MediaType.APPLICATION_XML)
    public List<Person> getPersonsInXml() {
//    	/System.out.println("Inside person api");
    	List<Person> p = new PersonService().getPersonList();
    	
    	
    	
    	//for (Person person : p) {
		//	System.out.println(person);
		//}
        return p;
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Map<String, Object> getPersonsInXml(@PathParam(value = "id") int id) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	//System.out.println("Inside person api");
    	Person p = new PersonService().getPerson(id);
    	if (p != null)
    	{
        	 map.put("result", p);
    	}
    	else
    	map.put("result","Not Found");
    	
    	return map;
    }
    
    @GET 
    @Path("/webapp")
    @Produces("application/json")
	public void execHttp() {
    	System.out.println("hii");
		try {
			//Thread.sleep(5000);
			callHttp("https://www.google.com");
		} catch (Exception e) {
			System.out.println("catch");
		}
	}

    
    
    
    
	private void callHttp(String url) throws Exception {
		try (CloseableHttpClient httpClient = HttpClients.createDefault();) {
			// HTTPリクエストの設定を行います。
			// ここでは例としてタイムアウトの時間を設定します。
			RequestConfig config = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();

			// HTTPのGETリクエストを構築します。
			// ここでは例としてHTTPヘッダ(User-Agent)と設定をセットします。
			HttpGet httpGet = new HttpGet(url);
			httpGet.addHeader("User-Agent",
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");
			httpGet.setConfig(config);

			// HTTPリクエストを実行します。 HTTPステータスが200の場合は取得したHTMLを表示します。
			try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet);) {
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					System.out.println(EntityUtils.toString(httpResponse.getEntity()));
				} else {
					System.out.println("Error Code:" + httpResponse.getStatusLine().getStatusCode() + " returned");
					raiseException(httpResponse);
				}
			} catch (Exception exception) {
				// exception.printStackTrace();
				System.out.println(exception.getMessage());
				throw exception;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}
	private void raiseException(CloseableHttpResponse httpResponse) throws Exception {
		throw new Exception("Error Code:" + httpResponse.getStatusLine().getStatusCode() + " returned");
	}
    
}
