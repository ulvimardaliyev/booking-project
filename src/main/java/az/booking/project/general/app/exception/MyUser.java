package az.booking.project.general.app.exception;

import az.booking.project.general.app.constants.ExceptionStatusCodes;

public class MyUser {

    private int age;
    private String username;

    public MyUser(int age, String username) {
        this.age = age;
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String findUser(String username, int age) throws UserNotFoundException {
        if (!username.equals("Ulvi")) {
            throw new UserNotFoundException(ExceptionStatusCodes.SERIAL_NUMBER_NOT_FOUND.getMessage(),
                    ExceptionStatusCodes.SERIAL_NUMBER_NOT_FOUND.getStatusCode());
        }
        return username;
    }

    public static void main(String[] args) {
        MyUser myUser = new MyUser(10, "Khalid");
        try {
            myUser.findUser("Khalid", 10);
        } catch (UserNotFoundException e) {

        }
    }
}
