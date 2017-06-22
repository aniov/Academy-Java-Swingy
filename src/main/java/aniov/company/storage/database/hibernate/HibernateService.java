package aniov.company.storage.database.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Marius on 6/18/2017.
 */

public abstract class HibernateService {

    public final static SessionFactory sessionFactory = setup();

    private HibernateService() {
    }

    private static SessionFactory setup() {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            return new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
            return null;
        }
    }

    public static void exit() {
        sessionFactory.close();
    }
}
