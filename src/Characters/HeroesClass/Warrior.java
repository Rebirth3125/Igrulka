package Characters.HeroesClass;

import Characters.Enemies.Enemy;

public class Warrior extends Hero {

    private static final int ATTACK_DAMAGE = 10;
    private static final double DAMAGE_REDUCTION_PERCENT = 0.7;

    public Warrior(String name) {
        super(name, 80, 0);
    }

    @Override
    public void attackEnemy(Enemy enemy) {
        performAttack(enemy);
    }

    private void performAttack(Enemy enemy) {
        System.out.println(getClass().getSimpleName() + " атакует врага мечом, нанося " + Warrior.ATTACK_DAMAGE + " урона");
        enemy.takeDamage(Warrior.ATTACK_DAMAGE);

        if (enemy.isAlive()) {
            enemy.attackHero(this);
        }
    }

    @Override
    public void takeDamage(int damage) {
        int reducedDamage = calculateReducedDamage(damage);
        super.takeDamage(reducedDamage);
        System.out.println(getClass().getSimpleName() + " блокирует часть урона. Получено урона: " + reducedDamage);
    }

    private int calculateReducedDamage(int damage) {
        return (int) (damage * DAMAGE_REDUCTION_PERCENT);
    }
}
