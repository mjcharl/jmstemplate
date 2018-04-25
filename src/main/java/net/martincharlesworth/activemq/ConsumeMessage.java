package net.martincharlesworth.activemq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Hello world!
 */
public class ConsumeMessage {

    private static String queueuURL = "tcp://localhost:61616";

    public static void main(String[] args) throws Exception {
        ConsumeMessage consumeMessage = new ConsumeMessage();
        consumeMessage.consume();
    }

    public void consume() {
        try {

            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(queueuURL);

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // connection.setExceptionListener(this);

            // Create a Session
            Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue("TEST.FOO");

            // Create a MessageConsumer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(destination,
                    "JMSMessageID = 'ID:Martins-MacBook-Pro.local-57768-1448535991963-1:1:1:1:1'");

            // Wait for a message
            Message message = consumer.receive(1000);
            if (message != null) {
                message.acknowledge();
            }

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("Received: " + text);
            } else {
                System.out.println("Received: " + message);
            }

            consumer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }
}
