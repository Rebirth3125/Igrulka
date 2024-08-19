public abstract class Hero implements Mortal {
    protected String name;
    protected int health;

    public Hero(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        if (health - damage < 0) {
            health = 0;
        } else {
            health -= damage;
        }
        System.out.println(name + " получает " + damage + " урона, текущее здоровье: " + health);
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    public abstract void attackEnemy(Enemy enemy);
}
