public class Mage extends Hero {

    public Mage(String name) {
        super(name, 80);
    }

    @Override
    public void attackEnemy(Enemy enemy) {
        int damage = 15;
        System.out.println(name + " атакует врага заклинанием, нанося " + damage + " урона");
        enemy.takeDamage(damage);
        if (enemy.isAlive()) {
            enemy.attackHero(this);
        }
    }

    public void castSpell(Enemy enemy) {
        System.out.println(name + " накладывает заклинание, ослабляя врага!");
        enemy.takeDamage(5);
    }
}
