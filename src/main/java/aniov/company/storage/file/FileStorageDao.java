package aniov.company.storage.file;

import aniov.company.storage.StorageAccess;

import java.util.Collection;

/**
 * Created by Marius on 6/22/2017.
 */
public class FileStorageDao implements StorageAccess {

    @Override
    public Object save(Object object) {
        return null;
    }

    @Override
    public void update(Object object) {
    }

    @Override
    public Collection<Object> findAll(Class c) {
        return null;
    }

    @Override
    public Object findById(Class c, Long id) {
        return null;
    }

    @Override
    public Collection<Object> findByName(Class c, String name) {
        return null;
    }

    @Override
    public void delete(Object object) {
    }

    @Override
    public void handleException(Exception e) {
    }

    @Override
    public void closeSessionFactory() {
    }

    @Override
    public boolean connectToDataBase() {
        return true;
    }
}
