import java.util.Random;

public class Zombie extends Enemy {
    private boolean hasRevived = false;

    public Zombie(int health) {
        super(health);
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (!isAlive() && !hasRevived) {
            Random random = new Random();
            if (random.nextInt(100) < 50) { // 50% reincarnation
                this.health = 20;
                hasRevived = true;
                System.out.println("Зомби воскресает с 20 единицами здоровья!");
            }
        }
    }

    @Override
    public void attackHero(Hero hero) {
        int damage = 7;
        System.out.println("Зомби атакует " + hero.getName() + " и наносит " + damage + " урона");
        hero.takeDamage(damage);
    }
}
