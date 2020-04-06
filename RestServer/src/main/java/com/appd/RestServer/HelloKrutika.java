package com.appd.RestServer;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import java.net.URL;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;





@Path("/krutika")
public class HelloKrutika {

	private String productList;
	@GET
	@Path("/metric")
    @Produces("application/json")
	public String metricUpload(@QueryParam("ip") String ip) throws IOException {
		System.out.println("Ip "+ip);
		execHttp(ip);
		
		
		
		
		return "http post request done";
		
	}
	
	
	
	
	public void execHttp(String ip) throws IOException {
		System.out.println("sending http post req");
		String baseUrl = "http://"+ip+":8293/api/v1/metrics";
		System.out.println("Base url : "+baseUrl);
		String payload="[{\"metricName\": \"Custom Metrics|Test2|MetricFromRESTClient1\", \"aggregatorType\": \"AVERAGE\",\"value\": 4}]";
		URL url = new URL(baseUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        writer.write(payload);
        writer.close();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer jsonString = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
                jsonString.append(line);
        }
        br.close();
        connection.disconnect();
        
        //return jsonString.toString();
	}
}
