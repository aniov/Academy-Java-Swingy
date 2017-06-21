package aniov.company.view.consoleView;

import aniov.company.controller.ObserverOfTheView;
import aniov.company.model.hero.Hero;
import aniov.company.view.RpgView;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Marius on 6/19/2017.
 */
public class ConsoleView implements RpgView {

    public void displayAllHeroes(List<Hero> heroes) {
        System.out.println("Heroes:");

        for (Hero hero : heroes) {
            System.out.println(hero.getId() + ". Name: " + hero.getName() + ", Type: " + hero.getHeroType() + ", Artifacts: " + hero.getArtifacts());
        }
    }

    public void displayHero(Hero hero) {

    }

    public void readInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert something: ");
        String myText = scanner.nextLine();

        for (ObserverOfTheView observer : observers) {
            observer.getTextFromView(myText);
        }
    }

    @Override
    public void addObserver(ObserverOfTheView observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ObserverOfTheView observer) {
        observers.remove(observer);
    }
}
