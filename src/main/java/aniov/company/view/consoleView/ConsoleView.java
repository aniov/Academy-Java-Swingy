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
    private List<Hero> heroes;
    private Hero currentHero;

    @Override
    public void addObserver(ObserverOfTheView observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ObserverOfTheView observer) {
        observers.remove(observer);
    }

    /** Enter point*/
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
        heroes = observers.get(0).getAllHeroes();
        displayAllHeroes();
        while (!pickHeroOrCreate()) {
            displayAllHeroes();
        }
    }

    @Override
    public Hero choseHero(Integer heroIndex) {
        Long heroId = heroes.get(heroIndex).getId();
        return observers.get(0).getHeroById(heroId);
    }

    private void displayAllHeroes() {
        System.out.println("\nHeroes List:" + (heroes.isEmpty() ? " you don't have any heroes" : "" + "\n----------------------------------------------"));

        ListIterator iterator = heroes.listIterator();
        while (iterator.hasNext()) {
            Hero hero = (Hero) iterator.next();
            System.out.println(iterator.nextIndex() + ". Name: " + hero.getName() + ", Type: " + hero.getHeroType() + ", Artifacts: ");
            for (Artifact artifact : hero.getArtifacts()) {
                System.out.println("\t\t" + artifact);
            }
        }
        System.out.print("Chose hero number from the list\nCreate a new one - press y\n-> ");
    }

    private boolean pickHeroOrCreate() {
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("y")) {
            createNewHero();
            heroes = observers.get(0).getAllHeroes();
            displayAllHeroes();
            pickHeroOrCreate();
            return true;
        } else {
            try {
                Integer heroIndex = Integer.parseInt(input);
                if (heroIndex > 0 && heroIndex <= heroes.size()) {
                    currentHero = choseHero(heroIndex - 1);
                    enterGamePlay();
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

    private void enterGamePlay() {
        System.out.println("It's time to PLAY \nYour hero: " + currentHero.getName() + " Artifacts: " + currentHero.getArtifacts() + "\n");
        scanner.nextLine();
        observers.get(0).createMap(currentHero);
        displayGameMap();
        readInputMoves();

    }

    private void readInputMoves() {
    }

    private void displayGameMap() {
        String[][] map = observers.get(0).getMap();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("you can move up(W) - down(S) - left(A) - right(D)");
    }

    private boolean createNewHero() {

        while (true) {
            System.out.println("\nCreate new Hero \n-------------------------------------\nEnter the name of your new character(Only letters <min 3, max 25>): ");

            HeroType[] heroTypes = observers.get(0).getHeroTypes();
            String newHeroName = scanner.nextLine();
            if (!observers.get(0).isHeroNameValid(newHeroName)) {
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
                    observers.get(0).createNewHero(newHeroName, heroTypes[heroTypeSelected].name());
                    return true;
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
                return false;
            }
            return false;
        }
    }

}
