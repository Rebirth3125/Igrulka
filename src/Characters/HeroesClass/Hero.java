package Characters.HeroesClass;

import Characters.Enemies.Enemy;
import Characters.Entity;

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

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public String getName() {
        return name;
    }

    public abstract void attackEnemy(Enemy enemy);
}
