import Characters.HeroesClass.Hero;
import Characters.HeroesClass.Mage;
import Characters.HeroesClass.Warrior;
import Characters.HeroesClass.Archer;
import Characters.Enemies.Enemy;
import Characters.Enemies.Goblin;
import Characters.Enemies.Zombie;
import Characters.Enemies.Wolf;

import java.util.Scanner;

public class BattleGround {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            Hero hero = selectHero(scanner);

            Enemy[] enemies = createEnemies();

            System.out.println("\n=== БОЙ НАЧИНАЕТСЯ! ===");

            for (Enemy enemy : enemies) {
                startBattle(hero, enemy);

                if (!hero.isAlive()) {
                    System.out.println("\n=== " + hero.getName() + " пал в бою! ===");
                    break;
                }
            }

            System.out.println("\n=== БОЙ ЗАВЕРШЕН! ===");

            playAgain = askToPlayAgain(scanner);

        } while (playAgain);

        scanner.close();
    }

    private static void startBattle(Hero hero, Enemy enemy) {
        System.out.println("\n--- " + hero.getName() + " сталкивается с новым врагом: " + enemy.getClass().getSimpleName() + " ---\n");

        while (hero.isAlive() && enemy.isAlive()) {
            hero.attackEnemy(enemy);
            if (!enemy.isAlive()) {
                System.out.println("\n=== " + enemy.getClass().getSimpleName() + " повержен! ===");
            }
        }
    }

    private static Hero selectHero(Scanner scanner) {
        Hero hero = null;
        while (hero == null) {
            System.out.println("Выберите героя:");
            for (HeroType type : HeroType.values()) {
                System.out.println(type.getOptionNumber() + " - " + type.getDisplayName());
            }
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();
            hero = HeroType.getHeroByChoice(choice);
            if (hero == null) {
                System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
        return hero;
    }

    private static Enemy[] createEnemies() {
        Enemy zombie = new Zombie(50);
        Enemy goblin = new Goblin(20);
        Enemy wolf = new Wolf(30);

        return new Enemy[]{zombie, goblin, wolf};
    }

    private static boolean askToPlayAgain(Scanner scanner) {
        System.out.println("\nХотите сыграть снова? (1 - Да, 2 - Нет)");
        int choice = scanner.nextInt();
        return choice == 1;
    }

    private enum HeroType {
        WARRIOR(1, "Воин", new Warrior("Колыван")),
        MAGE(2, "Маг", new Mage("Шаровой")),
        ARCHER(3, "Лучник", new Archer("Одноглазый снайпер"));

        private final int optionNumber;
        private final String displayName;
        private final Hero hero;

        HeroType(int optionNumber, String displayName, Hero hero) {
            this.optionNumber = optionNumber;
            this.displayName = displayName;
            this.hero = hero;
        }

        public int getOptionNumber() {
            return optionNumber;
        }

        public String getDisplayName() {
            return displayName;
        }

        public Hero getHero() {
            return hero;
        }

        public static Hero getHeroByChoice(int choice) {
            for (HeroType type : values()) {
                if (type.getOptionNumber() == choice) {
                    return type.getHero();
                }
            }
            return null;
        }
    }
}
