package aniov.company;

import aniov.company.service.hibernate.HibernateService;

/**
 * RPG Game Entry point
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        HibernateService service = new HibernateService();
        service.setup();

        service.exit();

    }
}
