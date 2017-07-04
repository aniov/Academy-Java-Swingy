package aniov.company;

/**
 * RPG Game Entry point
 */
public class Swingy {

    public static void main(String[] args) {

        if (args.length != 1 || !(args[0].equals("console") || args[0].equals("gui"))) {
            System.out.println("Use 'console' Or 'gui' as arguments to start the app\n");
            return;
        }
        StartRpg startRpg = new StartRpg();
        startRpg.start(args[0]);

    }

}
