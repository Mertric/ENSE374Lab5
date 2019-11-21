package main.java.gameoflife.model;

public enum CharacterSymbol {
    EMPTY(0),
    TREE(1),
    GRASS(2),
    GRASSHOPPER(3),
    CATERPILLAR(4),
    MOUSE(5),
    RABBIT(6),
    SQUIRREL(7),
    DEER(8),
    BLUEJAY(9),
    HAWK(10),
    FOX(11),
    WOLF(12);
    private int characterValue;

    CharacterSymbol(int characterValue) {
        this.characterValue = characterValue;
    }

    public int getCharacterValue() {
        return this.characterValue;
    }
}
