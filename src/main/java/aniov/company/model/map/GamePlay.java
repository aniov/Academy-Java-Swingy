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
    private VillainType villainType;
    private Artifact artifact;

    public void heroMove(Point newLocation) {

        heroNextPosition.setLocation(gameMap.getHeroPosition());

        heroNextPosition.x += newLocation.getX();
        heroNextPosition.y += newLocation.getY();

        if (moveIsValid()) {
            gameMap.setNextHeroPosition(heroNextPosition);

            if (isVillain()) {
                /** Generate new Villain based on hero level*/
                generateNewVillain();
                return;
            }
            /** Set the leaving position of the hero*/
            gameMap.setOnMap(gameMap.getHeroPosition(), GameMap.PASSED);
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
        for (VillainType villainType : VillainType.values()) {
            if (villainType.name().equals(villain)) {
                this.villainType = villainType;
                return true;
            }
        }
        return false;
    }

    public boolean fight() {

        if (fightIsWon()) {
            villain = null;
            artifact = null;
            gameMap.setOnMap(gameMap.getHeroPosition(), GameMap.PASSED);

            gameMap.setOnMap(gameMap.getNextHeroPosition(), GameMap.HERO);
            gameMap.getHeroPosition().setLocation(gameMap.getNextHeroPosition());

            generateNewArtifact();
            addExperience();

            // We won the fight, we can continue
            return true;
        }
        // We lost the game
        return false;
    }

    private void addExperience() {

        Integer maxExperience = (int) (gameMap.getLevel() * 1000 + Math.pow((gameMap.getLevel() - 1), 2) * 450);
        //We appreciate that approx 20% xp gained from killing one villain is reasonable
        Integer xpGained = maxExperience / 5;
        switch (villainType) {
            case BOSS:
                xpGained += (xpGained * 50) / 100; //+50%
                break;
            case SUPER_BAD_VILLAIN:
                xpGained += (xpGained * 40) / 100; //+40%
                break;
            case BAD_VILLAIN:
                xpGained += (xpGained * 10) / 100; //+10%
                break;
            case PUSSY_VILLAIN:
                xpGained -= (xpGained * 10) / 100; //-10%
                break;
        }
        hero.addXpAndLvlUp(xpGained);
    }

    private boolean fightIsWon() { //TODO

       /* while (villain.getHealth() > 0 && hero.getHealth() > 0) {
                villain.setHealth(new Random().nextBoolean());
        }*/
        return true;
    }

    public void generateNewArtifact() {
        /** 50% chance to generate(drop) new Artifact */
        if (new Random().nextBoolean()) {
            int random = new Random().nextInt(ArtifactType.values().length);
            ArtifactType artifactType = ArtifactType.values()[random];

            this.artifact = new Artifact(hero, artifactType, villainType, villain.getLevel());
        }
    }

    private void generateNewVillain() {
        villain = (Villain) CharacterFactory.getInstance()
                .createNewCharacter(villainType.name(), villainType, gameMap.getLevel());
    }

    public boolean tryToRun() {
        /** 50% chance to escape the fight */
        if (new Random().nextBoolean()) {
            villain = null;
            return true;
        }
        return false;
    }
}
