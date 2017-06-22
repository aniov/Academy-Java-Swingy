package aniov.company;

/**
 * RPG Game Entry point
 */
public class App {

    public static void main(String[] args) {

        if (args.length != 1 || !args[0].equals("console") || args[0].equals("gui")) {
            return;
        }
        StartRpg startRpg = new StartRpg();
        startRpg.start(args[0]);
    }

}
