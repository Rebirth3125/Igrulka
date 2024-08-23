package Characters.HeroesClass;

import Characters.Enemies.Enemy;

public class Archer extends Hero {

    private static final int BASE_DAMAGE = 7;
    private static final int MULTIPLIER_DAMAGE = 2;
    private static final int CONCENTRATION_THRESHOLD = 3;
    private int attackCounter = 0;

    public Archer(String name) {
        super(name, 80, 0);
    }

    @Override
    public void attackEnemy(Enemy enemy) {
        incrementAttackCounter();

        if (isConcentrationAttack()) {
            performConcentratedAttack(enemy);
        } else {
            performBaseAttack(enemy);
        }

        if (enemy.isAlive()) {
            enemy.attackHero(this);
        }
    }

    private void incrementAttackCounter() {
        attackCounter++;
    }

    private boolean isConcentrationAttack() {
        return attackCounter % CONCENTRATION_THRESHOLD == 0;
    }

    private void performBaseAttack(Enemy enemy) {
        System.out.println(getClass().getSimpleName() + " атакует врага из лука, нанося " + BASE_DAMAGE + " урона");
        enemy.takeDamage(BASE_DAMAGE);
    }

    private void performConcentratedAttack(Enemy enemy) {
        int concentratedDamage = BASE_DAMAGE * MULTIPLIER_DAMAGE;
        System.out.println(getClass().getSimpleName() + " накапливает концентрацию и наносит удвоенный урон: " + concentratedDamage);
        enemy.takeDamage(concentratedDamage);
    }
}
