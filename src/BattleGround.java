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
                System.out.println("\n--- " + hero.getName() + " сталкивается с новым врагом: " + enemy.getClass().getSimpleName() + " ---\n");

                while (hero.isAlive() && enemy.isAlive()) {
                    hero.attackEnemy(enemy);
                    if (!enemy.isAlive()) {
                        System.out.println("\n=== " + enemy.getClass().getSimpleName() + " повержен! ===");
                    }
                    if (!hero.isAlive()) {
                        System.out.println("\n=== " + hero.getName() + " пал в бою! ===");
                        break;
                    }
                }

                if (hero.isAlive()) {
                    System.out.println("\n--------------------\n");
                } else {
                    break;
                }
            }

            System.out.println("\n=== БОЙ ЗАВЕРШЕН! ===");

            playAgain = askToPlayAgain(scanner);

        } while (playAgain);

        scanner.close();
    }

    private static Hero selectHero(Scanner scanner) {
        Hero hero = null;
        while (hero == null) {
            System.out.println("Выберите героя:");
            System.out.println("1 - Воин");
            System.out.println("2 - Маг");
            System.out.println("3 - Лучник");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hero = new Warrior("Колыван");
                    break;
                case 2:
                    hero = new Mage("Шаровой");
                    break;
                case 3:
                    hero = new Archer("Одноглазый снайпер");
                    break;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
                    break;
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
}
