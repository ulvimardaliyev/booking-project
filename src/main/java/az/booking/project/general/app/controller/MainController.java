package az.booking.project.general.app.controller;

import az.booking.project.general.app.service.MainService;

public class MainController {

    private MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    public void onlineBoard() {
        mainService.onlineBoard();
    }

    public boolean exit() {
        return mainService.exit();
    }
}
