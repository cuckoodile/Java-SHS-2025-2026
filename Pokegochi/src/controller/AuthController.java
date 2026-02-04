package controller;

import model.Player;
import util.DataStore;

public class AuthController {

    public static Player register(String username, String password) {
        if (DataStore.players.containsKey(username)) return null;
        Player player = new Player(username, password);
        DataStore.players.put(username, player);
        return player;
    }

    public static Player login(String username, String password) {
        Player player = DataStore.players.get(username);
        if (player != null && player.checkPassword(password)) {
            return player;
        }
        return null;
    }
}
