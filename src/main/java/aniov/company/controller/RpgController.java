package aniov.company.controller;

import aniov.company.model.character.hero.Hero;
import aniov.company.service.ArtifactService;
import aniov.company.service.HeroService;
import aniov.company.storage.database.DataBaseStorageDao;
import aniov.company.view.RpgView;
import lombok.Data;

import java.util.List;

/**
 * Created by Marius on 6/19/2017.
 */
@Data
public class RpgController extends ObserverOfTheView {

    private final static DataBaseStorageDao dataBaseStorageDao = new DataBaseStorageDao();
    private HeroService heroService = new HeroService(dataBaseStorageDao);
    private ArtifactService artifactService = new ArtifactService(dataBaseStorageDao);

    @Override
    public void update() {

    }

    @Override
    public void getTextFromView(String input) {
        System.out.println("I got this from view: " + input);
    }

    @Override
    public void setRpgView(RpgView rpgView) {
        this.rpgView = rpgView;
        this.rpgView.addObserver(this);

    }

    @Override
    public List<Hero> getAllHeroes() {
        return heroService.findAllHeroes();
    }
}
