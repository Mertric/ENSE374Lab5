package main.java.gameoflife.model;

import javafx.geometry.Pos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class TerrainPlayers {
    @Getter
    private CharacterSymbol symbol;
    @Getter
    @Setter
    private Position currentPosition;

    public TerrainPlayers() {
        this.symbol = CharacterSymbol.EMPTY;
        this.currentPosition = new Position()
                .toPosition(0,0);
    }

    public TerrainPlayers(CharacterSymbol symbol) {
        this.symbol = symbol;
        this.currentPosition = new Position()
                .toPosition(0,0);
    }

    public TerrainPlayers(CharacterSymbol symbol, Position position) {
        this.symbol = symbol;
        this.currentPosition = position;
    }

    @Override
    public boolean equals(Object obj) {
        return ((TerrainPlayers)(obj)).symbol.equals(this.symbol);
    }
}
