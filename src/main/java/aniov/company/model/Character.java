package aniov.company.model;

import lombok.Data;

import javax.persistence.MappedSuperclass;

/**
 * Created by Marius on 6/18/2017.
 */
@MappedSuperclass
@Data
public abstract class Character {

    private Integer level;
    private Integer health;
    private Integer attack;
    private Integer defence;
    private Integer hitPoints;
}
