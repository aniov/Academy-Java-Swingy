package aniov.company.model.map;

import aniov.company.model.artifact.Artifact;
import aniov.company.model.artifact.ArtifactType;
import aniov.company.model.character.CharacterFactory;
import aniov.company.model.character.hero.Hero;
import aniov.company.model.character.villain.Villain;
import aniov.company.model.character.villain.VillainType;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.Random;

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
    @NonNull
    private Hero hero;

    private Point heroNextPosition = new Point();
    private Villain villain;

    public void heroMove(Point newLocation) {

        heroNextPosition.setLocation(gameMap.getHeroPosition());

        heroNextPosition.x += newLocation.getX();
        heroNextPosition.y += newLocation.getY();

        if (moveIsValid()) {
            gameMap.setNextHeroPosition(heroNextPosition);

            if (isVillain()) {

                /** Generate new Villain based on hero level*/
                villain = (Villain) CharacterFactory.getInstance()
                        .createNewCharacter("Villain", VillainType.BAD_VILLAIN, gameMap.getLevel());
                return;
            }
            /** Set the leaving position of the hero*/ //TODO
            gameMap.setOnMap(gameMap.getHeroPosition(), GameMap.EMPTY);
            /** Set the next position of the hero*/
            gameMap.setOnMap(heroNextPosition, GameMap.HERO);
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

    public boolean fight() {

        if (fightIsWon()) {
            villain = null;
            gameMap.setOnMap(gameMap.getHeroPosition(), GameMap.EMPTY);

            gameMap.setOnMap(gameMap.getNextHeroPosition(), GameMap.HERO);
            gameMap.getHeroPosition().setLocation(gameMap.getNextHeroPosition());
            // We won the fight, we can continue
            return true;
        }
        // We lost the game
        return false;
    }

    private boolean fightIsWon() {

       /* while (villain.getHealth() > 0 && hero.getHealth() > 0) {
                villain.setHealth(new Random().nextBoolean());
        }*/
        return true;
    }

    public Artifact generateNewArtifact() {

        if (new Random().nextBoolean()) {
            int random = new Random().nextInt(ArtifactType.values().length);
            ArtifactType artifactType = ArtifactType.values()[random];

           return new Artifact(hero, artifactType);
        }
        return null;
    }

    public boolean tryToRun() {
        // 50% chance to escape the fight
        boolean run = new Random().nextBoolean();
        if (run) {
            villain = null;
            return true;
        }
        return false;
    }
}
