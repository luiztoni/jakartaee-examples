package br.luiztoni.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class ChatSocket {
	private static Set<Session> sessions;

	@OnOpen
	public void onOpen(Session session) {
		if (sessions == null) {
			sessions = new HashSet<Session>();
		}
		sessions.add(session);
	}

	@OnMessage
	public void onMessage(String message) throws IOException {
		System.out.println("Message received: " + message);
		System.out.println("sessions "+sessions.size());
		for (Session session : sessions) {
			System.out.println("Send");
			session.getBasicRemote().sendText(message.toUpperCase());
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	@OnClose
	public void onClose(CloseReason reason, Session session) {
		sessions.remove(session);
	}

	@SuppressWarnings("unlikely-arg-type")
	@OnError
	public void onError(Session session, Throwable throwable) {
		sessions.remove(session);
	}
}
