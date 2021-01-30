package com.http.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class ServerThread extends Thread {
	private static Logger logger = Logger.getLogger(ServerThread.class.getName());
    private Socket socket;
 
    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    public void run() {
    	String standardResponse = "HTTP/1.1 200 OK\r\n\r\n";
    	try{
    		BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    		String method = null ;
    		String input;
    		while ((input = inputStream.readLine()) != null) {
    		   if(input.contains("GET")){
    			   method ="GET";
    			  break;
    		   }else if( input.contains("POST")){
    			   method ="POST";
    			   break;
    		   }
    		}
    		 
    		OutputStream outputStream = socket.getOutputStream();
    		String response = standardResponse
    				+ "<html><head><title>Simple HTPP Server</title></head><body><h1>"+method+" : This page is served uing my simple java http server.</h1></body></html>";
    		outputStream.write(response.getBytes("UTF-8"));
    		socket.close();
    	}catch(Exception e){
    		e.printStackTrace();
		} 
    	
    }
}
