public abstract class Enemy extends Entity {

    public Enemy(int health) {
        super(health);
    }

    public abstract void attackHero(Hero hero);

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
