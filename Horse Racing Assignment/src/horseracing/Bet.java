package horseracing;

/* this class holds all the betting info including the player who placed the bet, the type of bet, the horse(s) the bet was 
    placed on which holds all the information about the horse and points to the horse class, and the bet amount.
 */

public class Bet {
    private int typeBet;
    private Player player;
    private Horse horse;
    private Horse horse1;
    private Horse horse2;
    private Horse horse3;
    private int betAmount;

    public int getTypeBet() {
        return typeBet;
    }

    public Horse getHorse() {
        return horse;
    }

    public Horse getHorse1() {
        return horse1;
    }

    public Horse getHorse2() {
        return horse2;
    }

    public Horse getHorse3() {
        return horse3;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public Player getPlayer(){
        return player;
    }

    // constructs win, place, and show bets
    public Bet(int typeBet, Horse horse, int betAmount, Player player){    
        this.typeBet = typeBet;
        this.horse = horse;
        this.betAmount = betAmount;
        this.player = player;
    }
    
    // constructs boxed exacta and exacta bets
    public Bet(int typeBet, Horse horse1, Horse horse2, int betAmount, Player player){     
        this.typeBet = typeBet;
        this.horse1 = horse1;
        this.horse2 = horse2;
        this.betAmount = betAmount;
        this.player = player;
    }

    // constructs boxed trifecta and trifecta bets
    public Bet(int typeBet, Horse horse1, Horse horse2, Horse horse3, int betAmount, Player player){
        this.typeBet = typeBet;
        this.horse1 = horse1;
        this.horse2 = horse2;
        this.horse3 = horse3;
        this.betAmount = betAmount;
        this.player = player;
    }
}
