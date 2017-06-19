package aniov.company.model.Dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Marius on 6/19/2017.
 */
public abstract class AbstractDao {

    protected SessionFactory sessionFactory;

    public void saveOrUpdate(Object object) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(object);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Object findById(Class c, Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Object object = session.load(c, id);
            return object;
        } catch (HibernateException e) {
            handleException(e);
            return null;
        } finally {
            session.close();
        }
    }

    public List<Object> findAll(Class c) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("from " + c.getName());
            List<Object> objects = query.list();
            return objects;
        } catch (HibernateException e) {
            handleException(e);
            return null;
        } finally {
            session.close();
        }
    }

    public void delete(Object object) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            session.close();
        }
    }

    protected void handleException(HibernateException e) {
        System.out.println("An Error Occurred: " + e);
    }
}
