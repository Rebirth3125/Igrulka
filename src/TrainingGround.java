import Characters.Enemies.Enemy;
import Characters.Enemies.Goblin;
import Characters.HeroesClass.Archer;
import Characters.HeroesClass.Hero;
import Characters.HeroesClass.Mage;
import Characters.HeroesClass.Warrior;

public class TrainingGround {
    public static void main(String[] args){
        Enemy enemy = new Goblin(20);

        Hero warrior = new Warrior("Колыван");
        warrior.attackEnemy(enemy);
        Hero mage = new Mage("Шаровой");
        mage.attackEnemy(enemy);
        Hero archer = new Archer("Одноглазый снайпер");
        archer.attackEnemy(enemy);

        if (!enemy.isAlive()) {
            System.out.println(enemy + " побежден!");
        } else {
            System.out.println(enemy + " остался в живых!");
        }
    }
}