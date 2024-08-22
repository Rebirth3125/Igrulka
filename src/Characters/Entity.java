package Characters;

import Other.Mortal;

public abstract class Entity implements Mortal {
    protected int health;

    public Entity(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
        System.out.println(getClass().getSimpleName() + " получает " + damage + " урона, текущее здоровье: " + health);
    }
}
