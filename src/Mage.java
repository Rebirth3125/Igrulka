public class Mage extends Hero {

    public Mage(String name) {
        super(name, 80, 20);
    }

    @Override
    public void attackEnemy(Enemy enemy) {
        if (mana >= 20) {
            int damage = 30;
            System.out.println(name + " использует мощное заклинание, нанося " + damage + " урона");
            enemy.takeDamage(damage);
            mana -= 20;
        } else {
            int damage = 5;
            System.out.println(name + " наносит обычный удар палкой, нанося " + damage + " урона");
            enemy.takeDamage(damage);
        }

        if (enemy.isAlive()) {
            enemy.attackHero(this);
        }

        regenerateMana(10);
    }

//    public void castSpell(Enemy enemy) {
//        System.out.println(name + " накладывает дополнительное заклинание, ослабляя врага!");
//        enemy.takeDamage(5);
//    }
}
