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
        Hero hero = null;

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
                System.out.println("Неверный выбор, по умолчанию выбран Воин.");
                hero = new Warrior("Колыван");
                break;
        }

        Enemy zombie = new Zombie(50);
        Enemy goblin = new Goblin(20);
        Enemy wolf = new Wolf(30);

        Enemy[] enemies = {zombie, goblin, wolf};

        System.out.println("\n=== БОЙ НАЧИНАЕТСЯ! ===");

        for (Enemy enemy : enemies) {
            System.out.println("\n--- " + hero.getName() + " сталкивается с новым врагом: " + enemy + " ---\n");

            while (hero.isAlive() && enemy.isAlive()) {
                hero.attackEnemy(enemy);
                if (!enemy.isAlive()) {
                    System.out.println("\n=== " + enemy + " повержен! ===");
                }
                if (!hero.isAlive()) {
                    System.out.println("\n=== " + hero.getName() + " пал в бою! ===");
                    break;
                }
            }

            if (hero.isAlive() && enemy.isAlive()) {
                System.out.println("\n--------------------\n");
            }

            if (!hero.isAlive()) {
                break;
            }
        }

        System.out.println("\n=== БОЙ ЗАВЕРШЕН! ===");
        scanner.close();
    }
}
