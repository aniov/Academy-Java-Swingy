package aniov.company.model.map;

import aniov.company.model.character.villain.Villain;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.awt.*;

/**
 * Created by Marius on 6/24/2017.
 */
@Data
@RequiredArgsConstructor
public class GamePlay {

    public static final Point UP = new Point(-1, 0);
    public static final Point DOWN = new Point(1, 0);
    public static final Point LEFT = new Point(0, -1);
    public static final Point RIGHT = new Point(0, 1);

    @NonNull
    private GameMap gameMap;

    private Point heroNextPosition = new Point();
    private Villain villain;

    public void heroMove(Point newLocation) {

        heroNextPosition.setLocation(gameMap.getHeroPosition());

        heroNextPosition.x += newLocation.getX();
        heroNextPosition.y += newLocation.getY();

        if (moveIsValid()) {
            gameMap.setNextHeroPosition(heroNextPosition);

            if (isVillain()) {

                /** Generate new Villain conforming to the type*/
                villain = new Villain();
                return;
            }
            /** Set the leaving position of the hero*/ //TODO
            if (gameMap.getFromMap(gameMap.getHeroPosition()).equals(GameMap.HERO)) {
                gameMap.setOnMap(gameMap.getHeroPosition(), GameMap.EMPTY);
            } else {
                gameMap.setOnMap(gameMap.getHeroPosition(), GameMap.EMPTY);
            }
            /** Set the next position of the hero*/
            if (gameMap.getFromMap(gameMap.getNextHeroPosition()).equals(GameMap.EMPTY)) {
                gameMap.setOnMap(heroNextPosition, GameMap.HERO);
            } else {
                gameMap.setOnMap(heroNextPosition, GameMap.HERO);
            }
            gameMap.getHeroPosition().setLocation(heroNextPosition);
        } else {
            gameMap.setGameWin(true);
        }
    }

    private boolean moveIsValid() {
        int size = gameMap.getSize();
        return (heroNextPosition.getX() >= 0 && heroNextPosition.getX() < size &&
                heroNextPosition.getY() >= 0 && heroNextPosition.getY() < size);
    }

    private boolean isVillain() {
        String villain = gameMap.getFromMap(heroNextPosition);
        return villain.equals(GameMap.VILLAIN);
    }

}
