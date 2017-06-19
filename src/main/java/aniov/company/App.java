package aniov.company;

import aniov.company.controller.RpgController;
import aniov.company.service.hibernate.HibernateService;
import aniov.company.view.consoleView.ConsoleView;
import aniov.company.view.swingView.SwingView;

/**
 * RPG Game Entry point
 */
public class App {

    private static HibernateService hibernateService = new HibernateService();
    private static RpgController rpgController = new RpgController();
    private static ConsoleView consoleView = new ConsoleView();
    private static SwingView swingView = new SwingView();

    public static void main(String[] args) {

        if (args.length == 1 && setView(args[0])) {

            startRPG();
            hibernateService.exit();
        }

    }

    private static void startRPG() {

    }

    private static boolean setView(String input) {
        if (input.equals("console")) {
            rpgController.setRpgView(consoleView);
            rpgController.displayMainWindow();
            return true;

        } else if (input.equals("gui")) {
            rpgController.setRpgView(swingView);
            return true;
        }
        return false;
    }
}
