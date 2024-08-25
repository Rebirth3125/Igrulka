import Characters.Enemies.Enemy;
import Characters.HeroesClass.Hero;
import Characters.HeroesClass.Mage;
import Characters.HeroesClass.Warrior;
import Characters.HeroesClass.Archer;
import Characters.Levels.Level;
import Characters.Levels.Location;
import Characters.Levels.ForestLevel;
import Characters.Levels.GraveyardLevel;
import Characters.Levels.OldVillageLevel;

import java.util.List;
import java.util.Scanner;

public class Game {

    private static Level currentLevel;
    private static Hero hero;
    private static boolean[] locationsStatus;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        hero = selectHero(scanner);
        displayHeroStats(hero);

        List<Location> forestLocations = ForestLevel.createForestLocations();
        List<Location> graveyardLocations = GraveyardLevel.createGraveyardLocations();
        List<Location> oldVillageLocations = OldVillageLevel.createOldVillageLocations();

        currentLevel = new Level("Лес", forestLocations);
        locationsStatus = new boolean[forestLocations.size()];

        while (currentLevel != null) {
            System.out.println("\nВы находитесь на уровне: " + currentLevel.getName());

            List<Location> locations = currentLevel.getLocations();
            displayLocations(locations);

            System.out.println("Выберите участок или введите 0 для завершения игры: ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                break;
            } else if (choice > 0 && choice <= locations.size()) {
                if (choice > 1 && !locationsStatus[choice - 2]) {
                    System.out.println("Вы должны пройти предыдущий участок: " + locations.get(choice - 2).getName());
                } else if (locationsStatus[choice - 1]) {
                    System.out.println("Вы уже прошли этот участок.");
                } else {
                    Location selectedLocation = locations.get(choice - 1);
                    System.out.println("\nВы выбрали: " + selectedLocation.getName());

                    fightEnemies(selectedLocation.getEnemies(), scanner);

                    if (!hero.isAlive()) {
                        System.out.println("\n=== Игра завершена! Герой погиб. ===");
                        break;
                    } else {
                        locationsStatus[choice - 1] = true;
                    }
                }
            }

            if (allLocationsCompleted(locationsStatus)) {
                if (currentLevel.getName().equals("Лес")) {
                    currentLevel = new Level("Кладбище", graveyardLocations);
                    locationsStatus = new boolean[graveyardLocations.size()];
                } else if (currentLevel.getName().equals("Кладбище")) {
                    currentLevel = new Level("Старая деревня", oldVillageLocations);
                    locationsStatus = new boolean[oldVillageLocations.size()];
                } else if (currentLevel.getName().equals("Старая деревня")) {
                    System.out.println("\n=== Поздравляем! Вы прошли игру! ===");
                    break;
                }
            }
        }

        scanner.close();
    }

    private static void displayLocations(List<Location> locations) {
        for (int i = 0; i < locations.size(); i++) {
            String status = locationsStatus[i] ? "(Пройдено)" : (i > 0 && !locationsStatus[i - 1]) ? "(Недоступно)" : "";
            System.out.println((i + 1) + ". " + locations.get(i).getName() + " " + status);
        }
    }

    private static boolean allLocationsCompleted(boolean[] locationsStatus) {
        for (boolean status : locationsStatus) {
            if (!status) {
                return false;
            }
        }
        return true;
    }

    private static void fightEnemies(List<Enemy> enemies, Scanner scanner) {
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
    }

    private static Hero selectHero(Scanner scanner) {
        return BattleGround.selectHero(scanner);
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
