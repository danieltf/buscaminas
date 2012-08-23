package com.neblire.games.buscaminas.websocket;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.neblire.games.buscaminas.activities.GameActivity;

public class MyWebSocket {

	public GameActivity activity;
	WebSocketClient client;
	
	
	public MyWebSocket(GameActivity activity){
		this.activity = activity;
//		MyWebSocket.this.activity.runOnUiThread(new Runnable() {
//    		public void run() {
////    			MyWebSocket.this.activity.servidor.setText("Esperando a conectar....");
//    			System.out.println("CONECTANDO.......");
//    		}
//    	});
		
		
		client = new WebSocketClient(URI.create("wss://192.168.1.34:9000/hola")) {

			@Override
			public void onClose(int arg0, String arg1, boolean arg2) {
				System.out.println("Conexion cerrada");
			}

			@Override
			public void onError(Exception arg0) {
				arg0.printStackTrace();
			}

			@Override
			public void onMessage(String arg0) {
				System.out.println("Mensaje recibido: " + arg0);
			}

			@Override
			public void onOpen(ServerHandshake arg0) {
				System.out.println("Conexion abierta");
//				MyWebSocket.this.activity.runOnUiThread(new Runnable() {
//		    		public void run() {
//		    			System.out.println("CONECTÓ !!!!!!!!!!!!!");
////		    			MyWebSocket.this.activity.servidor.setText("Hemos conectado!!!");
//		    		}
//		    	});
			}
		    
		};
	}
	
	public void enviar(String msg){
		client.send(msg);
	}
	
	public void crear(){
		client.connect();
	}
	
	
}
