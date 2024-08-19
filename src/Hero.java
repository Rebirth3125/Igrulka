public abstract class Hero {
    protected String name;
    protected int health;
    protected int mana;
    protected int maxMana;

    public Hero(String name, int health, int maxMana) {
        this.name = name;
        this.health = health;
        this.maxMana = maxMana;
        this.mana = maxMana;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
        System.out.println(name + " получает " + damage + " урона, текущее здоровье: " + health);
    }

    public abstract void attackEnemy(Enemy enemy);

    protected void regenerateMana(int amount) {
        this.mana += amount;
        if (this.mana > maxMana) {
            this.mana = maxMana;
        }
        System.out.println(name + " восстанавливает " + amount + " маны, текущее количество маны: " + this.mana);
    }
}
