package az.booking.project.general.app.service.impl;

import az.booking.project.general.app.entity.Flight;
import az.booking.project.general.app.entity.Friend;
import az.booking.project.general.app.repository.PassengerRepository;
import az.booking.project.general.app.service.UserService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class UserServiceImpl implements UserService {
    private PassengerRepository passengerRepository;
    private Scanner scanner;
    private static final String SENT_FROM = "merdeliyev@inbox.ru";
    private static final String PASSWORD = "6LrhWSfU9y5MY79nCWhM";
    private static int id = 0;
    public static final String USER_INFO_FILE_PATH = "src/main/resources/userinfo.txt";
    public static final String EMAIL_SERVER_CONFIG = "src/main/resources/emailconfig.properties";

    public UserServiceImpl(PassengerRepository passengerRepository, Scanner scanner) {
        this.passengerRepository = passengerRepository;
        this.scanner = scanner;
    }

    @Override
    public boolean login() {
        System.out.println("You want to login");
        System.out.println("Please enter the username: ");
        String username = scanner.next();
        System.out.println("Please enter the password: ");
        String password = scanner.next();
        int id = passengerRepository.findCurrentUser(username, password);
        if (id > 0) {
            UserServiceImpl.writeUserIdToFile(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean signUp() {
        System.out.println("Please enter username : ");
        String username = scanner.next();
        System.out.println("Please enter password : ");
        String password = scanner.next();
        System.out.println("Please enter valid email : ");
        String email = scanner.next();
        int userOnDbResult = this.passengerRepository.findCurrentUser(username, password);
        if (userOnDbResult > 0) {
            System.out.println("User is already registered, please use another username and password");
            return false;
        } else {
            System.out.println("Please confirm123");
            return register(username, password, email);
        }
    }

    @Override
    public List<Flight> myFlights() {
        int id = UserServiceImpl.getUserId();
        List<Flight> allFlights = passengerRepository.myFlights(id);
        System.out.println(allFlights);
        return allFlights;
    }

    @Override
    public void cancelBooking() {
        System.out.println("Please enter flight id");
        int flightId = scanner.nextInt();
        passengerRepository.deleteFlight(flightId);
    }

    @Override
    public void book() {
        List<Friend> friends = new ArrayList<>();
        System.out.println("Enter the serial number");
        int serialNum = scanner.nextInt();
        System.out.println("Enter the count of people");
        int count = scanner.nextInt();
        System.out.println("Please enter the details of people");
        String friendName = null;
        String friendSurname = null;
        for (int i = 0; i < count; i++) {
            System.out.println("Please enter the name :");
            friendName = scanner.next();
            System.out.println("Please enter the surname : ");
            friendSurname = scanner.next();
            Friend friend = Friend.builder().name(friendName).lastname(friendSurname).build();
            friends.add(friend);
        }
        passengerRepository.bookFlight(serialNum, friends);
    }


    private boolean register(String username, String password, String email) {
        if (confirmEmail(username, email)) {
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
            fileReader = new FileReader(EMAIL_SERVER_CONFIG);
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
            if (randomNum == scanner.nextInt()) {
                System.out.println("Successful");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static int getUserId() {
        File file = new File(USER_INFO_FILE_PATH);
        try (FileReader fileReader = new FileReader(file)) {
            StringBuilder word = new StringBuilder();
            int a;
            while ((a = fileReader.read()) != -1) {
                word.append((char) a);
            }
            id = Integer.parseInt(word.substring(14));
            System.out.println("ID is " + id);
        } catch (IOException io) {
            io.printStackTrace();
        }
        return id;
    }

    public static void writeUserIdToFile(int userId) {
        try (FileWriter fileWriter1 = new FileWriter(USER_INFO_FILE_PATH)) {
            String word = "currentUserId=";
            fileWriter1.append(word).append(Integer.toString(userId));
            fileWriter1.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
