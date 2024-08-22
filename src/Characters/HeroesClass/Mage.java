package Characters.HeroesClass;

import Characters.Enemies.Enemy;

public class Mage extends Hero {

    private static final int BASE_DAMAGE = 5;
    private static final int SPELL_DAMAGE = 30;
    private static final int MANA_COST = 20;
    private static final int MANA_REGEN = 10;

    public Mage(String name) {
        super(name, 80, 20);
    }

    @Override
    public void attackEnemy(Enemy enemy) {
        if (canCastSpell()) {
            castSpell(enemy);
        } else {
            staffAttack(enemy);
        }

        if (enemy.isAlive()) {
            enemy.attackHero(this);
        }

        regenerateMana(MANA_REGEN);
    }

    private boolean canCastSpell() {
        return getMana() >= MANA_COST;
    }

    private void castSpell(Enemy enemy) {
        System.out.println(getClass().getSimpleName() + " использует мощное заклинание, нанося " + SPELL_DAMAGE + " урона");
        enemy.takeDamage(SPELL_DAMAGE);
        setMana(getMana() - MANA_COST);
    }

    private void staffAttack(Enemy enemy) {
        System.out.println(getClass().getSimpleName() + " наносит обычный удар палкой, нанося " + BASE_DAMAGE + " урона");
        enemy.takeDamage(BASE_DAMAGE);
    }

    private void regenerateMana(int amount) {
        setMana(Math.min(getMana() + amount, getMaxMana()));
        System.out.println(getClass().getSimpleName() + " восстанавливает " + amount + " маны, текущее количество маны: " + getMana());
    }
}
