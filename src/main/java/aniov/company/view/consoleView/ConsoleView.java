package aniov.company.view.consoleView;

import aniov.company.controller.ObserverOfTheView;
import aniov.company.model.character.hero.Hero;
import aniov.company.view.RpgView;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Marius on 6/19/2017.
 */
public class ConsoleView implements RpgView{

    private List<Hero> heroes;

    @Override
    public void addObserver(ObserverOfTheView observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ObserverOfTheView observer) {
        observers.remove(observer);
    }

    /*public void displayAllHeroes() {
        System.out.println("Heroes:");

        for (Hero hero : heroes) {
            System.out.println(hero.getId() + ". Name: " + hero.getName() + ", Type: " + hero.getHeroType() + ", Artifacts: " + hero.getArtifacts());
        }
    }

    public void displayHero(Hero hero) {

    }*/



    @Override
    public void showMainInterface() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to RPG Game\nPress Y(es) if you want to start: ");
        String myText = scanner.nextLine();
        if (myText.equalsIgnoreCase("y")) {
            //enterHeroInterface();
            System.out.println("We can start");
        }
    }

   /* @Override
    public void enterHeroInterface() {
        heroes = observers.get(0).getAllHeroes();
        displayAllHeroes();
    }*/

}
