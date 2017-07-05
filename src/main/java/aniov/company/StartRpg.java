package aniov.company;

import aniov.company.controller.RpgController;
import aniov.company.model.Model;
import aniov.company.view.consoleView.ConsoleView;
import aniov.company.view.swingView.SwingView;

/**
 *
 */
public class StartRpg {

    private static ConsoleView consoleView = new ConsoleView();
    private static SwingView swingView = new SwingView();
    private static Model model = new Model();
    private static RpgController rpgController = new RpgController(model);
    private static boolean consoleIsOn = false;

    void start(String input) {
        if (model.getStorageDao().getSessionFactory() == null) {
            System.out.println("Cannot connect to data base...closing");
            return;
        }
        setViewType(input);
        rpgController.ShowView();
    }

    public static void switchView() {
        if (consoleIsOn) {
            consoleIsOn = false;
            rpgController.setRpgView(swingView);
            rpgController.ShowView();
        } else {
            consoleIsOn = true;
            rpgController.setRpgView(consoleView);
            rpgController.ShowView();
        }
    }

    private void setViewType(String input) {
        if (input.equals("console")) {
            consoleIsOn = true;
            rpgController.setRpgView(consoleView);

        } else if (input.equals("gui")) {
            rpgController.setRpgView(swingView);
        }
    }
}
