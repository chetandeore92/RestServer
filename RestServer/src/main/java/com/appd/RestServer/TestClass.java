package com.appd.RestServer;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.app.customEcxeption.CustomException;
import com.appd.logger.CustomLogger;

@Path("/log4jexception_testing")
public class TestClass {

	@GET
	@Path("/test")
	public String log4jerrorCapture()
	{
		int i = 0;
		try {
			i = 100/i;
		} catch (Exception e) {
			CustomLogger cl = new CustomLogger();
			cl.error("error occured in "+this.getClass().getName()+" this is only logging error");
		}
		
		return "log4jerrorCapture sucessfull";
	}
	@GET
	@Path("/test/exception")
	public String log4jerrorCaptureAndThrownException()
	{
		int i = 0;
		try {
			i = 100/i;
		} catch (Exception e) {
			CustomLogger cl = new CustomLogger();
			cl.error("error occured in "+this.getClass().getName()+" This is looging error plus throwing an exception",new ArithmeticException(e.getMessage()));
		}
		
		return "log4jerrorCaptureandThrown sucessfull";
	}
	
	@GET
	@Path("/test/exception/arithmatic1")
	public String CaptureAndThrownException()
	{
		int i = 0;
		try {
			i = 100/i;
		} catch (Exception e) {
			CustomLogger cl = new CustomLogger();
			cl.error("error occured in "+this.getClass().getName()+" This is looging error plus throwing an exception",new ArithmeticException("Hello this is chetan"));
		}
		
		return "log4jerrorCaptureandThrown sucessfull";
	}
	
	@GET
	@Path("/test/Custom_exception")
	public String testCustomException()
	{
		int i = 0;
		try {
			i = 100/i;
		} catch (Exception e) {
			CustomLogger cl = new CustomLogger();
			cl.error("error occured in "+this.getClass().getName()+" This is custom exception",new CustomException("This is custom exception msg"));
		}
		
		return "custom exception thrown sucessfull";
	}
	
	@POST
	@Path("/http/template")
	@Consumes("application/json")
	public String httpTemplate(String name,String date) {
		return name+" "+date;
		
	}
		
	
	
	
}
