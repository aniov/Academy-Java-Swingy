package aniov.company;

import aniov.company.controller.RpgController;
import aniov.company.model.Model;
import aniov.company.storage.database.hibernate.HibernateService;
import aniov.company.view.consoleView.ConsoleView;
import aniov.company.view.swingView.SwingView;

/**
 * Created by Marius on 6/23/2017.
 */
public class StartRpg {

    private ConsoleView consoleView = new ConsoleView();
    private SwingView swingView = new SwingView();
    private Model model = new Model();
    private RpgController rpgController = new RpgController(model);

    public void start(String input) {
        setViewType(input);
        rpgController.ShowView();


       // HibernateService.exit();
    }

    private void setViewType(String input) {
        if (input.equals("console")) {
            rpgController.setRpgView(consoleView);

        } else if (input.equals("gui")) {
            rpgController.setRpgView(swingView);
        }
    }
}
