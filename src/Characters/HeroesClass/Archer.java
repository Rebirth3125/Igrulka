package Characters.HeroesClass;

import Characters.Enemies.Enemy;

public class Archer extends Hero {
    // Пассивная способность: Каждый третий выстрел лучника наносит 3‑х урон.
    private static final int BASE_DAMAGE = 8;
    private static final int MULTIPLIER_DAMAGE = 3;
    private static final int CONCENTRATION_THRESHOLD = 3;
    private int attackCounter = 0;

    // Активная способность: Даёт 50% шанс уклониться от атаки врага на 2 хода.
    private boolean windActive = false;
    private int windCooldown = 0;
    private int windDuration = 0;
    private static final int WIND_DURATION_LIMIT = 2;
    private static final int WIND_COOLDOWN_LIMIT = 4;

    // Ультимативная способность: Каждый 5 выстрел можно использовать "Меткий выстрел" - атака наносит 5-х урон.
    private boolean ultimateReady = false;
    private static final int ULTIMATE_REQUIRED_HITS = 5;
    private int hitsToUltimate = 0;

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

        incrementHitsForUltimate();

        if (enemy.isAlive()) {
            enemy.attackHero(this);
        }

        updateWindState();
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
        System.out.println(getClass().getSimpleName() + " накапливает концентрацию и наносит утроенный урон: " + concentratedDamage);
        enemy.takeDamage(concentratedDamage);
    }

    public void activateWind() {
        if (windCooldown == 0) {
            windActive = true;
            windDuration = WIND_DURATION_LIMIT;
            windCooldown = WIND_COOLDOWN_LIMIT;
            System.out.println(getName() + " активирует способность 'Как ветер'! Шанс уклонения увеличен на 50% на " + WIND_DURATION_LIMIT + " хода.");
        } else {
            System.out.println("Способность 'Как ветер' на перезарядке, осталось " + windCooldown + " ходов.");
        }
    }

    private void updateWindState() {
        if (windActive) {
            windDuration--;
            if (windDuration <= 0) {
                windActive = false;
                System.out.println("Способность 'Как ветер' закончила свое действие.");
            }
        }

        if (windCooldown > 0) {
            windCooldown--;
        }
    }

    public boolean canDodge() {
        if (windActive) {
            return Math.random() < 0.5; // 50% шанс уклониться
        }
        return false;
    }

    public void activateUltimate(Enemy enemy) {
        if (ultimateReady) {
            int ultimateDamage = BASE_DAMAGE * 5;
            System.out.println(getName() + " использует ультимативную способность 'Меткий выстрел', нанося " + ultimateDamage + " урона!");
            enemy.takeDamage(ultimateDamage);
            resetUltimate();
        } else {
            System.out.println("Ультимативная способность ещё не готова, необходимо нанести " + (ULTIMATE_REQUIRED_HITS - hitsToUltimate) + " ударов.");
        }
    }

    private void incrementHitsForUltimate() {
        hitsToUltimate++;
        if (hitsToUltimate >= ULTIMATE_REQUIRED_HITS) {
            ultimateReady = true;
            System.out.println(getName() + " заряжает ультимативную способность 'Меткий выстрел'!");
        }
    }

    private void resetUltimate() {
        ultimateReady = false;
        hitsToUltimate = 0;
    }

    @Override
    public void takeDamage(int damage) {
        if (canDodge()) {
            System.out.println(getName() + " уклоняется от атаки!");
        } else {
            super.takeDamage(damage);
        }
    }

    public static int getUltimateRequiredHits() {
        return ULTIMATE_REQUIRED_HITS;
    }

    public int getHitsToUltimate() {
        return hitsToUltimate;
    }

    public boolean isWindOnCooldown() {
        return windCooldown > 0;
    }

    public int getWindCooldownCounter() {
        return windCooldown;
    }

    public boolean isUltimateReady() {
        return ultimateReady;
    }
}
