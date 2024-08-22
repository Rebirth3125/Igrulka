package Characters.HeroesClass;

import Characters.Enemies.Enemy;

public class Warrior extends Hero {

    public Warrior(String name) {
        super(name, 80, 0);
    }

    @Override
    public void attackEnemy(Enemy enemy) {
        int damage = 10;
        System.out.println(getClass().getSimpleName() + " атакует врага мечом, нанося " + damage + " урона");
        enemy.takeDamage(damage);
        if (enemy.isAlive()) {
            enemy.attackHero(this);
        }
    }

    @Override
    public void takeDamage(int damage) {
        int reducedDamage = (int) (damage * 0.7);
        super.takeDamage(reducedDamage);
        System.out.println(getClass().getSimpleName() + " блокирует часть урона. Получено урона: " + reducedDamage);
    }
}
