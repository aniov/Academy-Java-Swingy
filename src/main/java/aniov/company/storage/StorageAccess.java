package aniov.company.storage;

import java.util.Collection;

/**
 * Created by Marius on 6/22/2017.
 */
public interface StorageAccess {

    Object save(Object object);

    void update(Object object);

    void delete(Object object);

    Collection<Object> findAll(Class c);

    Object findById(Class c, Long id);

    Collection<Object> findByName(Class c, String name);

    void handleException(Exception e);
}
