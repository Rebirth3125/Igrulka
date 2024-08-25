package Characters.Enemies;

import Characters.HeroesClass.Hero;

import java.util.Random;

public class Goblin extends Enemy {

    private static final int ATTACK_DAMAGE = 8;
    private static final int EVASION_CHANCE_PERCENT = 50;
    private final Random randomEvasion;

    public Goblin(int health) {
        super(health);
        this.randomEvasion = new Random();
    }

    @Override
    public void takeDamage(int damage) {
        if (attemptEvasion()) {
            System.out.println("Гоблин уклоняется от атаки!");
        } else {
            super.takeDamage(damage);
        }
    }

    @Override
    public void attackHero(Hero hero) {
        System.out.println(getClass().getSimpleName() + " атакует " + hero.getClass().getSimpleName() + " и наносит " + ATTACK_DAMAGE + " урона");
        hero.takeDamage(ATTACK_DAMAGE);
    }

    private boolean attemptEvasion() {
        return randomEvasion.nextInt(100) < EVASION_CHANCE_PERCENT;
    }
}
