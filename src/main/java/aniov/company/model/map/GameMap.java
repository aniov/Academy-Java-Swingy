package aniov.company.model.map;

import aniov.company.model.character.villain.VillainType;
import lombok.Data;

import java.awt.*;
import java.util.Random;

/**
 * Created by Marius on 6/22/2017.
 */
@Data
public class GameMap {

    private int level;
    private int size;
    private String[][] theMap;
    private Point heroPosition;
    private Point nextHeroPosition;
    private Long heroId;
    private boolean gameWin;

    public static final String EMPTY = "-";
    public static final String HERO = "H";
    public static final String VILLAIN = "V";
    public static final String PASSED = "P";
    public static final String DEAD_VILLAIN = "D";
    public static final String HERO_AND_VILLAIN = "HV";

    public GameMap(int heroLevel, Long heroId) {
        this.level = heroLevel;
        this.heroId = heroId;
        generateMap();
    }

    private void generateMap(){
        size = (level - 1) * 5 + 10 - (level % 2);
        initializeMap();
        putHeroOnMap();
        putVillainsOnMap();
    }

    private void initializeMap(){
        theMap = new String[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                this.theMap[i][j] = EMPTY;
    }
    /** Put Hero in center of the map*/
    private void putHeroOnMap(){
        theMap[size / 2][size / 2] = HERO;
        heroPosition = new Point(size / 2, size / 2);
    }

    private void putVillainsOnMap(){
        Random random = new Random();

        int nrOfVillains = random.nextInt(4) + size;
        nrOfVillains = (size * size) - 3 * nrOfVillains;

        /** Create a Point with X, Y coordinates*/
        Point point = new Point();

        while (nrOfVillains > 0){
            point.setLocation(random.nextInt(size), random.nextInt(size));

            if (theMap[point.x][point.y].equals(EMPTY)) {
                int villainType = random.nextInt(VillainType.values().length);
                //theMap[point.x][point.y] = VillainType.values()[villainType].name();
                theMap[point.x][point.y] = VILLAIN;
                nrOfVillains--;
            }
        }
    }

    public void setOnMap(Point point, String str){
        theMap[point.x][point.y] = str;
    }

    public String getFromMap(Point point){
        return theMap[point.x][point.y];
    }

}
