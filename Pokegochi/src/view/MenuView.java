package view;

import model.Player;
import model.Pokemon;
import util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MenuView {

    public static void showMainMenu() {
        System.out.println("\n=== POKEGOCHI ===");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. See Players");
        System.out.println("4. Exit");
    }

    public static void showGameMenu() {
        System.out.println("\n=== Game Menu ===");
        System.out.println("1. Eat");
        System.out.println("2. Play");
        System.out.println("3. Sleep");
        System.out.println("4. Work");
        System.out.println("5. Quit");
    }
    
    public static void showPokemonAdoption() {
        
    }
    
    public static void showAllPokemons() {
        try{
            Connection con = DBConnection.getConnection();
            
            con.prepareStatement(
            "SELECT * FROM pokemons"
            );
            
        } catch(SQLException e) {
            throw new Error(e);
        }
    }
    
    public static void showPokemonStats(Player player) {
        Pokemon p = player.getPokemon();

        System.out.println("\n=== Pokémon Status ===");
        System.out.println("Name: " + p.getNickname());
        System.out.println("Type: " + p.getType());
        System.out.println("Health: " + p.getHealth());
        System.out.println("Stamina: " + p.getStamina());
        System.out.println("Happiness: " + p.getHappiness());
        System.out.println("Hunger: " + p.getHunger());
        System.out.println("Gold: " + player.getGold());
    }

}
