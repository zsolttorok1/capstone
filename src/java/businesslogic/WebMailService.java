package businesslogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Webmail service to handle all the generating and sending logic.
 * 
 */
public class WebMailService {
    
    /**
     * Preparing and generating the webmail template from the provided information.
     * @param to To whom to send the mail.
     * @param subject the subject of the mail.
     * @param template the template to generate the mail body from.
     * @param contents the content variable values in the mail.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws MessagingException
     * @throws NamingException 
     */
    public static void sendMail(String to, String subject, String template, HashMap<String, String> contents) throws FileNotFoundException, IOException, MessagingException, NamingException {
        BufferedReader br = new BufferedReader(new FileReader(new File(template)));
        
        StringBuilder body = new StringBuilder();
        String line = br.readLine();
        while(line != null) {
            body.append(line);
            line = br.readLine();
        }
        
        String bodyString = body.toString();
        
        for(String key : contents.keySet()) {
            bodyString = bodyString.replace("{{" + key + "}}", contents.get(key));
        }
        
        sendMail(to, subject, bodyString, true);
    }
    
    /**
     * Sending the generated mail through the defined mail server. 
     * @param to the receiver of the mail.
     * @param subject the subject of the mail.
     * @param body the body of the mail.
     * @param bodyIsHTML does the body contain HTML elements or not.
     * @throws MessagingException
     * @throws NamingException 
     */
    public static void sendMail(String to, String subject, String body, boolean bodyIsHTML) throws MessagingException, NamingException {
        Context env = (Context)new InitialContext().lookup("java:comp/env");
        String username = (String)env.lookup("webmail-username");
        String password = (String)env.lookup("webmail-password");
        
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.port", 465);
        props.put("mail.smtps.auth", "true");
        props.put("mai.smtps.quitwait", "false");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        
        // create a message
        Message message = new MimeMessage(session);
        message.setSubject(subject);
        if (bodyIsHTML) {
            message.setContent(body, "text/html");
        } else {
            message.setText(body);
        }
        
        // address the message
        Address fromAddress = new InternetAddress("satomi.software@gmail.com");
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);
        
        // send the message
        Transport transport = session.getTransport();
        transport.connect(username, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
