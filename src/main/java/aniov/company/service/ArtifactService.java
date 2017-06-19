package aniov.company.service;

import aniov.company.model.Dao.ArtifactDao;
import aniov.company.model.artifact.Artifact;

/**
 * Created by Marius on 6/19/2017.
 */
public class ArtifactService {

    private ArtifactDao artifactDao = new ArtifactDao();

    public Artifact findArtifactById(Long id) {
        return (Artifact) artifactDao.findById(Artifact.class, id);
    }

    public Artifact saveArtifact(Artifact artifact) {
        return (Artifact) artifactDao.save(artifact);
    }

    public void updateArtifact(Artifact artifact) {
        artifactDao.update(artifact);
    }

    public void deleteArtifact(Artifact artifact) {
        artifactDao.delete(artifact);
    }
}
