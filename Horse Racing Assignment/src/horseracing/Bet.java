package horseracing;

public class Bet {
    private int typeBet;
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

    public int getReturnAmount() {
        return returnAmount;
    }

    private int returnAmount;

    public Bet(int typeBet, Horse horse, int betAmount){
        this.typeBet = typeBet;
        this.horse = horse;
        this.betAmount = betAmount;
    }
    
    public Bet(int typeBet, Horse horse1, Horse horse2, int betAmount){
        this.typeBet = typeBet;
        this.horse1 = horse1;
        this.horse2 = horse2;
        this.betAmount = betAmount;
    }

    public Bet(int typeBet, Horse horse1, Horse horse2, Horse horse3, int betAmount){
        this.typeBet = typeBet;
        this.horse1 = horse1;
        this.horse2 = horse2;
        this.horse3 = horse3;
        this.betAmount = betAmount;
    }
}
