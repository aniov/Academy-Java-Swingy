package aniov.company.model;

import aniov.company.model.character.hero.Hero;
import aniov.company.model.map.GameMap;
import aniov.company.service.HeroService;
import aniov.company.storage.StorageAccess;
import aniov.company.storage.database.DataBaseStorageDao;
import lombok.Data;

/**
 * Created by Marius on 6/23/2017.
 */
@Data
public class Model {

    private StorageAccess storageDao = new DataBaseStorageDao();//We will use for now the DB Storage
    private Hero hero;
    private HeroService heroService = new HeroService(storageDao);
    private GameMap gameMap;

    public void createGameMap(){
        this.gameMap = new GameMap(hero.getLevel(), hero.getId());
    }
}
