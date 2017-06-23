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
        //Only letters, min 3, max 15
        return name.matches("[a-zA-Z]{3,25}");
    }

    public HeroType[] getHeroTypes() {
        return HeroType.values();
    }
}