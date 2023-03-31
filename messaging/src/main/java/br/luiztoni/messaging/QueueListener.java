package br.luiztoni.messaging;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.JMSRuntimeException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

@MessageDriven(name = "QueueListener")
public class QueueListener implements MessageListener {
	
	private static final Logger LOGGER = Logger.getLogger(QueueListener.class.getName());


	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				System.out.println("Content in queue: "+textMessage.getText());
				LOGGER.log(Level.INFO, "New TextMessage: ",  message.getBody(String.class));
			} else {
				LOGGER.log(Level.WARNING, "Message type unknown");
			}
		} catch (JMSException | JMSRuntimeException e) {
			LOGGER.log(Level.SEVERE, "Exception occur in onMessage:", e);
		}
	}

}