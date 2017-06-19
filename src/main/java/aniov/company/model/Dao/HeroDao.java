package aniov.company.model.Dao;

import aniov.company.model.hero.Hero;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Marius on 6/19/2017.
 */
public class HeroDao extends AbstractDao {

    public List<Hero> findHeroesByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from Hero h where h.name = :Name").setParameter("Name", name);
            List<Hero> heroes = query.list();
            return heroes;
        } catch (HibernateException e) {
            handleException(e);
            return null;
        }
    }
}
