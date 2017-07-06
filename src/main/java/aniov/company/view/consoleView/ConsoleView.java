package aniov.company.view.consoleView;

import aniov.company.StartRpg;
import aniov.company.controller.ObserverOfTheView;
import aniov.company.model.character.hero.Hero;
import aniov.company.model.character.hero.HeroType;
import aniov.company.view.validation.HeroModel;
import aniov.company.view.validation.ModelValidation;
import aniov.company.view.RpgView;

import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Created by Marius on 6/19/2017.
 */
public class ConsoleView extends RpgView {

    private static final Scanner scanner = new Scanner(System.in);
    private ObserverOfTheView controllerObserver;
    private List<Hero> heroes;
    private Hero currentHero;
    private boolean exitApplication;
    private boolean exitGame;
    private boolean exitConsole;
    private boolean fightIsLost;
    private boolean heroWonOnMap;

    @Override
    public void addObserver(ObserverOfTheView observer) {
        super.addObserver(observer);
        controllerObserver = observers.get(0);
    }

    /**
     * Entry point
     */
    @Override
    public void showMainInterface() {
        System.out.print("Welcome to RPG Game\nPress Y(es) if you want to start: ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("y")) {
            enterHeroInterface();
        } else {
            controllerObserver.closeDataBaseConnection();
            return;
        }
    }

    @Override
    public boolean wantToFight(String villainType) {

        while (true) {
            System.out.println("You encounter a " + villainType + ". You can fight him or try to run (will have 50% chance to happen)\nto Fight press 'f' to Run press 'r': ");
            String fightOrRun = scanner.nextLine();
            if (fightOrRun.equalsIgnoreCase("f")) {
                return true;
            } else if (fightOrRun.equalsIgnoreCase("r")) {
                return false;
            } else {
                System.out.println("that's not a valid choice");
            }
        }
    }

    @Override
    public void heroWonTheFight() {
        System.out.println("You won the fight against the Villain");
    }

    @Override
    public void heroWon() {
        heroWonOnMap = true;
    }

    @Override
    public void heroCouldNotEscape() {
        System.out.println("You could not escape this villain, you have to Fight him...\npress 'Enter' key to continue");
        scanner.nextLine();
    }

    @Override
    public void heroEscapedVillain() {
        System.out.println("You could Escaped this villain, you are so lucky...\npress 'Enter' key to continue");
    }

    @Override
    public void heroLostTheFight() {
        System.out.println("You Lost the fight. Press 'Enter' to return to Game board");
        scanner.nextLine();
        fightIsLost = true;
    }

