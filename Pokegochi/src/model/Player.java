package model;

public class Player {
    private String username;
    private String password;
    private Pokemon pokemon;
    private int gold;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean hasPokemon() {
        return pokemon != null;
    }

    public void adoptPokemon(Pokemon pokemon, int initialGold) {
        this.pokemon = pokemon;
        this.gold = initialGold;
    }

    public void earnGold(int amount) {
        gold += amount;
    }

    public void spendGold(int amount) {
        gold -= amount;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public int getGold() {
        return gold;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String input) {
        return password.equals(input);
    }
}
