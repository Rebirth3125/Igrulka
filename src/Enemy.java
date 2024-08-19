public class Enemy implements Mortal {
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
        if (health - damage < 0) {
            health = 0;
        } else {
            health -= damage;
        }
        System.out.println("Враг получает " + damage + " урона, здоровье врага: " + health);
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public void attackHero(Hero hero) {
        int damage = 5;
        System.out.println("Враг атакует " + hero.getName() + " и наносит " + damage + " урона");
        hero.takeDamage(damage);
    }
}
