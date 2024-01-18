package horseracing;

public class Bet {
    private int typeBet;
    private int horse;
    private int horse1;
    private int horse2;
    private int horse3;
    private int betAmount;

    public Bet(int typeBet, int horse, int betAmount){
        this.typeBet = typeBet;
        this.horse = horse;
        this.betAmount = betAmount;
    }
    
    public Bet(int typeBet, int horse1, int horse2, int betAmount){
        this.typeBet = typeBet;
        this.horse1 = horse1;
        this.horse2 = horse2;
        this.betAmount = betAmount;
    }

    public Bet(int typeBet, int horse1, int horse2, int horse3, int betAmount){
        this.typeBet = typeBet;
        this.horse1 = horse1;
        this.horse2 = horse2;
        this.horse3 = horse3;
        this.betAmount = betAmount;
    }
}
