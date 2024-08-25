package Characters.HeroesClass;

import Characters.Enemies.Enemy;

public class Warrior extends Hero {

    private static final int ATTACK_DAMAGE = 10;
    private static final double DAMAGE_REDUCTION_PERCENT = 0.7;
    private static final int POWER_STRIKE_DAMAGE = 20;
    private static final int ULTIMATE_STRIKE_DAMAGE = 40;
    private static final int POWER_STRIKE_COOLDOWN = 3;
    private static final int RAGE_THRESHOLD = 30;

    private int powerStrikeCooldownCounter = 0;
    private int accumulatedRage = 0;
    private boolean powerStrikeActive = false;
    private boolean ultimateReady = false;

    public Warrior(String name) {
        super(name, 100, 0);
    }

    @Override
    public void attackEnemy(Enemy enemy) {
        if (powerStrikeActive && powerStrikeCooldownCounter == 0) {
            performPowerStrike(enemy);
        } else {
            performBasicAttack(enemy);
        }

        updateCooldowns();
    }

    private void performBasicAttack(Enemy enemy) {
        System.out.println(getClass().getSimpleName() + " атакует врага мечом, нанося " + ATTACK_DAMAGE + " урона");
        enemy.takeDamage(ATTACK_DAMAGE);
        if (enemy.isAlive()) {
            enemy.attackHero(this);
        }
    }

    private void performPowerStrike(Enemy enemy) {
        System.out.println(getClass().getSimpleName() + " использует Мощный Удар, нанося " + POWER_STRIKE_DAMAGE + " урона!");
        enemy.takeDamage(POWER_STRIKE_DAMAGE);
        powerStrikeActive = false;
        powerStrikeCooldownCounter = POWER_STRIKE_COOLDOWN;

        if (enemy.isAlive()) {
            enemy.attackHero(this);
        }
    }

    public void performUltimateStrike(Enemy enemy) {
        if (ultimateReady) {
            System.out.println(getClass().getSimpleName() + " использует Ультимативный Удар, нанося " + ULTIMATE_STRIKE_DAMAGE + " урона!");
            enemy.takeDamage(ULTIMATE_STRIKE_DAMAGE);
            ultimateReady = false;
            accumulatedRage = 0;

            if (enemy.isAlive()) {
                enemy.attackHero(this);
            }
        } else {
            System.out.println("Ультимативная способность ещё не готова. Нужно получить ещё " + (RAGE_THRESHOLD - accumulatedRage) + " урона.");
        }
    }

    public void activatePowerStrike() {
        if (powerStrikeCooldownCounter == 0) {
            powerStrikeActive = true;
            System.out.println(getClass().getSimpleName() + " готовится к Мощному Удару!");
        } else {
            System.out.println("Мощный Удар находится на перезарядке. Оставшихся ходов: " + powerStrikeCooldownCounter);
        }
    }

    @Override
    public void takeDamage(int damage) {
        int reducedDamage = calculateReducedDamage(damage);
        super.takeDamage(reducedDamage);
        System.out.println(getClass().getSimpleName() + " блокирует часть урона. Получено урона: " + reducedDamage);

        accumulateRage(reducedDamage);
    }

    private int calculateReducedDamage(int damage) {
        return (int) (damage * DAMAGE_REDUCTION_PERCENT);
    }

    private void updateCooldowns() {
        if (powerStrikeCooldownCounter > 0) {
            powerStrikeCooldownCounter--;
        }
    }

    private void accumulateRage(int damage) {
        if (!ultimateReady) {
            accumulatedRage += damage;
            if (accumulatedRage >= RAGE_THRESHOLD) {
                ultimateReady = true;
                System.out.println(getClass().getSimpleName() + " в ярости! Ультимативная способность готова к использованию.");
            } else {
                System.out.println(getClass().getSimpleName() + " накапливает ярость. Осталось набрать урона: " + (RAGE_THRESHOLD - accumulatedRage));
            }
        }
    }

    public boolean isPowerStrikeOnCooldown() {
        return powerStrikeCooldownCounter > 0;
    }

    public boolean isUltimateReady() {
        return ultimateReady;
    }

    public int getPowerStrikeCooldownCounter() {
        return powerStrikeCooldownCounter;
    }

    public int getRemainingRage() {
        return RAGE_THRESHOLD - accumulatedRage;
    }
}
