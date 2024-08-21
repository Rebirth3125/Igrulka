public abstract class Enemy implements Mortal {
    protected int health;

    public Enemy(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
        System.out.println(getClass().getSimpleName() + " получает " + damage + " урона, текущее здоровье: " + health);
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    public abstract void attackHero(Hero hero);

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
