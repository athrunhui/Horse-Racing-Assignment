package horseracing;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Race {
    private List<Horse> horses;
    private List<Bet> bets;
    private double raceLength; // in furlongs
    private String raceSurface; // "grass", "dirt", or "mud" (Uses HorseRacingHelper constants)
    private int currentHorse;
    private int currentBet;
    private String spaceM;
    private String spaceG;
    private String spaceD;
    private String spaceL;
    private int betAmt = 0;
    private int returnAmt;
    private int wallet = 20;
    private int betType;
    private Bet Bet;
    private String result;
    private Scanner in = new Scanner(System.in);
    
    private List<Horse> results;

    public Race(List<Horse> horses, double raceLength, String raceSurface) {
        this.horses = horses;
        this.raceLength = raceLength;
        this.raceSurface = raceSurface;
        this.currentHorse = 0;
        this.currentBet = 0;
        this.results = new ArrayList<Horse>();
        this.bets = new ArrayList<Bet>();
    }

    public void checkBets(){
        for (Bet bet : bets) {
            if (bet.getTypeBet() == 1){
                if (bet.getHorse().equals(results.get(0))){
                  returnAmt = bet.getBetAmount() * results.get(0).getWinOdds();
                  wallet += returnAmt;
                  System.out.println("Your win bet won $" + returnAmt);                
                } else{                
                    System.out.println("You lost your $" + bet.getBetAmount() + " win bet on " +
                    bet.getHorse());
                }
            }

            else if(bet.getTypeBet() == 2){
                if(bet.getHorse().equals(results.get(0)) || bet.getHorse().equals(results.get(1))){
                  returnAmt = bet.getBetAmount() * results.get(0).getPlaceOdds();
                  wallet += returnAmt;
                  System.out.println("Your place bet won $" + returnAmt); 
                } else {
                    System.out.println("You lost your $" + bet.getBetAmount() + " place bet on " +
                    bet.getHorse());
                }

            }

            else if(bet.getTypeBet() == 3){
                if(bet.getHorse().equals(results.get(0)) || bet.getHorse().equals(results.get(1)) || bet.getHorse().equals(results.get(2))){
                  returnAmt = bet.getBetAmount() * results.get(0).getShowOdds();
                  wallet += returnAmt;
                  System.out.println("Your show bet won $" + returnAmt); 
                } else{
                    System.out.println("You lost your $" + bet.getBetAmount() + "  show bet on " +
                    bet.getHorse());
                }
            }

            else if(bet.getTypeBet() == 4){
                if((bet.getHorse1().equals(results.get(0)) || bet.getHorse1().equals(results.get(1))) &&
                   (bet.getHorse2().equals(results.get(0)) || bet.getHorse2().equals(results.get(1)))){
                    returnAmt = bet.getBetAmount() * results.get(0).getPlaceOdds() * results.get(1).getPlaceOdds();
                    wallet += returnAmt;
                    System.out.println("Your boxed exacta bet won $" + returnAmt); 
                } else{
                    System.out.println("You lost your $" + bet.getBetAmount() + " boxed exacta bet on " + bet.getHorse1() 
                    + "and" + bet.getHorse2());
                }
            }

            else if(bet.getTypeBet() == 5){
                if((bet.getHorse1().equals(results.get(0)) || bet.getHorse1().equals(results.get(1)) || bet.getHorse1().equals(results.get(2))) &&
                   (bet.getHorse2().equals(results.get(0)) || bet.getHorse2().equals(results.get(1)) || bet.getHorse2().equals(results.get(2))) &&
                   (bet.getHorse3().equals(results.get(0)) || bet.getHorse3().equals(results.get(1)) || bet.getHorse3().equals(results.get(1)))){
                    returnAmt = bet.getBetAmount() * results.get(0).getShowOdds() * results.get(1).getShowOdds() * results.get(2).getShowOdds();
                    wallet += returnAmt;
                    System.out.println("Your boxed trifecta bet won $" + returnAmt);  
                } else{
                    System.out.println("You lost your $" + bet.getBetAmount() + " boxed trifecta bet on "
                    + bet.getHorse1() + ", " + bet.getHorse2() + ", and " + bet.getHorse3());
                }
            }

            else if(bet.getTypeBet() == 6){
                if(bet.getHorse1().equals(results.get(0)) && bet.getHorse2().equals(results.get(1))){
                    returnAmt = bet.getBetAmount() * results.get(0).getWinOdds() * results.get(1).getWinOdds();
                    wallet += returnAmt;
                    System.out.println("Your exacta bet won $" + returnAmt); 
                } else{
                    System.out.println("You lost your $" + bet.getBetAmount() + " exacta bet on " +
                    bet.getHorse1() + " and " + bet.getHorse2());
                }
            }

            else if(bet.getTypeBet() == 7){
                if(bet.getHorse1().equals(results.get(0)) && bet.getHorse2().equals(results.get(1)) && bet.getHorse3().equals(results.get(2))){
                    returnAmt = bet.getBetAmount() * results.get(0).getWinOdds() * results.get(1).getWinOdds() * results.get(2).getWinOdds();
                    wallet += returnAmt;
                    System.out.println("Your trifecta bet won $" + returnAmt); 
                } else{
                    System.out.println("You lost your $" + bet.getBetAmount() + " trifecta bet on "
                    + bet.getHorse1() + ", " + bet.getHorse2() + ", and " + bet.getHorse3());
                }
            }            
        }
        System.out.println();
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public List<Bet> getBets(){
        return bets;
    }

    public int numHorses(){
        return horses.size();
    }

    public int numBets(){
        return bets.size();
    }

    public Horse getNextHorse(){
        if (currentHorse == horses.size())
            currentHorse = 0;
        
        return horses.get(currentHorse++);
    }

    public Bet getNextBet(){
        if(currentBet == bets.size())
            currentBet = 0;
        return bets.get(currentBet++);
    }

    public double getRaceLength() {
        return raceLength;
    }

    public String getRaceSurface() {
        return raceSurface;
    }

    public void displayRaceInfo() {
        System.out.println("Race Information:");
        System.out.println("Race Surface: " + raceSurface);
        System.out.println("Race Length: " + raceLength + " furlongs");
        System.out.println("+---------------------------+----------+------------+-----------+----------+");
        System.out.println("|            name           |Mud Rating|Grass Rating|Dirt Rating|Fav Length|");
        System.out.println("+---------------------------+----------+------------+-----------+----------+");
        for (Horse horse : horses) {
            if(horse.getNumber() >= 10)
                System.out.print("|" + horse.getNumber() + ". " + horse.getName());
            else
                System.out.print("|" + horse.getNumber() + ". " + horse.getName() + " ");
            for(int j = 0; j < 23 - horse.getLength(); j++){
                System.out.print(" ");    
            }
            if(horse.getMudRating() == 10 )
                spaceM = "";
            else
                spaceM = " ";
            if(horse.getGrassRating() == 10)
                spaceG = "";
            else
                spaceG = " ";
            if(horse.getDirtRating() == 10)
                spaceD = "";
            else 
                spaceD = " ";
            if(horse.getPreferredLength() >= 10.0)
                spaceL = "";
            else   
                spaceL = " ";
            
            System.out.print("|   " + spaceM + horse.getMudRating() + "0%   ");
            System.out.print("|    " + spaceG + horse.getGrassRating() + "0%    ");
            System.out.print("|    " + spaceD + horse.getDirtRating() + "0%   ");
            System.out.println("|   " + spaceL + horse.getPreferredLength() + "   |");
        }
        System.out.println("+--------------------------------------------------------------------------+");
        System.out.println();
    }

    public void displayBettingInfo(){
        System.out.println("1. Win - A horse finishing 1st");
        System.out.println("2. Place - A horse finishing 1st or 2nd");
        System.out.println("3. Show - A horse finishing in the top 3");
        System.out.println("4. Boxed Exacta - 2 horses finishing 1st or 2nd in any order");
        System.out.println("5. Boxed Trifecta - 3 horses finishing in the top 3");
        System.out.println("6. Exacta - 2 horses that finish 1st and 2nd in a specific order");
        System.out.println("7. Trifecta - 3 horses finishing 1st, 2nd, and 3rd in the correct order");
        System.out.println();
        System.out.println("+---------------------------+---+---+---+");
        System.out.println("|            name           | 1 | 2 | 3 |");
        System.out.println("+---------------------------+---+---+---+");
        for (Horse horse : horses) {
            if(horse.getNumber() >= 10)
              System.out.print("|" + horse.getNumber() + ". " + horse.getName());
            else
              System.out.print("|" + horse.getNumber() + ". " + horse.getName() + " ");
            for(int j = 0; j < 23 - horse.getLength(); j++){
                System.out.print(" ");    
            }
            System.out.print("|" + horse.bettingWin());
            System.out.print("|" + horse.bettingPlace());
            System.out.print("|" + horse.bettingShow());
            System.out.println("|");
        }
        System.out.println("+---------------------------------------+");

    }

    public void chooseBetType(){
        this.betType = HorseRacingHelper.getNumericInput(1, 7, in, "Please choose a bet type: ");
    }

    public int getBetType(){
        return this.betType;
    }

    public String askDoneBets(Scanner in){
        boolean isValid = false;
        while(!isValid){
            System.out.print("Bet again? (y/n): ");
            try{
                result = (in.nextLine()).toLowerCase(); //turns next thing put to lowercase
                if ((result.equals("n"))||(result.equals("y"))){
                    isValid = true; //if it is y or n continue to line 61
                }
            }catch(InputMismatchException badThing){
                System.out.println("Please enter y or n."); //if it is not y or n, prompt again
            }
        }
        return result;
    }

    public void placeBet(){
        int horseNumber = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a horse to bet on: ");
        Horse horse = horses.get(horseNumber - 1);
        System.out.println("You have $" + wallet);
        this.betAmt = HorseRacingHelper.getNumericInput(1, wallet, in, "Please choose a bet amount: ");   
        wallet -= this.betAmt;     
        bets.add(new Bet(this.betType, horse, this.betAmt));
    }

    public void placeBetBoxEx(){
        int horseNumber1 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a horse place 1st or 2nd: ");
        Horse horse1 = horses.get(horseNumber1 - 1);
        int horseNumber2 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a second horse place 1st or 2nd: ");
        Horse horse2 = horses.get(horseNumber2 - 1);

        System.out.println("You have $" + wallet);
        this.betAmt = HorseRacingHelper.getNumericInput(1, wallet, in, "Please choose a bet amount: ");   
        wallet -= this.betAmt;
        bets.add(new Bet(this.betType, horse1, horse2, this.betAmt));
          
    }

    public void placeBetBoxTri(){
        
        int horseNumber1 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a horse place 1st, 2nd, or 3rd: ");
        Horse horse1 = horses.get(horseNumber1 - 1);
        int horseNumber2 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a second horse place 1st, 2nd, or 3rd: ");
        Horse horse2 = horses.get(horseNumber2 - 1);
        int horseNumber3 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a third horse place 1st, 2nd, or 3rd: ");
        Horse horse3 = horses.get(horseNumber3 - 1);

        System.out.println("You have $" + wallet);
        this.betAmt = HorseRacingHelper.getNumericInput(1, wallet, in, "Please choose a bet amount: ");   
        wallet -= this.betAmt;
        bets.add(new Bet(this.betType, horse1, horse2, horse3, this.betAmt));
    }

    public void placeBetEx(){
        int horseNumber1 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a horse to place 1st: ");
        Horse horse1 = horses.get(horseNumber1 - 1);
        int horseNumber2 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a horse to place 2nd: ");
        Horse horse2 = horses.get(horseNumber2 - 1);
        

        System.out.println("You have $" + wallet);
        this.betAmt = HorseRacingHelper.getNumericInput(1, wallet, in, "Please choose a bet amount: ");   
        wallet -= this.betAmt;

        bets.add(new Bet(this.betType, horse1, horse2, this.betAmt));       
    }

    public void placeBetTri(){
        int horseNumber1 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a horse to place 1st: ");
        Horse horse1 = horses.get(horseNumber1 - 1);
        int horseNumber2 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a horse to place 2nd: ");
        Horse horse2 = horses.get(horseNumber2 - 1);
        int horseNumber3 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a horse place 3rd: ");
        Horse horse3 = horses.get(horseNumber3 - 1);
        

        System.out.println("You have $" + wallet);
        this.betAmt = HorseRacingHelper.getNumericInput(1, wallet, in, "Please choose a bet amount: ");   
        wallet -= this.betAmt;

        bets.add(new Bet(this.betType, horse1, horse2, horse3, this.betAmt));       
    }

    public void displayBetResults(){
        System.out.println("Bet Results");
        System.out.println("-----------");        
    }

    public void displayResults(){
        System.out.println("\n\nRace Results");
        System.out.println("------------");
        for(int i=0; i<results.size(); i++){
            System.out.println((i+1) + ": " + results.get(i).getName() + "("+results.get(i).getNumber()+")");
        }
        System.out.println();
    }


    public void startRace(){
        resetHorses();
        int numSpaces = (int)(raceLength*10);
        boolean done = false;
        HorseRacingHelper.pauseForMilliseconds(1000);
        HorseRacingHelper.playBackgroundMusicAndWait("Race.wav");
        HorseRacingHelper.playBackgroundMusic("horse_gallop.wav", true);

        while(!done){
            HorseRacingHelper.pauseForMilliseconds(100);
            HorseRacingHelper.clearConsole();
            HorseRacingHelper.updateTrack(numSpaces, horses);
            Horse horse = getNextHorse();

            int mover = setMovement(horse); //calls method to set horse movement
            
            if(!horse.raceFinished() && horse.getCurrentPosition() >= numSpaces){
                results.add(horse);
                horse.setRaceFinished(true);
            } else if(!horse.raceFinished()){
                horse.incrementPosition((int)((Math.random() * 4) + mover)); //generates random # with min being set in movement class
            }

            displayResults();
            

            if (results.size() == horses.size())
                done = true;
        }
        
        checkBets();

        HorseRacingHelper.stopMusic();
    }
    // Other methods for simulating the race, calculating winners, etc., can be added as needed

    private void resetHorses() {
        for (Horse horse : horses) {
            horse.resetCurrenPosition();
        }
    }

    private int setMovement(Horse horse){ //movement method
        int mover = 0; // default is 0
        double lengthCheck = horse.getPreferredLength(); //get horse pref length
        int ratingCheck = 0;
        if (raceSurface == "Mud") // depending on race surface, will get the horses rating
            ratingCheck = horse.getMudRating(); 
        if (raceSurface == "Grass")
            ratingCheck = horse.getGrassRating();
        if (raceSurface == "Dirt")
            ratingCheck = horse.getDirtRating();
        if (Math.abs(raceLength - lengthCheck) <= 1.5) //if pref length is 1.5 away from race length, raise min increment
            mover += 2;
        if (Math.abs(raceLength - lengthCheck) <= 3 && Math.abs(raceLength - lengthCheck) > 1.5) //if pref length is 3-1.5 away from race length, raise min increment
            mover += 1;
        if (ratingCheck >= 8) //if rating is over 8, raise min increment
            mover += 3;
        if (ratingCheck >= 6 && ratingCheck < 8) //if rating is 6-8, raise min increment
            mover += 2;
        return mover;
      }

}
