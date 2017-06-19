package aniov.company.service;

import aniov.company.model.Dao.HeroDao;
import aniov.company.model.Hero;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marius on 6/18/2017.
 */
@AllArgsConstructor
public class HeroService {

    private HeroDao heroDao;

    public List<Hero> findAllHeroes() {
        List<Object> objects =  heroDao.findAll(Hero.class);
        List<Hero> heroes = new ArrayList<Hero>();
        for (Object object : objects) {
            heroes.add((Hero)object);
        }
        return heroes;
    }

    public Hero findHeroById(Long id) {
        return (Hero) heroDao.findById(Hero.class, id);
    }

    public List<Hero> findHeroByName(String name) {
        return heroDao.findHeroesByName(name);
    }

    public void saveHero(Hero hero) {
        heroDao.saveOrUpdate(hero);
    }

    public void deleteHero(Hero hero) {
        heroDao.delete(hero);
    }
}
