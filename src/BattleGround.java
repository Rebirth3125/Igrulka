public class BattleGround {
    public static void main(String[] args) {
//        Hero hero = new Warrior("Колыван");
        Hero hero = new Mage("Шаровой");
//        Hero hero = new Archer("Одноглазый снайпер");

        Enemy zombie = new Zombie(50);
        Enemy goblin = new Goblin(20);
        Enemy wolf = new Wolf(30);

        Enemy[] enemies = {zombie, goblin, wolf};

        System.out.println("=== БОЙ НАЧИНАЕТСЯ! ===");

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
    }
}