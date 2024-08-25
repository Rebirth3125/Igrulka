package Characters.Levels;

import Characters.Enemies.Goblin;
import Characters.Enemies.Wolf;
import Characters.Enemies.Zombie;

import java.util.Arrays;
import java.util.List;

public class OldVillageLevel {

    public static List<Location> createOldVillageLocations() {
        return Arrays.asList(
                new Location("Центральная площадь", List.of(new Goblin(15))),
                new Location("Заброшенные дома", Arrays.asList(new Goblin(15), new Wolf(10))),
                new Location("Старый рынок", List.of(new Zombie(25))),
                new Location("Разрушенная церковь", Arrays.asList(new Goblin(20), new Zombie(50))),
                new Location("Деревенская стена", List.of(new Wolf(20))) // Этот участок будет иметь переход на финальный уровень
        );
    }
}
