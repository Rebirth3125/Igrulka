package Characters.Enemies;

import Characters.HeroesClass.Hero;

import java.util.Random;

public class Goblin extends Enemy {

    public Goblin(int health) {
        super(health);
    }

    @Override
    public void takeDamage(int damage) {
        Random random = new Random();
        if (random.nextInt(100) < 50) { // 50% evasion
            System.out.println("Гоблин уклоняется от атаки!");
        } else {
            super.takeDamage(damage);
        }
    }

    @Override
    public void attackHero(Hero hero) {
        int damage = 6;
        System.out.println("Гоблин атакует " + hero.getName() + " и наносит " + damage + " урона");
        hero.takeDamage(damage);
    }
}
