package aniov.company.service;

import aniov.company.model.Hero;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Marius on 6/18/2017.
 */
public class HeroService {

    private SessionFactory sessionFactory;

    public void saveHero(Hero hero) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(hero);
        session.getTransaction().commit();
        session.close();
    }

    public void updateHero(Hero hero) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(hero);
        session.getTransaction().commit();
        session.close();
    }

    public Hero findHeroById(Long id) {
        Session session = sessionFactory.openSession();
        Hero hero = session.get(Hero.class, id);
        session.close();
        return hero;

    }

    public List<Hero> findHeroesByName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Hero h where h.name = :Name").setParameter("Name", name);
        List<Hero> heroes = query.list();
        session.close();
        return heroes;
    }

    public void deleteHero(Hero hero) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(hero);
        session.getTransaction().commit();
        session.close();
    }
}
