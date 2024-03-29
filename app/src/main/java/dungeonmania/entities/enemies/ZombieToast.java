package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.util.Position;

import java.util.Random;

public class ZombieToast extends Enemy {
    public static final double DEFAULT_HEALTH = 5.0;
    public static final double DEFAULT_ATTACK = 6.0;

    public Random getRandGen() {
        return randGen;
    }

    public void setRandGen(Random randGen) {
        this.randGen = randGen;
    }

    private Random randGen = new Random();

    private final MoveStrategy moveStrategy;

    public ZombieToast(Position position, double health, double attack) {

        super(position, health, attack);
        moveStrategy = new ZombieToastMoveStrategy();
    }

    @Override
    public void move(Game game) {
        moveStrategy.move(this, game);
    }
}
