package main.java.gameoflife.core;

import main.java.gameoflife.model.Animals;
import main.java.gameoflife.model.CharacterSymbol;
import main.java.gameoflife.model.Position;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Habitat {
    //. = empty , T = tree , G = grass , I = grasshopper , c = caterpillar , m = mouse , r = rabbit , s =squirrel , d = deer , B =bluejay , H = hawk , F = fox , W = wolf STARTING POSITIONS
    char[] input_terrain = {'.', 'T', 'G', 'I', 'C', 'M', 'R', 'S', 'D', 'B', 'H', 'F', 'W'};
    char[] output_terrain = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
    private int ROWS = 20;
    private int COLS = 60;

    private Position OVER_MAX_POSITION = new Position().toPosition(ROWS + 1, COLS + 1);

    private Integer chanceForProducers = 50;
    private Integer chanceForPrimaryConsumers = 30;
    private Integer chanceForSecondaryConsumers = 10;

    private static Double HUNTER_DISTANCE_TO_KILL = 3d;


    private static List<Animals> producers;
    private static List<Animals> primaryConsumers;
    private static List<Animals> secondaryConsumers;

    Animals[][] terrain = new Animals[ROWS][COLS];

    List<Animals> animalsInHabitat = new ArrayList<>();

    public Habitat() {

        producers = createProducers();
        primaryConsumers = createPrimaryConsumer();
        secondaryConsumers = createSecondaryConsumer();
        System.err.println(producers);
        System.err.println(primaryConsumers);
        System.err.println(secondaryConsumers);

        Random rand = new Random();


        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                terrain[r][c] = determineAnimalByDistribution(rand.nextInt());
            }
        }
        printHabitat();
    }

    public Habitat(String fileName) {
        String pathName = "C:\\Users\\allur\\IdeaProjects\\Habitat\\src\\main\\java\\resources\\HabitatFile.txt";
        File habitat = new File(pathName);


        if (!habitat.exists()) {
            System.out.println("File does not exist");
            System.exit(1);
        }
        if (!(habitat.isFile() && habitat.canRead())) {
            System.out.println(habitat.getName() + "Cannot be read");
        }
        try (FileInputStream fin = new FileInputStream(pathName)) {
            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    if (fin.available() > 0) {
                        this.terrain[r][c] = convertTextToCharacterSymbol((char) fin.read());
                    }
                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
        System.out.println("***Starting area***");
        printHabitat();
    }

//CHANGED TO PUBLIC FROM PRIVATE
    public void printHabitat() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (this.terrain[r][c] != null) {
                    System.out.print(this.terrain[r][c].getSymbol().getCharacterValue());
                } else {
                    System.out.print(CharacterSymbol.EMPTY.getCharacterValue());
                }
            }
            System.out.println();
        }
    }

    public void move() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (terrain[r][c] != null) {
                    Animals hunter = terrain[r][c];
                    hunter.setCurrentPosition(new Position().toPosition(r, c));
                    if (secondaryConsumers.contains(hunter)) {
                        hunter.seekClosestPray(findClosestPrey(hunter.getCurrentPosition(), primaryConsumers));
                        if (closeEnoughToKill(hunter.getClosestPrey(), hunter.getCurrentPosition())) {
                            terrain[hunter.getClosestPrey().getRow()][hunter.getClosestPrey().getCol()] = null;
                            hunter.setHealth(hunter.getHealth() + 1);
                        }
                    } else if (primaryConsumers.contains(hunter)) {
                        hunter.seekClosestPray(findClosestPrey(hunter.getCurrentPosition(), producers));
                        if (closeEnoughToKill(hunter.getClosestPrey(), hunter.getCurrentPosition())) {
                            terrain[hunter.getClosestPrey().getRow()][hunter.getClosestPrey().getCol()] = null;
                            hunter.setHealth(hunter.getHealth() + 1);
                        }
                    }
                       /* if (primaryConsumers.contains(hunter) || secondaryConsumers.contains(hunter)) {
                        Integer speed = hunter.getSpeed();
                        while (speed > 0 && (hunter.getCurrentPosition().getRow() + 1 < ROWS && hunter.getCurrentPosition().getRow() - 1 > 0)
                                && (hunter.getCurrentPosition().getCol() + 1 < COLS && hunter.getCurrentPosition().getCol() - 1 > 0)) {
                            //TODO make this move better
                                    int nextR = hunter.getClosestPrey().getRow();
                                    int nextC = hunter.getClosestPrey().getCol();
                                    hunter.setCurrentPosition(new Position().toPosition(nextR, nextC));
    //                           hunter.setCurrentPosition(new Position().toPosition());
                            animalMove(hunter.getCurrentPosition(), hunter.getClosestPrey());
                        }
                    }*/
                }
            }
        }
    }


    private void animalMove(Position animal, Position p) {
        Animals temp = terrain[animal.getRow()][animal.getCol()];
        terrain[animal.getRow()][animal.getCol()] = terrain[p.getRow()][p.getCol()];
        terrain[p.getRow()][p.getCol()] = temp;
    }

    private boolean closeEnoughToKill(Position hunter, Position prey) {
        return prey.calculateDistance(hunter) < HUNTER_DISTANCE_TO_KILL;
    }

    private void respawnSomeProducers() {

    }


    //get the closest prey to hunter
    private Position findClosestPrey(Position currentPosition, List<Animals> preys) {
        Position closestPrimaryConsumer = new Position();
        Double currentMax = Double.MAX_VALUE;
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (terrain[r][c] != null) {
                    if (preys.contains(terrain[r][c])) {
                        Double currentDistance = terrain[r][c].getCurrentPosition().calculateDistance(currentPosition);
                        if (currentDistance < currentMax) {
                            currentMax = currentDistance;
                            closestPrimaryConsumer = terrain[r][c].getCurrentPosition();
                        }
                    }
                }
            }
        }

        if (currentMax.equals(Double.MAX_VALUE)) {
            return currentPosition;
        }
        return closestPrimaryConsumer;
    }


    //convert text to Animals Object
    private Animals convertTextToCharacterSymbol(char ch) {
        switch (ch) {
            case ('T'):
                return CurrentAnimals.TREE.getAnimal();
            case ('G'):
                return CurrentAnimals.GRASS.getAnimal();
            case ('I'):
                return CurrentAnimals.GRASSHOPPER.getAnimal();
            case ('C'):
                return CurrentAnimals.CATERPILLAR.getAnimal();
            case ('M'):
                return CurrentAnimals.MOUSE.getAnimal();
            case ('R'):
                return CurrentAnimals.RABBIT.getAnimal();
            case ('S'):
                return CurrentAnimals.SQUIRREL.getAnimal();
            case ('D'):
                return CurrentAnimals.DEER.getAnimal();
            case ('B'):
                return CurrentAnimals.BLUEJAY.getAnimal();
            case ('H'):
                return CurrentAnimals.HAWK.getAnimal();
            case ('F'):
                return CurrentAnimals.FOX.getAnimal();
            case ('W'):
                return CurrentAnimals.WOLF.getAnimal();
            default:
                return null;
        }
    }


    //crete producers
    private List<Animals> createProducers() {
        List<Animals> producers = new ArrayList<>();
        producers.add(CurrentAnimals.GRASS.getAnimal());
        producers.add(CurrentAnimals.TREE.getAnimal());
        return producers;
    }


    //create primary consumers
    private List<Animals> createPrimaryConsumer() {
        List<Animals> primaryConsumers = new ArrayList<>();
        primaryConsumers.add(CurrentAnimals.MOUSE.getAnimal());
        primaryConsumers.add(CurrentAnimals.RABBIT.getAnimal());
        primaryConsumers.add(CurrentAnimals.CATERPILLAR.getAnimal());
        primaryConsumers.add(CurrentAnimals.DEER.getAnimal());
        primaryConsumers.add(CurrentAnimals.SQUIRREL.getAnimal());
        primaryConsumers.add(CurrentAnimals.BLUEJAY.getAnimal());
        return primaryConsumers;
    }


    //create secondary consumers
    private List<Animals> createSecondaryConsumer() {
        List<Animals> secondaryConsumers = new ArrayList<>();
        secondaryConsumers.add(CurrentAnimals.HAWK.getAnimal());
        secondaryConsumers.add(CurrentAnimals.WOLF.getAnimal());
        secondaryConsumers.add(CurrentAnimals.FOX.getAnimal());
        return secondaryConsumers;
    }

    //random chance to determine tile character
    private Animals determineAnimalByDistribution(Integer randomValue) {
        Random rand = new Random();
        randomValue = randomValue % 100;
        if (randomValue > chanceForProducers) {
            if (randomValue - chanceForProducers < 15) {
                return null;
            }
            return producers.get(Math.abs(rand.nextInt() % producers.size()));
        } else if (randomValue > chanceForPrimaryConsumers) {
            return primaryConsumers.get(Math.abs(rand.nextInt() % primaryConsumers.size()));
        } else if (randomValue > chanceForSecondaryConsumers) {
            return secondaryConsumers.get(Math.abs(rand.nextInt() % secondaryConsumers.size()));
        } else {
            return null;
        }
    }
}
