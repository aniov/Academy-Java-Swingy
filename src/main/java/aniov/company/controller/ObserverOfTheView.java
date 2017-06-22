package aniov.company.controller;

import aniov.company.model.character.hero.Hero;
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
    }

    public abstract List<Hero> getAllHeroes();
}