    @Override
    public boolean keepThisArtifact(String artifact) {
        while (true) {
            System.out.println("Villain dropped this artifact: " + artifact + "\nyou want to keep it? Y / N");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("y")) {
                return true;
            } else if (input.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("that's not a valid choice");
            }
        }
    }

    public void enterHeroInterface() {
        exitConsole = false;
        while (true) {
            heroes = controllerObserver.getAllHeroes();
            pickHeroOrCreate();
            if (exitApplication) {
                controllerObserver.closeDataBaseConnection();
                return;
            } else if (exitConsole) {
                return;
            }
            /** Enter game play*/
            enterGamePlay();
        }
    }

    public Hero choseHero(Integer heroIndex) {
        Long heroId = heroes.get(heroIndex).getId();
        return controllerObserver.getHeroById(heroId);
    }

    private void displayAllHeroes() {
        System.out.println("\nHeroes List:" + (heroes.isEmpty() ? " you don't have any heroes" : "" + "\n----------------------------------------------"));

        ListIterator iterator = heroes.listIterator();
        while (iterator.hasNext()) {
            Hero hero = (Hero) iterator.next();
            System.out.println("\t" + iterator.nextIndex() + ". " + hero);
        }
        System.out.print("\n~ Chose hero number from the list OR\n~ Create a new one - press N (for a new hero)\n~ Switch to Graphic view, type: 'switch'\n~ If you want to exit the game, type 'exit': \n-> ");
    }

    private void pickHeroOrCreate() {
        while (true) {
            displayAllHeroes();
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("n")) {
                createNewHero();
                heroes = controllerObserver.getAllHeroes();
            } else if (input.equalsIgnoreCase("switch")) { // switch to Swing View and exit MainInterface
                StartRpg.switchView();
                exitConsole = true;
                return;
            } else if (input.equalsIgnoreCase("exit")) {
                exitApplication = true;
                return;
            } else {
                try {
                    Integer heroIndex = Integer.parseInt(input);
                    if (heroIndex > 0 && heroIndex <= heroes.size()) {
                        currentHero = choseHero(heroIndex - 1);
                        return;
                    } else {
                        System.out.println("That hero doesn't exist");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Try an integer.");
                }
            }
        }
    }

    private boolean createNewHero() {
        ModelValidation validation = new ModelValidation();
        while (true) {
            System.out.println("\nCreate new Hero \n-------------------------------------\nEnter the name of your new character(Only letters <min 3, max 20>): ");

            HeroType[] heroTypes = controllerObserver.getHeroTypes();
            String newHeroName = scanner.nextLine();
            System.out.println("Chose type of your hero (press number): ");

            for (int i = 0; i < heroTypes.length; i++) {
                System.out.println("\t" + (i + 1) + ". " + heroTypes[i] + " (attack: " + heroTypes[i].getAttack()
                        + ", defence: " + heroTypes[i].getDefence()
                        + ", hit points: " + heroTypes[i].getHitPoints() + ")");
            }
            System.out.print("-> ");
            try {
                Integer heroTypeSelected = Integer.parseInt(scanner.nextLine()) - 1;
                if (heroTypeSelected >= 0 && heroTypeSelected < heroTypes.length) {
                    HeroModel model = new HeroModel(newHeroName, heroTypes[heroTypeSelected]);
                    if (validation.isValid(model)) {
                        controllerObserver.createNewHero(newHeroName, heroTypes[heroTypeSelected].name());
                        return true;
                    } else {
                        System.out.println(validation.violations());
                    }
                } else {
                    System.out.println("The number you chose is not part of the Heroes Type list. Please try again !");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try an integer.");
            }
        }
    }

    private void enterGamePlay() {

        System.out.print("It's time to PLAY \n\nYour hero: ");
        displayHeroStats();
        System.out.println("press any key to start...");
        scanner.nextLine();
        controllerObserver.createMapAndStartGame(currentHero);
        fightIsLost = false;
        exitGame = false;
        while (true) {
            displayGameMap();
            readInputMoves();
            displayHeroStats();
            if (fightIsLost || exitGame) {
                return;
            } else if (heroWonOnMap) {
                playerWin();
                return;
            }
        }
    }

    private void playerWin() {
        System.out.println("\t\t\nYou Won !!!!\n");
        currentHero = null;
        heroWonOnMap = false;
    }

    private void readInputMoves() {

        while (true) {
            System.out.println("your move: ");
            String inputMove = scanner.nextLine().toUpperCase();
            switch (inputMove) {
                case "W":
                    controllerObserver.moveHeroUp();
                    return;
                case "S":
                    controllerObserver.moveHeroDown();
                    return;
                case "A":
                    controllerObserver.moveHeroLeft();
                    return;
                case "D":
                    controllerObserver.moveHeroRight();
                    return;
                case "QUIT":
                    exitGame = true;
                    return;
                default:
                    System.out.println("that's not a valid choice");
            }
        }
    }

    private void displayGameMap() {
        String[][] map = controllerObserver.getMap();
        System.out.println("\t\tGAME MAP\n");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print("\t" + map[i][j] + (j == map.length - 1 ? "\n" : ""));
            }
        }
        System.out.println("\nyou can move up(W) - down(S) - left(A) - right(D)\ntype 'quit' to exit this Game\n");
    }

    private void displayHeroStats() {
        System.out.println(currentHero);
    }

}
