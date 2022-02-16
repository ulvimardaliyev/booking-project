package az.booking.project.general.app.service.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UserServiceImpl {
    private static int id = 0;

    public static int getUserId() {

        File file = new File("src/main/resources/userinfo.txt");
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
}
