package az.booking.project.general.app.service.impl;

import az.booking.project.general.app.repository.PassengerRepository;
import az.booking.project.general.app.repository.impl.PassengerRepositoryImpl;
import az.booking.project.general.app.service.SignUpService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class SignUpServiceImpl implements SignUpService {
    private PassengerRepository passengerRepository;
    private static final String SENT_FROM = "merdeliyev@inbox.ru";
    private static final String PASSWORD = "6LrhWSfU9y5MY79nCWhM";

    @Override
    public boolean signUp(String username, String password, String email) {
        this.passengerRepository = new PassengerRepositoryImpl();
        int userOnDbResult = this.passengerRepository.findCurrentUser(username, password);
        if (userOnDbResult > 0) {
            System.out.println("User is already registered, please use another username and password");
            return false;
        } else {
            System.out.println("Please confirm123");
            return register(username, password, email);
        }
    }

    private boolean register(String username, String password, String email) {
        if (confirmEmail(username, email)) {
            this.passengerRepository = new PassengerRepositoryImpl();
            int a = passengerRepository.saveNewUser(username, password, email);
            System.out.println("A is ");
            System.out.println(a);
            return a > 0;
        }
        return false;
    }

    private boolean confirmEmail(String username, String email) {
        Properties properties = new Properties();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("src/main/resources/emailconfig.properties");
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENT_FROM, PASSWORD);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENT_FROM));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Welcome on board \uD83D\uDE00 ");
            Random random = new Random();
            int randomNum = random.nextInt(10000);
            String msg = "Hi " + username + " \uD83D\uDE00 ️" + "Please, use this code on your registration. " + randomNum;
            //logic comes here

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            Scanner scanner = new Scanner(System.in);
            if (randomNum == scanner.nextInt()) {
                System.out.println("Successful");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}


