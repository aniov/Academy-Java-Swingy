package aniov.company.service.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Marius on 6/18/2017.
 */

public class HibernateService {

    public static SessionFactory sessionFactory;

    public HibernateService() {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        setup();
    }

    public void setup() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public void exit() {
        sessionFactory.close();
    }
}
