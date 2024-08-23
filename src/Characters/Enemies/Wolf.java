package Characters.Enemies;

import Characters.HeroesClass.Hero;

import java.util.Random;

public class Wolf extends Enemy {

    private static final int BASE_DAMAGE = 8;
    private static final int DOUBLE_ATTACK_CHANCE_PERCENT = 50;
    private final Random randomDoubleAttack;

    public Wolf(int health) {
        super(health);
        this.randomDoubleAttack = new Random();
    }

    @Override
    public void attackHero(Hero hero) {
        performAttack(hero);

        if (randomDoubleAttack.nextInt(100) < DOUBLE_ATTACK_CHANCE_PERCENT) {
            System.out.println("Волк атакует повторно!");
            performAttack(hero);
        }
    }

    public void performAttack(Hero hero) {
        System.out.println("Волк атакует " + hero.getName() + " и наносит " + Wolf.BASE_DAMAGE + " урона");
        hero.takeDamage(Wolf.BASE_DAMAGE);
    }
}
