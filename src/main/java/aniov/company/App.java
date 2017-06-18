package aniov.company;

import aniov.company.service.hibernate.HibernateService;

/**
 * RPG Game Entry point
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        /*DataBaseUtil dataBaseUtil = new DataBaseUtil();
        dataBaseUtil.connectToDb();*/

        HibernateService hibernateService = new HibernateService();
        hibernateService.setup();
        hibernateService.exit();

    }
}
