package Characters.HeroesClass;

import Characters.Enemies.Enemy;

public class Mage extends Hero {

    private static final int BASE_DAMAGE = 5;
    private static final int SPELL_DAMAGE = 20;
    private static final int MANA_COST = 20;
    private static final int FIRE_SHIELD_COST = 15;
    private static final int FIRE_SHIELD_DURATION = 1;
    private static final int ARMAGEDDON_COST = 50;
    private static final int ARMAGEDDON_DAMAGE = 40;
    private static final int MANA_REGEN = 10;
    private static final int MANA_STORM_THRESHOLD = 10;
    private static final int MANA_STORM_RESTORE = 30;

    private boolean fireShieldActive = false;
    private int fireShieldTurnsRemaining = 0;

    public Mage(String name) {
        super(name, 50, 50); // Начальная мана 50
    }

    @Override
    public void attackEnemy(Enemy enemy) {
        if (fireShieldActive) {
            fireShieldTurnsRemaining--;
            if (fireShieldTurnsRemaining <= 0) {
                fireShieldActive = false;
                System.out.println(getClass().getSimpleName() + " огненный щит исчезает.");
            }
        }

        staffAttack(enemy);

        if (enemy.isAlive()) {
            enemy.attackHero(this);
        }

        regenerateMana(MANA_REGEN);
        checkManaStorm();
    }

    private void staffAttack(Enemy enemy) {
        System.out.println(getClass().getSimpleName() + " наносит обычный удар палкой, нанося " + BASE_DAMAGE + " урона.");
        enemy.takeDamage(BASE_DAMAGE);
    }

    private void regenerateMana(int amount) {
        setMana(Math.min(getMana() + amount, getMaxMana()));
        System.out.println(getClass().getSimpleName() + " восстанавливает " + amount + " маны, текущее количество маны: " + getMana());
    }

    public void castSpell(Enemy enemy) {
        if (getMana() >= MANA_COST) {
            System.out.println(getClass().getSimpleName() + " использует мощное заклинание, нанося " + SPELL_DAMAGE + " урона.");
            enemy.takeDamage(SPELL_DAMAGE);
            setMana(getMana() - MANA_COST);
        } else {
            System.out.println("Недостаточно маны для использования заклинания.");
        }
    }

    public void activateFireShield() {
        if (getMana() >= FIRE_SHIELD_COST) {
            fireShieldActive = true;
            fireShieldTurnsRemaining = FIRE_SHIELD_DURATION;
            setMana(getMana() - FIRE_SHIELD_COST);
            System.out.println(getClass().getSimpleName() + " активирует огненный щит, снижая входящий урон на 50% на один ход.");
        } else {
            System.out.println("Недостаточно маны для активации огненного щита.");
        }
    }

    public void castArmageddon(Enemy[] enemies) {
        if (getMana() >= ARMAGEDDON_COST) {
            System.out.println(getClass().getSimpleName() + " использует Армагеддон, нанося " + ARMAGEDDON_DAMAGE + " урона всем врагам!");
            for (Enemy enemy : enemies) {
                enemy.takeDamage(ARMAGEDDON_DAMAGE);
            }
            setMana(getMana() - ARMAGEDDON_COST);
        } else {
            System.out.println("Недостаточно маны для использования Армагеддона.");
        }
    }

    private void checkManaStorm() {
        if (getMana() < MANA_STORM_THRESHOLD) {
            System.out.println(getClass().getSimpleName() + " достигает критического уровня маны и запускает Мана Бурю!");
            setMana(MANA_STORM_RESTORE);
            System.out.println(getClass().getSimpleName() + " восстанавливает " + MANA_STORM_RESTORE + " маны.");
        }
    }

    @Override
    public void takeDamage(int damage) {
        if (fireShieldActive) {
            damage /= 2;
            System.out.println(getClass().getSimpleName() + " защищен огненным щитом, получено урона: " + damage);
        } else {
            System.out.println(getClass().getSimpleName() + " получает " + damage + " урона.");
        }
        super.takeDamage(damage);
    }
}
