package main.java.gameoflife.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@ToString
public class Animals extends TerrainPlayers {
    @Getter
    @Setter
    private Integer health;
    @Getter
    private List<Animals> eats;
    @Getter
    private Integer speed = 1;
    @Getter
    private Position closestPrey;

    public Animals() {
        super();
        this.health = 1;
        this.eats = new ArrayList<>();
    }

    public Animals(CharacterSymbol symbol, Integer health, List<Animals> eats) {
        super(symbol, new Position().toPosition(0,0));
        this.health = health > 0 ? health : 1;
        this.eats = eats;
    }

    public void seekClosestPray(Position closestPrey){
        this.closestPrey = closestPrey;
    }
}
