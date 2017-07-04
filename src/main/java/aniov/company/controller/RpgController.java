package aniov.company.controller;

import aniov.company.model.Model;
import aniov.company.model.artifact.Artifact;
import aniov.company.model.character.hero.Hero;
import aniov.company.model.character.hero.HeroType;
import aniov.company.model.map.GamePlay;
import aniov.company.service.ArtifactService;
import aniov.company.service.HeroService;
import lombok.Data;

import java.awt.*;
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
    public void deleteHero(Hero hero) {
        heroService.deleteHero(hero);
    }

    @Override
    public void createMapAndStartGame(Hero hero) {
        model.setHero(hero);
        model.createGameMap();
        model.startGamePlay();
    }

    @Override
    public String[][] getMap() {
        return model.getGameMap().getMapForView();
    }

    @Override
    public void moveHeroUp() {
        moveHero(GamePlay.UP);
    }

    @Override
    public void moveHeroDown() {
        moveHero(GamePlay.DOWN);
    }

    @Override
    public void moveHeroLeft() {
        moveHero(GamePlay.LEFT);
    }

    @Override
    public void moveHeroRight() {
        moveHero(GamePlay.RIGHT);
    }

    private void moveHero(Point move) {

        model.heroMove(move);
        if (model.isGameWon()) {
            rpgView.heroWon();
            return;
        }
        if (model.getGamePlay().getVillain() == null) { //if there is no villain we just move
            return;
        }
        String villainType = model.getGamePlay().getVillain().getType().name();

        if (rpgView.wantToFight(villainType)) {
            fight();
        } else {
            if (model.tryToRun()) {
                rpgView.heroEscapedVillain();
            } else {
                rpgView.heroCouldNotEscape();
                fight();
            }
        }
    }

    private void fight() {
        if (model.fight()) {
            rpgView.heroWonTheFight();
            newArtifact();//the fight is won, we see if the villain dropped an artifact
        } else {
            rpgView.heroLostTheFight();
        }
    }

    private void newArtifact() {
        Artifact artifact = model.getNewArtifact();

        if (artifact != null) {
            boolean keep = rpgView.keepThisArtifact(artifact.toString());
            if (keep) {
                model.getHero().addArtifact(artifact);
            }
        }
    }
}
