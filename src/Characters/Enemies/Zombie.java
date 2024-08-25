package Characters.Enemies;

import Characters.HeroesClass.Hero;

import java.util.Random;

public class Zombie extends Enemy {

    private static final int REVIVAL_HEALTH = 20;
    private static final int ATTACK_DAMAGE = 7;
    private static final int REVIVAL_CHANCE_PERCENT = 50;

    private boolean hasRevived = false;
    private final Random randomReincarnation;

    public Zombie(int health) {
        super(health);
        this.randomReincarnation = new Random();
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        attemptRevival();
    }

    private void attemptRevival() {
        if (!isAlive() && !hasRevived) {
            if (randomReincarnation.nextInt(100) < REVIVAL_CHANCE_PERCENT) {
                revive();
            }
        }
    }

    private void revive() {
        this.health = REVIVAL_HEALTH;
        hasRevived = true;
        System.out.println(getClass().getSimpleName() + " воскресает с " + REVIVAL_HEALTH + " единицами здоровья!");
    }

    @Override
    public void attackHero(Hero hero) {
        System.out.println(getClass().getSimpleName() + " атакует " + hero.getClass().getSimpleName() + " и наносит " + ATTACK_DAMAGE + " урона");
        hero.takeDamage(ATTACK_DAMAGE);
    }
}
