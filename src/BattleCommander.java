import Characters.HeroesClass.Hero;
import Characters.HeroesClass.Mage;
import Characters.HeroesClass.Warrior;
import Characters.HeroesClass.Archer;
import Characters.Enemies.Enemy;

import java.util.Scanner;

public class BattleCommander {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Hero hero = selectHero(scanner);
        displayHeroStats(hero);

        Enemy[] enemies = createEnemies();

        System.out.println("\n=== БОЙ НАЧИНАЕТСЯ! ===");

        for (Enemy enemy : enemies) {
            displayEnemyStats(enemy);

            System.out.println("\n--- " + hero.getName() + " сталкивается с новым врагом: " + enemy.getClass().getSimpleName() + " ---\n");

            while (hero.isAlive() && enemy.isAlive()) {
                int action = selectAction(scanner, hero);
                performAction(hero, enemy, action);

                if (!enemy.isAlive()) {
                    System.out.println("\n=== " + enemy.getClass().getSimpleName() + " повержен! ===");
                }

                if (!hero.isAlive()) {
                    System.out.println("\n=== " + hero.getName() + " пал в бою! ===");
                    break;
                }
            }

            if (!hero.isAlive()) {
                break;
            }
        }

        System.out.println("\n=== БОЙ ЗАВЕРШЕН! ===");
        scanner.close();
    }

    private static Hero selectHero(Scanner scanner) {
        return BattleGround.selectHero(scanner);
    }

    private static Enemy[] createEnemies() {
        return BattleGround.createEnemies();
    }

    private static void displayHeroStats(Hero hero) {
        System.out.println("\n=== Характеристики вашего героя ===");
        System.out.println("Имя: " + hero.getName());
        System.out.println("Здоровье: " + hero.getHealth());

        if (hero instanceof Mage) {
            System.out.println("Мана: " + hero.getMana() + "/" + hero.getMaxMana());
        }
        System.out.println("===============================");
    }

    private static void displayEnemyStats(Enemy enemy) {
        System.out.println("\n=== Характеристики врага ===");
        System.out.println("Тип врага: " + enemy.getClass().getSimpleName());
        System.out.println("Здоровье: " + enemy.getHealth());
        System.out.println("===============================");
    }

    private static int selectAction(Scanner scanner, Hero hero) {
        System.out.println("\nВыберите действие:");
        System.out.println("1 - Атаковать врага");
        System.out.println("2 - Пропустить ход");

        if (hero instanceof Warrior warrior) {
            System.out.println("3 - Использовать Мощный Удар " + (warrior.isPowerStrikeOnCooldown() ? "(На перезарядке, ходов осталось: " + warrior.getPowerStrikeCooldownCounter() + ")" : "(Готово)"));
            System.out.println("4 - Использовать Ультимативный Удар " + (warrior.isUltimateReady() ? "(Готово)" : "(Осталось набрать ярости: " + warrior.getRemainingRage() + ")"));
        } else if (hero instanceof Mage) {
            System.out.println("\nТекущая мана: " + hero.getMana() + "/" + hero.getMaxMana());
            System.out.println("3 - Использовать Огненный шар (20 маны)");
            System.out.println("4 - Использовать Огненный щит (15 маны)");
            System.out.println("5 - Использовать Заклинание Армагеддон (50 маны)");
        } else if (hero instanceof Archer archer) {
            System.out.println("3 - Использовать способность 'Как ветер' " + (archer.isWindOnCooldown() ? "(На перезарядке, ходов осталось: " + archer.getWindCooldownCounter() + ")" : "(Готово)"));
            System.out.println("4 - Использовать Ультимативную способность 'Меткий выстрел' " + (archer.isUltimateReady() ? "(Готово)" : "(Осталось ударов: " + (Archer.getUltimateRequiredHits() - archer.getHitsToUltimate()) + ")"));
        }

        System.out.println("0 - Завершить попытку");
        System.out.print("Ваш выбор: ");
        return scanner.nextInt();
    }

    private static void performAction(Hero hero, Enemy enemy, int action) {
        switch (hero) {
            case Warrior warrior -> {
                switch (action) {
                    case 1:
                        hero.attackEnemy(enemy);
                        break;
                    case 2:
                        System.out.println(hero.getName() + " пропускает ход.");
                        if (enemy.isAlive()) {
                            enemy.attackHero(hero);
                        }
                        break;
                    case 3:
                        if (!warrior.isPowerStrikeOnCooldown()) {
                            warrior.activatePowerStrike();
                            warrior.attackEnemy(enemy);
                        } else {
                            System.out.println("Мощный удар на перезарядке. Попробуйте другое действие.");
                        }
                        break;
                    case 4:
                        if (warrior.isUltimateReady()) {
                            warrior.performUltimateStrike(enemy);
                        } else {
                            System.out.println("Ультимативный удар ещё не готов. Попробуйте другое действие.");
                        }
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        System.out.println("Неверный выбор, попробуйте снова.");
                        break;
                }
            }
            case Mage mage -> {
                switch (action) {
                    case 1:
                        hero.attackEnemy(enemy);
                        break;
                    case 2:
                        System.out.println(hero.getName() + " пропускает ход.");
                        if (enemy.isAlive()) {
                            enemy.attackHero(hero);
                        }
                        break;
                    case 3:
                        mage.castSpell(enemy);
                        break;
                    case 4:
                        mage.activateFireShield();
                        break;
                    case 5:
                        mage.castArmageddon(new Enemy[]{enemy});
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        System.out.println("Неверный выбор, попробуйте снова.");
                        break;
                }
            }
            case Archer archer -> {
                switch (action) {
                    case 1:
                        hero.attackEnemy(enemy);
                        break;
                    case 2:
                        System.out.println(hero.getName() + " пропускает ход.");
                        if (enemy.isAlive()) {
                            enemy.attackHero(hero);
                        }
                        break;
                    case 3:
                        if (!archer.isWindOnCooldown()) {
                            archer.activateWind();
                        } else {
                            System.out.println("Способность 'Как ветер' на перезарядке. Попробуйте другое действие.");
                        }
                        break;
                    case 4:
                        if (archer.isUltimateReady()) {
                            archer.activateUltimate(enemy);
                        } else {
                            System.out.println("Ультимативная способность ещё не готова. Попробуйте другое действие.");
                        }
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        System.out.println("Неверный выбор, попробуйте снова.");
                        break;
                }
            }
            case null, default -> {
                switch (action) {
                    case 1:
                        assert hero != null;
                        hero.attackEnemy(enemy);
                        break;
                    case 2:
                        assert hero != null;
                        System.out.println(hero.getName() + " пропускает ход.");
                        if (enemy.isAlive()) {
                            enemy.attackHero(hero);
                        }
                        break;
                    default:
                        System.out.println("Неверный выбор, попробуйте снова.");
                        break;
                }
            }
        }
    }
}
