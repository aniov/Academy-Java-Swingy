package aniov.company.model;

import aniov.company.model.artifact.Artifact;
import aniov.company.model.character.hero.Hero;
import aniov.company.model.map.GameMap;
import aniov.company.model.map.GamePlay;
import aniov.company.service.HeroService;
import aniov.company.storage.StorageAccess;
import aniov.company.storage.database.DataBaseStorageDao;
import lombok.Data;

import java.awt.*;

/**
 * Created by Marius on 6/23/2017.
 */
@Data
public class Model {

    private StorageAccess storageDao = new DataBaseStorageDao();//We will use for now the DB Storage
    private Hero hero;
    private HeroService heroService = new HeroService(storageDao);
    private GameMap gameMap;
    private GamePlay gamePlay;

    public void createGameMap() {
        this.gameMap = new GameMap(hero.getLevel());
    }

    public void startGamePlay() {
        this.gamePlay = new GamePlay(gameMap, hero);
    }

    public void heroMove(Point move) {
        gamePlay.heroMove(move);
    }

    public boolean fight() {
        return gamePlay.fight();
    }

    public boolean tryToRun() {
        return gamePlay.tryToRun();
    }

    public Artifact getNewArtifact() {
        return gamePlay.getArtifact();
    }

    public boolean isGameWon() {
        if (gameMap.isGameWin()) {
            storageDao.update(hero);
            return true;
        }
        return false;
    }
}
