package aniov.company.controller;

import aniov.company.model.Model;
import aniov.company.model.character.hero.Hero;
import aniov.company.service.ArtifactService;
import aniov.company.service.HeroService;
import aniov.company.storage.database.DataBaseStorageDao;
import lombok.Data;

import java.util.List;

/**
 * Created by Marius on 6/19/2017.
 */
@Data
public class RpgController extends ObserverOfTheView {

    private final static DataBaseStorageDao dataBaseStorageDao = new DataBaseStorageDao();//We will use for now the DB Storage
    private HeroService heroService = new HeroService(dataBaseStorageDao);
    private ArtifactService artifactService = new ArtifactService(dataBaseStorageDao);
    private Model model;

    public RpgController(Model model) {
        this.model = model;
    }

    @Override
    public void ShowView() {
        rpgView.showMainInterface();
    }

    @Override
    public List<Hero> getAllHeroes() {
        return heroService.findAllHeroes();
    }
}
