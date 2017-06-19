package aniov.company.model;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Marius on 6/18/2017.
 */
@MappedSuperclass
@Data
public abstract class Character implements Serializable{

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
