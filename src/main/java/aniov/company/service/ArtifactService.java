package aniov.company.service;

import aniov.company.model.Artifact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by Marius on 6/19/2017.
 */
public class ArtifactService {

    private SessionFactory sessionFactory;

    public Artifact findArtifactById(Long id) {
        Session session = sessionFactory.openSession();
        Artifact artifact = session.get(Artifact.class, id);
        session.close();
        return artifact;
    }
}
