/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.util;

import co.edu.uniandes.csw.mpusedvehicle.persistence.AdminPersistence;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.io.FileInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author hl.murcia222
 */
public class MailManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminPersistence.class);

    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;

    public static void generateAndSendEmail(String message, String emailRecipient, String subject) {

        try {

            //loadProperties();
            //Step1
            LOGGER.info("\n 1st ===> setup Mail Server Properties..");
            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");
            LOGGER.info("Mail Server Properties have been setup successfully..");

            //Step2	
            LOGGER.info("\n\n 2nd ===> get Mail Session..");
            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipient));
            generateMailMessage.setSubject(subject);
            String emailBody = message;
            generateMailMessage.setContent(emailBody, "text/html");
            LOGGER.info("Mail Session has been created successfully..");

            //Step3
            LOGGER.info("\n\n 3rd ===> Get Session and Send mail");
            Transport transport = getMailSession.getTransport("smtp");

            // Enter your correct gmail UserID and Password (XXXApp Shah@gmail.com)
            transport.connect("smtp.gmail.com", "noreply.dummy10@gmail.com", "atalanta85");
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private static void loadProperties() {

        Properties datos = new Properties();
        String data = "";
        try {
            FileInputStream input = new FileInputStream("src/main/resources/admin_email.properties");
            datos.load(input);
            //adminEmail=datos.getProperty("admin.email");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

}
