package br.luiztoni.messaging;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.MessageProducer;
import jakarta.jms.Queue;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import jakarta.jms.Topic;

@Stateless
public class EventProducer {

	private static final Logger LOGGER = Logger.getLogger(EventProducer.class.getName());

	@Resource(lookup = "jms/cf")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "jms/q")
	private Queue queue;

	@Resource(lookup = "jms/t")
	private Topic topic;
	
	@Inject
	@JMSConnectionFactory("jms/cf")
	private JMSContext context;

	public void sendMessage(String message) throws Exception {
		context.createProducer().send(queue, message);
		System.out.println("Message enqueued.");
		LOGGER.log(Level.INFO, "Send message in sendMessage method.");
	}

	public void sendToQueue(final String message) {
		try (Connection connection = connectionFactory.createConnection();
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				MessageProducer producer = session.createProducer(queue)) {
			System.out.println("Sending a new message to queue");
			TextMessage textMessage = session.createTextMessage();
			textMessage.setText(message);
			producer.send(textMessage);
		} catch (JMSException e) {
			LOGGER.log(Level.SEVERE, "Exception occur in sendToQueue:", e);
		}
	}

	public void sendToTopic(final String message) {
		try (Connection connection = connectionFactory.createConnection();
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				MessageProducer producer = session.createProducer(topic)) {
			System.out.println("Sending a new message to topic");
			TextMessage textMessage = session.createTextMessage();
			textMessage.setText(message);
			producer.send(textMessage);
		} catch (JMSException e) {
			LOGGER.log(Level.SEVERE, "Exception occur in sendToTopic:", e);
		}
	}
}
