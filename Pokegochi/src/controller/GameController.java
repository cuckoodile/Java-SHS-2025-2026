package controller;

import model.*;
import util.RandomUtil;
import util.DataStore;


public class GameController {

    public static void adoptPokemon(Player player, PokemonType type, String nickname) {
        Pokemon pokemon = new Pokemon(type, nickname);
        int gold = RandomUtil.range(type.minGold, type.maxGold);
        player.adoptPokemon(pokemon, gold);
    }

    public static boolean checkPokemonStatus(Player player) {
        if (player.getPokemon().isDead()) {
            System.out.println("Your Pokémon has died...");
            return false;
        }
        return true;
    }
    
    public static void showAllPlayers() {
        System.out.println("\n=== Registered Players ===");

        if (DataStore.players.isEmpty()) {
            System.out.println("No players registered.");
            return;
        }

        for (Player player : DataStore.players.values()) {
            System.out.print("Username: " + player.getUsername());

            if (player.hasPokemon()) {
                Pokemon p = player.getPokemon();
                System.out.println(" | Pokémon: " + p.getType() +
                                   " (" + p.getNickname() + ")");
            } else {
                System.out.println(" | No Pokémon adopted");
            }
        }
    }

}
