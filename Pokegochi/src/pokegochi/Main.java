/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pokegochi;

import java.util.Scanner;

import controller.*;
import model.*;
import view.MenuView;

/**
 *
 * @author Admin
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Player currentPlayer = null;

        while (true) {
            MenuView.showMainMenu();
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Username: ");
                String u = sc.nextLine();
                System.out.print("Password: ");
                String p = sc.nextLine();
                currentPlayer = AuthController.login(u, p);
            } else if (choice == 2) {
                System.out.print("Username: ");
                String u = sc.nextLine();
                System.out.print("Password: ");
                String p = sc.nextLine();
                currentPlayer = AuthController.register(u, p);
            } else if (choice == 3) {
                GameController.showAllPlayers();
                continue;
            } else {
                break;
            }
                
//            Check if there is a logged in player AND if the player doesn't have a pokemon yet
            if (currentPlayer != null && !currentPlayer.hasPokemon()) {
                System.out.println("Choose Pokemon: 1. Pikachu 2. Charmander");
                int pick = sc.nextInt();
                sc.nextLine();

                System.out.print("Nickname: ");
                String name = sc.nextLine();

                GameController.adoptPokemon(
                        currentPlayer,
                        pick == 1 ? PokemonType.PIKACHU : PokemonType.CHARMANDER,
                        name
                );
            }

            while (currentPlayer != null) {

                MenuView.showPokemonStats(currentPlayer);
                MenuView.showGameMenu();

                int action = sc.nextInt();
                Pokemon p = currentPlayer.getPokemon();

                switch (action) {
                    case 1 ->
                        p.eat(30, 10);
                    case 2 ->
                        p.play();
                    case 3 ->
                        p.sleep();
                    case 4 -> {
                        p.work();
                        currentPlayer.earnGold(50);
                    }
                    case 5 ->
                        currentPlayer = null;
                }

                if (currentPlayer != null && !GameController.checkPokemonStatus(currentPlayer)) {
                    currentPlayer = null;
                }
            }

        }
    }
}
