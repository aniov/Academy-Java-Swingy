package aniov.company.service;

import aniov.company.model.artifact.Artifact;
import aniov.company.storage.StorageAccess;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Marius on 6/19/2017.
 */
@Data
@AllArgsConstructor
public class ArtifactService {

    private StorageAccess storageAccess;

    public Artifact findArtifactById(Long id) {
        return (Artifact) storageAccess.findById(Artifact.class, id);
    }

    public Artifact saveArtifact(Artifact artifact) {
        return (Artifact) storageAccess.save(artifact);
    }

    public void updateArtifact(Artifact artifact) {
        storageAccess.update(artifact);
    }

    public void deleteArtifact(Artifact artifact) {
        storageAccess.delete(artifact);
    }
}
