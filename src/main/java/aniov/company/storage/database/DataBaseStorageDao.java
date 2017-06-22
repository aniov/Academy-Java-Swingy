package aniov.company.storage.database;

import aniov.company.storage.database.hibernate.HibernateService;
import aniov.company.storage.StorageAccess;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Collection;
import java.util.List;

/**
 * Created by Marius on 6/19/2017.
 */

public class DataBaseStorageDao implements StorageAccess {

    protected SessionFactory sessionFactory = HibernateService.sessionFactory;

    public Object save(Object object) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(object);
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

    @Override
    public Collection<Object> findByName(Class c, String name) {

        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(String.format("from %s where name = :Name", c.getName())).setParameter("Name", name);
            List<Object> objectList = query.list();
            return objectList;
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

    public void handleException(Exception e) {
        System.out.println("An Error Occurred: " + e);
    }
}
