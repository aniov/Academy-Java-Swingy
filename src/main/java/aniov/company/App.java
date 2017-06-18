package aniov.company;

import aniov.company.service.database.DataBaseUtil;

/**
 * RPG Game Entry point
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        DataBaseUtil dataBaseUtil = new DataBaseUtil();
        dataBaseUtil.connectToDb();

    }
}
