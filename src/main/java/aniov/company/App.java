package aniov.company;

import aniov.company.controller.RpgController;
import aniov.company.service.HeroService;
import aniov.company.service.hibernate.HibernateService;
import aniov.company.view.RpgView;
import aniov.company.view.swingView.SwingView;

/**
 * RPG Game Entry point
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        HibernateService service = new HibernateService();
        service.setup();

        RpgView rpgView = new SwingView();
        HeroService service1 = new HeroService();

        RpgController controller = new RpgController(rpgView, service1);

        service.exit();

    }
}
