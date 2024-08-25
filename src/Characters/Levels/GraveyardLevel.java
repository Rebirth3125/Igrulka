package Characters.Levels;

import Characters.Enemies.Zombie;
import Characters.Enemies.Wolf;

import java.util.Arrays;
import java.util.List;

public class GraveyardLevel {

    public static List<Location> createGraveyardLocations() {
        return Arrays.asList(
                new Location("Вход на кладбище", List.of(new Zombie(25))),
                new Location("Заброшенная часовня", Arrays.asList(new Zombie(20), new Zombie(15))),
                new Location("Склеп", List.of(new Wolf(10))),
                new Location("Старое кладбище", Arrays.asList(new Zombie(20), new Wolf(10))),
                new Location("Мавзолей", List.of(new Zombie(50))) // Этот участок будет иметь переход на Старую деревню
        );
    }
}
