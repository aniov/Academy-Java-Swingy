package aniov.company.controller;

import aniov.company.model.Model;
import aniov.company.model.character.hero.Hero;
import aniov.company.model.character.hero.HeroType;
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

    private Model model;
    private HeroService heroService;
    private ArtifactService artifactService;

    public RpgController(Model model) {
        this.model = model;
        this.heroService = new HeroService(this.model.getStorageDao());
        this.artifactService = new ArtifactService(this.model.getStorageDao());
    }

    @Override
    public void ShowView() {
        rpgView.showMainInterface();
    }

    @Override
    public List<Hero> getAllHeroes() {
        return heroService.findAllHeroes();
    }

    @Override
    public Hero getHeroById(Long id) {
        return heroService.findHeroById(id);
    }

    @Override
    public Hero createNewHero(String name, String type) {
        HeroType heroType = HeroType.valueOf(type);
        return heroService.createNewHero(name, heroType);
    }

    @Override
    public void createMap(Hero hero) {
        model.setHero(hero);
        model.createGameMap();
    }

    @Override
    public String[][] getMap() {
        return model.getGameMap().getTheMap();
    }
}
