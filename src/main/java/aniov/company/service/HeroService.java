package aniov.company.service;

import aniov.company.model.character.HeroFactory;
import aniov.company.model.character.hero.Hero;
import aniov.company.model.character.hero.HeroType;
import aniov.company.storage.StorageAccess;
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

    private StorageAccess dbDao;

    public List<Hero> findAllHeroes() {
        List<Object> objects = (List<Object>) dbDao.findAll(Hero.class);
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

    public Hero createNewHero(String name, HeroType heroType) {
        Hero hero = HeroFactory.getInstance().createNewHero(name, heroType);
        return saveHero(hero);
    }

    public void updateHero(Hero hero) {
        dbDao.update(hero);
    }

    public void deleteHero(Hero hero) {
        dbDao.delete(hero);
    }
}
