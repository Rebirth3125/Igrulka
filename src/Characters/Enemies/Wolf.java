package Characters.Enemies;

import Characters.HeroesClass.Hero;

public class Wolf extends Enemy {

    public Wolf(int health) {
        super(health);
    }

    @Override
    public void attackHero(Hero hero) {
        int damage = 8;
        System.out.println("Волк атакует " + hero.getName() + " и наносит " + damage + " урона");
        hero.takeDamage(damage);

        System.out.println("Волк атакует повторно!"); // Double тычка
        hero.takeDamage(damage);
    }
}
