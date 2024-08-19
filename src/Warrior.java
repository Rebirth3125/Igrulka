public class Warrior extends Hero {

    public Warrior(String name) {
        super(name, 100);
    }

    @Override
    public void attackEnemy(Enemy enemy) {
        int damage = 10;
        System.out.println(name + " атакует врага мечом, нанося " + damage + " урона");
        enemy.takeDamage(damage);
        if (enemy.isAlive()) {
            enemy.attackHero(this);
        }
    }

    @Override
    public void takeDamage(int damage) {
        int reducedDamage = (int) (damage * 0.7);
        super.takeDamage(reducedDamage);
        System.out.println(name + " блокирует часть урона. Получено урона: " + reducedDamage);
    }
}
