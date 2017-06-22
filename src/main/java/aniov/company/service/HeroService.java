package aniov.company.service;

import aniov.company.model.character.hero.Hero;
import aniov.company.storage.database.DataBaseStorageDao;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Marius on 6/18/2017.
 */
@Data
@AllArgsConstructor
public class HeroService {

    private DataBaseStorageDao dbDao;

    public List<Hero> findAllHeroes() {
        List<Object> objects = dbDao.findAll(Hero.class);
        List<Hero> heroes = new ArrayList<Hero>();
        for (Object object : objects) {
            heroes.add((Hero) object);
        }
        return heroes;
    }

    public Hero findHeroById(Long id) {
        return (Hero) dbDao.findById(Hero.class, id);
    }

    public List<Hero> findHeroByName(String name) {
        Collection<Object> objects = dbDao.findByName(Hero.class, name);
        List<Hero> heroes = new ArrayList<>();

        for (Object object : objects) {
            heroes.add((Hero) object);
        }
        return heroes;
    }

    public Hero saveHero(Hero hero) {
        return (Hero) dbDao.save(hero);
    }

    public void updateHero(Hero hero) {
        dbDao.update(hero);
    }

    public void deleteHero(Hero hero) {
        dbDao.delete(hero);
    }
}
