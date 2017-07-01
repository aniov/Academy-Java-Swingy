package aniov.company.controller;

import aniov.company.model.character.hero.Hero;
import aniov.company.model.character.hero.HeroType;
import aniov.company.view.RpgView;
import lombok.Data;

import java.util.List;

/**
 * Created by Marius on 6/20/2017.
 */
@Data
public abstract class ObserverOfTheView {

    public RpgView rpgView;

    public abstract void ShowView();

    public void setRpgView(RpgView rpgView) {
        this.rpgView = rpgView;
        this.rpgView.addObserver(this);
    }

    public abstract List<Hero> getAllHeroes();

    public abstract Hero getHeroById(Long id);

    public abstract Hero createNewHero(String name, String type);

    public boolean isHeroNameValid(String name) {
        //Only letters, min 3, max 20
        return name.matches("[a-zA-Z]{3,20}");
    }

    public abstract void createMapAndStartGame(Hero hero);

    public abstract String[][] getMap();

    public abstract boolean moveHeroUp();

    public abstract boolean moveHeroDown();

    public abstract boolean moveHeroLeft();

    public abstract boolean moveHeroRight();

    public abstract boolean gameIsWon();

    public HeroType[] getHeroTypes() {
        return HeroType.values();
    }
}
