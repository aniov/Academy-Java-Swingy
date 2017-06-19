package aniov.company.controller;

import aniov.company.service.HeroService;
import aniov.company.view.RpgView;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Marius on 6/19/2017.
 */
@Data
@NoArgsConstructor
public class RpgController {

    private RpgView rpgView;
    private HeroService heroService = new HeroService();

    public void setRpgView(RpgView rpgView) {
        this.rpgView = rpgView;
    }

    public void displayMainWindow() {
        rpgView.displayAllHeroes(heroService.findAllHeroes());
    }

}
