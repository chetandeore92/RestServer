package com.app.customEcxeption;

@SuppressWarnings("serial")
public class CustomException extends Exception
{
public CustomException(String msg) {
super(msg);
System.out.println("after custom exception");
}
}
