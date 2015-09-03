/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.util;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.io.FileInputStream;

/**
 *
 * @author hl.murcia222
 */
public class MailManager {

    
    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;
    static String adminEmail="hl.murcia222@uniandes.edu.co";
    

    public static void generateAndSendEmail(String message) {

        try {
            
            //loadProperties();

            //Step1		
            System.out.println("\n 1st ===> setup Mail Server Properties..");
            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");
            System.out.println("Mail Server Properties have been setup successfully..");

            //Step2		
            System.out.println("\n\n 2nd ===> get Mail Session..");
            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(adminEmail));
            generateMailMessage.setSubject("test");
            String emailBody = message;
            generateMailMessage.setContent(emailBody, "text/html");
            System.out.println("Mail Session has been created successfully..");

            //Step3		
            System.out.println("\n\n 3rd ===> Get Session and Send mail");
            Transport transport = getMailSession.getTransport("smtp");

            // Enter your correct gmail UserID and Password (XXXApp Shah@gmail.com)
            transport.connect("smtp.gmail.com", "noreply.dummy10@gmail.com", "atalanta85");
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            System.out.println("Error mail "+e.getMessage());
        }
    }
    
    private static void loadProperties() {

        Properties datos = new Properties( );
        String data="";
        try
        {
                FileInputStream input = new FileInputStream( "src/main/resources/admin_email.properties" );
                datos.load( input );
                adminEmail=datos.getProperty("admin.email");
        }
        catch( Exception e )
        {
                e.printStackTrace();
        }

    }

}
