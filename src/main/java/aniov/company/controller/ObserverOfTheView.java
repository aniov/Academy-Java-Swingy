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

    public abstract void update();

    public abstract void getTextFromView(String input);

    public abstract void setRpgView(RpgView rpgView);

    public abstract List<Hero> getAllHeroes();
}
