package aniov.company.service;

import aniov.company.model.Artifact;
import aniov.company.model.Dao.ArtifactDao;

/**
 * Created by Marius on 6/19/2017.
 */
public class ArtifactService {

    private ArtifactDao artifactDao;

    public Artifact findArtifactById(Long id) {
        return (Artifact) artifactDao.findById(Artifact.class, id);
    }

    public void saveArtifact(Artifact artifact) {
        artifactDao.saveOrUpdate(artifact);
    }

    public void deleteArtifact(Artifact artifact) {
        artifactDao.delete(artifact);
    }
}
