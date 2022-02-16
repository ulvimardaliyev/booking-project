package az.booking.project.general.app.service.impl;

import az.booking.project.general.app.repository.PassengerRepository;
import az.booking.project.general.app.repository.impl.PassengerRepositoryImpl;
import az.booking.project.general.app.service.LoginService;

import java.io.File;
import java.io.FileWriter;

public class LoginServiceImpl implements LoginService {
    private PassengerRepository passengerRepository;
    private static final String USERINFO_PATH = "src/main/resources/userinfo.txt";

    @Override
    public boolean logIn(String username, String password) {
        this.passengerRepository = new PassengerRepositoryImpl();
        int id = passengerRepository.findCurrentUser(username, password);
        System.out.println("Id is " + id);
        //write id to file for later using
        File file = new File(USERINFO_PATH);
        if (id > 0) {
            try (FileWriter fileWriter1 = new FileWriter(file)) {
                String word = "currentUserId=";
                fileWriter1.append(word).append(Integer.toString(id));
                fileWriter1.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
