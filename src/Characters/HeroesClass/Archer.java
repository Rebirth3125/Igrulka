package Characters.HeroesClass;

import Characters.Enemies.Enemy;

public class Archer extends Hero {

    public Archer(String name) {
        super(name, 80, 0);
    }

    @Override
    public void attackEnemy(Enemy enemy) {
        int damage = 7;
        System.out.println(getClass().getSimpleName() + " атакует врага из лука, нанося " + damage + " урона");
        enemy.takeDamage(damage);
        if (enemy.isAlive()) {
            enemy.attackHero(this);
        }
    }
}
