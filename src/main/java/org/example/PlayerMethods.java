package org.example;

import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class PlayerMethods {
    private final List<Player> players;

    public PlayerMethods(List<Player> players) {
        this.players = players;
    }
    public Player searchPlayerByName( String name) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(name)) {
                return player;
            }
        }
        return null;
    }

    public String getTop10ByRounds() {
        System.out.println("\n   TOP 10 JOGADORES QUE MAIS USARAM FLASHS   \n");
        players.stream()
                .sorted(Comparator.comparingInt(Player::getRounds).reversed())
                .limit(10)
                .forEach(player -> System.out.println("Player: " + player.getName() + ", Rounds: " + player.getRounds()));
        return null;
    }
    public String getTop10BySucess() {
        System.out.println("\n TOP 10 JOGADORES QUE MAIS TIVERA SUCESSO EM SUAS FLASH \n");
        players.stream()
                .sorted(Comparator.comparingDouble(Player::getSuccess).reversed())
                .limit(10)
                .forEach(player -> System.out.println(" Player: " + player.getName() + ", Rounds: " + player.getSuccess()));
        return null;
    }
}


