public abstract class Hero extends Entity {
    protected String name;
    protected int mana;
    protected int maxMana;

    public Hero(String name, int health, int maxMana) {
        super(health);
        this.name = name;
        this.maxMana = maxMana;
        this.mana = maxMana;
    }

    public String getName() {
        return name;
    }

    public abstract void attackEnemy(Enemy enemy);
}
