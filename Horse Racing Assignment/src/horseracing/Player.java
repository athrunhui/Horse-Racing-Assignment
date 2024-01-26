package horseracing;

import java.util.ArrayList;
import java.util.List;

/* This class holds all the information of each player including the amount of money they have, their player name, their
    player number, and holds a list of all the bets they have placed in the event they place multiple bets.
    The updateWallet method updates the wallet when they place a bet or when they receive money after winning their bet.
    The removeBets method removes all the bets the player has made when the player chooses to play again.
    The addBets method adds the bets the player has placed to the bet list.

 */

public class Player {
    private int wallet;
    private String playerName;
    private int playerNumber;
    private List<Bet> bets;
    

    public Player(int money, String name, int numPlayer){
        wallet = money;
        playerName = name;
        playerNumber = numPlayer;
        this.bets = new ArrayList<Bet>();
    }

    public int getWallet(){
        return wallet;
    }

    public String getPlayerName(){
        return playerName;
    }

    public int getPlayerNumber(){
        return playerNumber;
    }

    public List<Bet> getPlayerBets(){
        return bets;
    }

    public void removeBets(){
        bets.removeAll(bets);
    }

    public void updateWallet(int money){
       wallet = money;
    }

    public void addBets(Bet newBet){
        bets.add(newBet);
    }
}
