package aniov.company.controller;

import aniov.company.service.HeroService;
import aniov.company.view.RpgView;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Marius on 6/19/2017.
 */
@Data
@AllArgsConstructor
public class RpgController {

    private RpgView rpgView;
    private HeroService heroService;


}
