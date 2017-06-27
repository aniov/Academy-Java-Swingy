package aniov.company.view.consoleView;

import aniov.company.controller.ObserverOfTheView;
import aniov.company.model.artifact.Artifact;
import aniov.company.model.character.hero.Hero;
import aniov.company.model.character.hero.HeroType;
import aniov.company.view.RpgView;

import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Created by Marius on 6/19/2017.
 */
public class ConsoleView implements RpgView {

    private static final Scanner scanner = new Scanner(System.in);
    private ObserverOfTheView controllerObserver;
    private List<Hero> heroes;
    private Hero currentHero;
    private boolean exitGame;
    private boolean fightIsLost;

    @Override
    public void addObserver(ObserverOfTheView observer) {
        observers.add(observer);
        controllerObserver = observers.get(0);
    }

    @Override
    public void removeObserver(ObserverOfTheView observer) {
        observers.remove(observer);
    }

    /**
     * Enter point
     */
    @Override
    public void showMainInterface() {
        System.out.print("Welcome to RPG Game\nPress Y(es) if you want to start: ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("y")) {
            enterHeroInterface();
        }
    }

    @Override
    public void enterHeroInterface() {
        while (true) {
            heroes = controllerObserver.getAllHeroes();
            displayAllHeroes();

            while (!pickHeroOrCreate() && currentHero != null) {
                displayAllHeroes();
            }
            if (exitGame) {
                return;
            }
            /** Enter game play*/
            enterGamePlay();
        }
    }

    @Override
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
        System.out.print("\n~Chose hero number from the list OR\n~Create a new one - press y (for new a hero)\n~If you want to exit the game, type 'exit': \n-> ");
    }

    private boolean pickHeroOrCreate() {
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("y")) {
                createNewHero();
                heroes = controllerObserver.getAllHeroes();
                displayAllHeroes();
                return true;
            } else if (input.equalsIgnoreCase("exit")) {
                exitGame = true;
            } else {
                try {
                    Integer heroIndex = Integer.parseInt(input);
                    if (heroIndex > 0 && heroIndex <= heroes.size()) {
                        currentHero = choseHero(heroIndex - 1);
                        return true;
                    } else {
                        System.out.println("That hero doesn't exist");
                    }
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            return false;
        }
    }

    private void enterGamePlay() {

        System.out.print("It's time to PLAY \n\nYour hero: ");
        displayHeroStats();
        System.out.println("press any key to start...");
        scanner.nextLine();
        controllerObserver.createMapAndStartGame(currentHero);
        fightIsLost = false;
        while (true) {
            displayGameMap();
            readInputMoves();
            displayHeroStats();
            if (fightIsLost) {
                return;
            } else if (controllerObserver.gameIsWon()) {
                playerWin();
                return;
            }
        }
    }

    private void playerWin() {
        System.out.println("\t\t\nYou Won !!!!\n");
        currentHero = null;
    }

    private boolean readInputMoves() {

        while (true) {
            System.out.println("your move: ");
            String inputMove = scanner.nextLine().toUpperCase();
            switch (inputMove) {
                case "W":
                    return controllerObserver.moveHeroUp();
                case "S":
                    return controllerObserver.moveHeroDown();
                case "A":
                    return controllerObserver.moveHeroLeft();
                case "D":
                    return controllerObserver.moveHeroRight();
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
        System.out.println("\nyou can move up(W) - down(S) - left(A) - right(D)\n");
    }

    private boolean createNewHero() {

        while (true) {
            System.out.println("\nCreate new Hero \n-------------------------------------\nEnter the name of your new character(Only letters <min 3, max 25>): ");

            HeroType[] heroTypes = controllerObserver.getHeroTypes();
            String newHeroName = scanner.nextLine();
            if (!controllerObserver.isHeroNameValid(newHeroName)) {
                System.out.println("The name you've entered is not valid");
                continue;
            }
            System.out.println("Chose type of your hero (press number): ");

            for (int i = 0; i < heroTypes.length; i++) {
                System.out.println("\t" + i + ". " + heroTypes[i] + " (health: " + heroTypes[i].getHealth()
                        + ", attack: " + heroTypes[i].getAttack() + ", defence: " + heroTypes[i].getDefence()
                        + ", hit points: " + heroTypes[i].getHitPoints() + ")");
            }
            System.out.print("-> ");
            try {
                Integer heroTypeSelected = scanner.nextInt();
                if (heroTypeSelected >= 0 && heroTypeSelected < heroTypes.length) {
                    controllerObserver.createNewHero(newHeroName, heroTypes[heroTypeSelected].name());
                    return true;
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
                return false;
            }
            return false;
        }
    }

    @Override
    public boolean wantToFight() {

        while (true) {
            System.out.println("You encounter a Villain. You can fight him or try to run (will have 50% chance to happen)\nto Fight press 'f' to Run press 'r': ");
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

    private void displayHeroStats() {
        System.out.println(currentHero);
    }


}
