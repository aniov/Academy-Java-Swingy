package aniov.company.model.character;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Marius on 6/18/2017.
 */
@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class Character implements Serializable {

    @NotNull
    private Integer level;

    @NotNull
    private Integer health;

    @NotNull
    private Integer attack;

    @NotNull
    private Integer defence;

    @NotNull
    private Integer hitPoints;
}
