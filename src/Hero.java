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

    protected void regenerateMana(int amount) {
        this.mana += amount;
        if (this.mana > maxMana) {
            this.mana = maxMana;
        }
        System.out.println(getClass().getSimpleName() + " восстанавливает " + amount + " маны, текущее количество маны: " + this.mana);
    }
}
