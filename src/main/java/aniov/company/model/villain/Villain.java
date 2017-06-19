package aniov.company.model.villain;

import aniov.company.model.Character;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/**
 * Created by Marius on 6/18/2017.
 */
@Data
public class Villain extends Character {

    @NotNull
    @Enumerated(EnumType.STRING)
    private VillainType type;
}
