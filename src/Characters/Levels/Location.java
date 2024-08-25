package Characters.Levels;

import Characters.Enemies.Enemy;
import java.util.List;

public class Location {
    private final String name;
    private final List<Enemy> enemies;

    public Location(String name, List<Enemy> enemies) {
        this.name = name;
        this.enemies = enemies;
    }

    public String getName() {
        return name;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
}
