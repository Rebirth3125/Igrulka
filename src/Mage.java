public class Mage extends Hero {

    public Mage(String name) {
        super(name, 80, 20);
    }

    @Override
    public void attackEnemy(Enemy enemy) {
        if (mana >= 20) {
            int damage = 30;
            System.out.println(getClass().getSimpleName() + " использует мощное заклинание, нанося " + damage + " урона");
            enemy.takeDamage(damage);
            mana -= 20;
        } else {
            int damage = 5;
            System.out.println(getClass().getSimpleName() + " наносит обычный удар палкой, нанося " + damage + " урона");
            enemy.takeDamage(damage);
        }

        if (enemy.isAlive()) {
            enemy.attackHero(this);
        }

        regenerateMana(10);
    }

    public void regenerateMana(int amount) {
        this.mana += amount;
        if (this.mana > maxMana) {
            this.mana = maxMana;
        }
        System.out.println(getClass().getSimpleName() + " восстанавливает " + amount + " маны, текущее количество маны: " + this.mana);
    }
}
