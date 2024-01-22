package horseracing;

import java.util.Scanner;

public class Player {
    private int wallet;
    private String playerName;
    private int playerNumber;

    public Player(int money, String name){
        wallet = money;
        playerName = name;
    }

    public int getWallet(){
        return wallet;
    }

    public String playerName(){
        return playerName;
    }

    public void setPlayerName(Scanner in){
       
        boolean isValid = false;
    
    }
}
