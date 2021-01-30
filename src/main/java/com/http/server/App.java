package com.http.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App {
	private static Logger logger = Logger.getLogger(App.class.getName());
	public static void main(String[] args) throws IOException {
		//tomcat, was, jboss server, glassfishserver, 
		ServerSocket serverSocket = new ServerSocket(8080);
		try {
			logger.info("Listening for connection on port 8080 ....");
			while (true) {
				Socket socket = null;
				try {
					socket = serverSocket.accept();
					logger.info("accept");
					new ServerThread(socket).start();
				} catch (Exception e) {
					socket.close();
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			serverSocket.close();
			logger.info(e.getMessage());
			e.printStackTrace();
		}

	}
}
