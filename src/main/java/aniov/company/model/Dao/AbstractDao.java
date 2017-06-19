package aniov.company.model.Dao;

import aniov.company.service.hibernate.HibernateService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Marius on 6/19/2017.
 */

public abstract class AbstractDao {

    protected SessionFactory sessionFactory = HibernateService.sessionFactory;

    public Object save(Object object) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Long id = (Long) session.save(object);
            session.getTransaction().commit();
            return object;
        } catch (HibernateException e) {
            handleException(e);
            return null;
        }
    }

    public void update(Object object) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(object);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            handleException(e);
        }
    }

    public Object findById(Class c, Long id) {
        try (Session session = sessionFactory.openSession()) {
            Object object = session.load(c, id);
            return object;
        } catch (HibernateException e) {
            handleException(e);
            return null;
        }
    }

    public List<Object> findAll(Class c) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(String.format("from %s order by id asc", c.getName()));
            List<Object> objects = query.list();
            return objects;
        } catch (HibernateException e) {
            handleException(e);
            return null;
        }
    }

    public void delete(Object object) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            handleException(e);
        }
    }

    protected void handleException(HibernateException e) {
        System.out.println("An Error Occurred: " + e);
    }
}
