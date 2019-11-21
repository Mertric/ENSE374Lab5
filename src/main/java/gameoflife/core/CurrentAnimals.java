package main.java.gameoflife.core;

import lombok.Value;
import main.java.gameoflife.model.Animals;
import main.java.gameoflife.model.CharacterSymbol;
import main.java.gameoflife.model.Position;

@Value
public class CurrentAnimals {
    public static final CurrentAnimals TREE = new CurrentAnimals(Animals.builder()
            .symbol(CharacterSymbol.TREE)
            .currentPosition(new Position().toPosition(0, 0))
            .speed(-1)
            .health(1)
            .build());
    public static final CurrentAnimals GRASS = new CurrentAnimals(Animals.builder()
            .symbol(CharacterSymbol.GRASS)
            .currentPosition(new Position().toPosition(0, 0))
            .speed(-1)
            .health(1)
            .build());
    public static final CurrentAnimals GRASSHOPPER = new CurrentAnimals(Animals.builder()
            .symbol(CharacterSymbol.GRASSHOPPER)
            .currentPosition(new Position().toPosition(0, 0))
            .health(1)
            .build());
    public static final CurrentAnimals CATERPILLAR = new CurrentAnimals(Animals.builder()
            .symbol(CharacterSymbol.CATERPILLAR)
            .currentPosition(new Position().toPosition(0, 0))
            .health(1)
            .build());
    public static final CurrentAnimals MOUSE = new CurrentAnimals(Animals.builder()
            .symbol(CharacterSymbol.MOUSE)
            .currentPosition(new Position().toPosition(0, 0))
            .health(1)
            .build());
    public static final CurrentAnimals RABBIT = new CurrentAnimals(Animals.builder()
            .symbol(CharacterSymbol.RABBIT)
            .currentPosition(new Position().toPosition(0, 0))
            .health(1)
            .build());
    public static final CurrentAnimals SQUIRREL = new CurrentAnimals(Animals.builder()
            .symbol(CharacterSymbol.SQUIRREL)
            .currentPosition(new Position().toPosition(0, 0))
            .health(1)
            .build());
    public static final CurrentAnimals DEER = new CurrentAnimals(Animals.builder()
            .symbol(CharacterSymbol.DEER)
            .currentPosition(new Position().toPosition(0, 0))
            .health(1)
            .build());
    public static final CurrentAnimals BLUEJAY = new CurrentAnimals(Animals.builder()
            .symbol(CharacterSymbol.BLUEJAY)
            .currentPosition(new Position().toPosition(0, 0))
            .health(1)
            .build());
    public static final CurrentAnimals HAWK = new CurrentAnimals(Animals.builder()
            .symbol(CharacterSymbol.HAWK)
            .currentPosition(new Position().toPosition(0, 0))
            .health(1)
            .build());
    public static final CurrentAnimals FOX = new CurrentAnimals(Animals.builder()
            .symbol(CharacterSymbol.FOX)
            .currentPosition(new Position().toPosition(0, 0))
            .health(1)
            .build());
    public static final CurrentAnimals WOLF = new CurrentAnimals(Animals.builder()
            .symbol(CharacterSymbol.WOLF)
            .currentPosition(new Position().toPosition(0, 0))
            .health(1)
            .build());

    private final Animals animal;

    public CurrentAnimals(Animals animal) {
        this.animal = animal;
    }

    public Animals getValue() {
        return animal;
    }

    @Override
    public String toString() {
        return animal.toString();
    }
}