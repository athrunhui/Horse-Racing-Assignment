package horseracing;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Race {
    private List<Horse> horses;
    private List<Bet> bets;
    private List<Player> players;
    private double raceLength; // in furlongs
    private String raceSurface; // "grass", "dirt", or "mud" (Uses HorseRacingHelper constants)
    private int currentHorse;
    private int currentBet;
    private int currentPlayer;
    private String spaceM;
    private String spaceG;
    private String spaceD;
    private String spaceL;
    private int betAmt = 0;
    private int returnAmt;
    private int betType;
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
        this.bets = new ArrayList<Bet>();       // creates somewhere to hold all of the betting information
        this.players = new ArrayList<Player>(); // creates somewhere to hold all the players
    }

    public void checkBets(int wallet){
        displayBetResults();
        for (Bet bet : bets) {
            if (bet.getTypeBet() == 1){                                         // if it is a "win" bet
                if (bet.getHorse().equals(results.get(0))){                     // if the horse got first (0)
                  returnAmt = bet.getBetAmount() * bet.getHorse().getWinOdds(); // multiplies bet amount by win odds

                  wallet += returnAmt;                                          // adds money to wallet
                  System.out.println("Your win bet on " + bet.getHorse().getName() + " won $" + returnAmt);   

                } else{                
                    System.out.println("You lost your $" + bet.getBetAmount() + " win bet on " + bet.getHorse().getName());
                } // prints player outcome
            }

        else if(bet.getTypeBet() == 2){     // if it is a "place bet"
                if(bet.getHorse().equals(results.get(0)) || bet.getHorse().equals(results.get(1))){  // if the horse got 1st or 2nd
                  returnAmt = bet.getBetAmount() * bet.getHorse().getPlaceOdds();                    // multiplies bet by place odds

                  if(bet.getHorse().bettingPlace().substring(1).equals("-2"))                        // divides return amt by 2 if
                    returnAmt /= 2;                                                                  // the place odd was X-2

                  wallet += returnAmt;
                  System.out.println("Your place bet on " + bet.getHorse().getName() + " won $" + returnAmt); 

                } else {
                    System.out.println("You lost your $" + bet.getBetAmount() + " place bet on " + bet.getHorse().getName());
                } // prints player outcome

            }

            else if(bet.getTypeBet() == 3){ // if the bet was a "show" bet,         if the horse got 1st, 2nd, or 3rd, the horse 
                if(bet.getHorse().equals(results.get(0)) || bet.getHorse().equals(results.get(1)) || bet.getHorse().equals(results.get(2))){
                  returnAmt = bet.getBetAmount() * bet.getHorse().getShowOdds(); // multiplies the bet by the show odds 

                  if(bet.getHorse().bettingShow().substring(1).equals("-2"))     // divides the return by 2 if
                    returnAmt /= 2;                                              // the show odd was X-2
                    
                  wallet += returnAmt;
                  System.out.println("Your show bet on " + bet.getHorse().getName() + " won $" + returnAmt); 

                } else{
                    System.out.println("You lost your $" + bet.getBetAmount() + "  show bet on " + bet.getHorse().getName());
                } // prints player outcome
            }

            else if(bet.getTypeBet() == 4){ // if the bet was a "boxed exacta" bet
                if((bet.getHorse1().equals(results.get(0)) || bet.getHorse1().equals(results.get(1))) &&     
                   (bet.getHorse2().equals(results.get(0)) || bet.getHorse2().equals(results.get(1)))){  
                    // if the horses got 1st or 2nd the bet is mulltiplied by each place odd
                  returnAmt = bet.getBetAmount() * bet.getHorse1().getPlaceOdds() * bet.getHorse2().getPlaceOdds(); 

                  if(bet.getHorse1().bettingPlace().substring(1).equals("-2"))                  // divides return amt by 2 if
                    returnAmt /= 2;                                                           // the place odd was X-2
                  if(bet.getHorse2().bettingPlace().substring(1).equals("-2"))                // same
                    returnAmt /= 2;

                  wallet += returnAmt;
                  System.out.println("Your boxed exacta bet on " + bet.getHorse1().getName() + " and " + 
                  bet.getHorse2().getName() +  " won $" + returnAmt); 

                } else{
                    System.out.println("You lost your $" + bet.getBetAmount() + " boxed exacta bet on " + bet.getHorse1().getName() + 
                    "and" + bet.getHorse2().getName());
                } // prints player outcome
            }

            else if(bet.getTypeBet() == 5){ // if the bet was a "boxed trifecta bet"
                if((bet.getHorse1().equals(results.get(0)) || bet.getHorse1().equals(results.get(1)) || bet.getHorse1().equals(results.get(2))) &&
                   (bet.getHorse2().equals(results.get(0)) || bet.getHorse2().equals(results.get(1)) || bet.getHorse2().equals(results.get(2))) &&
                   (bet.getHorse3().equals(results.get(0)) || bet.getHorse3().equals(results.get(1)) || bet.getHorse3().equals(results.get(2)))){
                    // if the 3 horses bet on got either 1st, 2nd, or 3rd, the bet will be multiplied by each of their show odds

                  returnAmt = bet.getBetAmount() * bet.getHorse1().getShowOdds() * bet.getHorse2().getShowOdds() * bet.getHorse3().getShowOdds();
                  if(bet.getHorse1().bettingShow().substring(1).equals("-2"))                        // divides return amt by 2 if
                    returnAmt /= 2;                                                                  // the show odd was X-2
                  if(bet.getHorse2().bettingShow().substring(1).equals("-2"))                        // same
                    returnAmt /= 2;
                  if(bet.getHorse3().bettingShow().substring(1).equals("-2"))                        // same
                    returnAmt /= 2;

                  wallet += returnAmt;
                  System.out.println("Your boxed trifecta bet on " + bet.getHorse1().getName() + ", " + bet.getHorse2().getName() +
                  ", and " + bet.getHorse3().getName() + " won $" + returnAmt);  

                } else{
                    System.out.println("You lost your $" + bet.getBetAmount() + " boxed trifecta bet on "
                    + bet.getHorse1().getName() + ", " + bet.getHorse2().getName() + ", and " + bet.getHorse3().getName());
                } // prints player outcome
            }

            else if(bet.getTypeBet() == 6){ // if the bet was an "exacta" bet
                if(bet.getHorse1().equals(results.get(0)) && bet.getHorse2().equals(results.get(1))){ 
                // if the horses got 1st and 2nd in the correct order, the bet will be multiplied by each of their win odds

                  returnAmt = bet.getBetAmount() * bet.getHorse1().getWinOdds() * bet.getHorse2().getWinOdds();

                  wallet += returnAmt;
                  System.out.println("Your exacta bet on " + bet.getHorse1().getName() + " and " + bet.getHorse2().getName() +
                  " won $" + returnAmt); 

                } else{
                    System.out.println("You lost your $" + bet.getBetAmount() + " exacta bet on " + bet.getHorse1().getName() + " and " + bet.getHorse2().getName());
                } // prints player outcome
            }

            else if(bet.getTypeBet() == 7){ // if the bet was a "trifecta" bet
                if(bet.getHorse1().equals(results.get(0)) && bet.getHorse2().equals(results.get(1)) && bet.getHorse3().equals(results.get(2))){
                // if the horses got 1st, 2nd, and 3rd in the correct order, the bet will be multiplied by each of their win odds

                  returnAmt = bet.getBetAmount() * bet.getHorse1().getWinOdds() * bet.getHorse2().getWinOdds() * bet.getHorse3().getWinOdds();
                    
                  wallet += returnAmt;
                  System.out.println("Your trifecta bet on " + bet.getHorse1().getName() + ", " + bet.getHorse2().getName() +
                  ", and " + bet.getHorse3().getName() + " won $" + returnAmt); 

                } else{
                    System.out.println("You lost your $" + bet.getBetAmount() + " trifecta bet on " + bet.getHorse1().getName() + 
                    ", " + bet.getHorse2().getName() + ", and " + bet.getHorse3().getName());
                } // prints player outcome
            }            
        }
        System.out.println();
        System.out.println("Wallet: $" + wallet); // prints amt of money in the wallet
        System.out.println();
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public List<Bet> getBets(){
        return bets;
    }

    public List<Player> getPlayers(){
        return players;
    }

    public int numHorses(){ // number of horses in the list
        return horses.size();
    }

    public int numBets(){ // number of bets in the list
        return bets.size();
    }

    public int numPlayers(){
        return players.size();
    }

    public Horse getNextHorse(){ // gets the next horse in the list starting at 0
        if (currentHorse == horses.size())
            currentHorse = 0;
        
        return horses.get(currentHorse++);
    }

    public Bet getNextBet(){ // gets the next bet in the list starting at 0
        if(currentBet == bets.size())
            currentBet = 0;
        return bets.get(currentBet++);
    }

    public Player getNextPlayer(){
        if(currentPlayer == players.size())
            currentPlayer = 0;
        return players.get(currentPlayer++);
    }

    public double getRaceLength() {
        return raceLength;
    }

    public String getRaceSurface() {
        return raceSurface;
    }

    public void displayRaceInfo() { // displays each horse and their respective stats
        System.out.println("Race Information:");
        System.out.println("Race Surface: " + raceSurface);
        System.out.println("Race Length: " + raceLength + " furlongs");
        System.out.println("+---------------------------+----------+------------+-----------+----------+");
        System.out.println("|            name           |Mud Rating|Grass Rating|Dirt Rating|Fav Length|");
        System.out.println("+---------------------------+----------+------------+-----------+----------+");
        for (Horse horse : horses) {
            if(horse.getNumber() >= 10)
                System.out.print("|" + horse.getNumber() + ". " + horse.getName()); // allows the horse to fit in the space of the column
            else
                System.out.print("|" + horse.getNumber() + ". " + horse.getName() + " "); // prints the number and name into the column
            for(int j = 0; j < 23 - horse.getLength(); j++){
                System.out.print(" ");    
            }
            if(horse.getMudRating() == 10 ) // adds spaces to fit the rating if it becomes 2 digits
                spaceM = "";
            else
                spaceM = " ";
            if(horse.getGrassRating() == 10)        // same
                spaceG = "";
            else
                spaceG = " ";
            if(horse.getDirtRating() == 10)         // same
                spaceD = "";
            else 
                spaceD = " ";
            if(horse.getPreferredLength() >= 10.0)  // same
                spaceL = "";
            else   
                spaceL = " ";
            
            System.out.print("|   " + spaceM + horse.getMudRating() + "0%   ");         // adds each horse's stats to their
            System.out.print("|    " + spaceG + horse.getGrassRating() + "0%    ");     // respective columns
            System.out.print("|    " + spaceD + horse.getDirtRating() + "0%   ");
            System.out.println("|   " + spaceL + horse.getPreferredLength() + "   |");
        }
        System.out.println("+--------------------------------------------------------------------------+");
        System.out.println();
    }

    public void displayBettingInfo(int wallet){ // displays each horse and their odds of getting the win, place, and show bet
        System.out.println("1. Win - A horse finishing 1st");
        System.out.println("2. Place - A horse finishing 1st or 2nd");
        System.out.println("3. Show - A horse finishing in the top 3");
        System.out.println("4. Boxed Exacta - 2 horses finishing 1st or 2nd in any order");
        System.out.println("5. Boxed Trifecta - 3 horses finishing in the top 3");
        System.out.println("6. Exacta - 2 horses that finish 1st and 2nd in a specific order");
        System.out.println("7. Trifecta - 3 horses finishing 1st, 2nd, and 3rd in the specific order");
        System.out.println();
        System.out.println("+---------------------------+-----+-------+------+");
        System.out.println("|            name           | Win | Place | Show |");
        System.out.println("+---------------------------+-----+-------+------+");
        for (Horse horse : horses) {
            if(horse.getNumber() >= 10)
              System.out.print("|" + horse.getNumber() + ". " + horse.getName());
            else
              System.out.print("|" + horse.getNumber() + ". " + horse.getName() + " ");
            for(int j = 0; j < 23 - horse.getLength(); j++){
                System.out.print(" ");    
            }
            System.out.print("| " + horse.bettingWin() + " ");
            System.out.print("|  " + horse.bettingPlace() + "  ");
            System.out.print("|  " + horse.bettingShow() + " ");
            System.out.println("|");
        }
        System.out.println("+------------------------------------------------+");
        System.out.println();
        System.out.println("If you win the boxed exacta, your bet will be multiplied by " +
                            "each horse's place odd");
        System.out.println("If you win the boxed trifecta, your bet will be multiplied by " +
                            "each horse's show odd");
        System.out.println("If you win the exacta, your bet will be multiplied by " +
                            "each horse's win odd");
        System.out.println("If you win the trifecta, your bet will be multiplied by " +
                            "each horse's win odd");
        System.out.println();
        System.out.println("You have $" + wallet); // prints the money in the player's wallet.
        System.out.println();
        System.out.println("Please make all selections in numbers (e.g. selecting win bet = 1).");
    }

    public void chooseBetType(){ // prompts the user to choose a bet type from 1-7
        this.betType = HorseRacingHelper.getNumericInput(1, 7, in, "Please choose a bet type: ");
    }

    public int getBetType(){
        return this.betType;
    }

    public String doneBets(Scanner in){
        if(askDoneBets(in).equals("n")) // if they don't want to make a bet, they will respond "n"
          return "n";                   
        return "y";                     // otherwise they will respond "y"
    }

    public int betType(Scanner in, int wallet){
        chooseBetType();                // prompts the player to choose a bet type

        if (getBetType() < 4)           // if the bet is a win, place, or show bet
          wallet = placeBet(wallet);                   // they will be prompted to place a bet amt on a horse

        else if(getBetType() == 4)      // if the bet is a boxed exacta
          wallet = placeBetBoxEx(wallet);              // they will be prompted to place a bet on 2 horses

        else if(getBetType() == 5)      // if the bet is a boxed trifecta
          wallet = placeBetBoxTri(wallet);             // they will be prompted to place a bet on 3 horses

        else if(getBetType() == 6)      // if the bet is an exacta
          wallet = placeBetEx(wallet);                 // they will be prompted to place a bet on 2 horses

        else if(getBetType() == 7)      // if the bet is a trifecta
          wallet = placeBetTri(wallet);                // they will be prompted to place a bet on 3 horses
        return wallet;     
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

    public int placeBet(int wallet){ // method for win, place, or show bets

        int horseNumber = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a horse to bet on: ");
        Horse horse = horses.get(horseNumber - 1); // prompts the user to choose a horse from 1-# of horses in the race
        // but the list of horses starts at 0 so subtracts one to get(horse)

        System.out.println("You have $" + wallet); // displays amount of money
        this.betAmt = HorseRacingHelper.getNumericInput(1, wallet, in, "Please choose a bet amount: "); 
        wallet -= this.betAmt;      // prompts user to bet money and subtracts it from the wallet

        bets.add(new Bet(this.betType, horse, this.betAmt)); // creates a new bet in the list to hold the betting info
    
        return wallet;
    }

    public int placeBetBoxEx(int wallet){ // method for boxed exacta and prompts 2 horses instead of 1, otherwise same logic as placeBet()

        int horseNumber1 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a horse place 1st or 2nd: ");
        Horse horse1 = horses.get(horseNumber1 - 1);
        int horseNumber2 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a second horse place 1st or 2nd: ");
        Horse horse2 = horses.get(horseNumber2 - 1); 

        System.out.println("You have $" + wallet);
        this.betAmt = HorseRacingHelper.getNumericInput(1, wallet, in, "Please choose a bet amount: ");   
        wallet -= this.betAmt;
        bets.add(new Bet(this.betType, horse1, horse2, this.betAmt));
        
        return wallet;
    }

    public int placeBetBoxTri(int wallet){ // method for boxed trifecta and prompts 3 horses, otherwise same logic  

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

        return wallet;
    }

    public int placeBetEx(int wallet){ // method for exacta and prompts 2 horses, otherwise same logic

        int horseNumber1 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a horse to place 1st: ");
        Horse horse1 = horses.get(horseNumber1 - 1);
        int horseNumber2 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a horse to place 2nd: ");
        Horse horse2 = horses.get(horseNumber2 - 1);
        

        System.out.println("You have $" + wallet);
        this.betAmt = HorseRacingHelper.getNumericInput(1, wallet, in, "Please choose a bet amount: ");   
        wallet -= this.betAmt;

        bets.add(new Bet(this.betType, horse1, horse2, this.betAmt));  
        
        return wallet;
    }

    public int placeBetTri(int wallet){ // method for trifecta and prompts 3 horses, otherwise same logic

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
        
        return wallet;
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


    public void startRace(int wallet){
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
        
        checkBets(wallet);

        HorseRacingHelper.stopMusic();
    }
    // Other methods for simulating the race, calculating winners, etc., can be added as needed

    private void resetHorses() {
        for (Horse horse : horses) {
            horse.resetCurrenPosition();
        }
    }

    private int setMovement(Horse horse){ //movement method
        int mover = 1; // default is 1
        double lengthCheck = horse.getPreferredLength(); //get horse pref length
        int ratingCheck = 0;
        if (raceSurface == "Mud") // depending on race surface, will get the horses rating
            ratingCheck = horse.getMudRating(); 
        else if (raceSurface == "Grass")
            ratingCheck = horse.getGrassRating();
        else if (raceSurface == "Dirt")
            ratingCheck = horse.getDirtRating();
        if (Math.abs(raceLength - lengthCheck) == 0) //if pref length is race length, raise min increment
            mover += 3;
        if (Math.abs(raceLength - lengthCheck) <= 1.5 && Math.abs(raceLength - lengthCheck) > 0) //if pref length is 1.5 away from race length, raise min increment
            mover += 2;
        if (Math.abs(raceLength - lengthCheck) <= 2.5 && Math.abs(raceLength - lengthCheck) > 1.5) //if pref length is 2.5-1.5 away from race length, raise min increment
            mover += 1;
        if (ratingCheck == 10) //if rating is 10, raise min increment
            mover += 4;
        if (ratingCheck >= 8 && ratingCheck < 10) //if rating is over 8, raise min increment
            mover += 3;
        if (ratingCheck >= 6 && ratingCheck < 8) //if rating is 6-8, raise min increment
            mover += 2;
        return mover;
      }

    /*this method sets the movement for each horse that affects how quickly they move across the screen,
    i.e. how likely they are to win. the method returns a minimum movement number that is used in the math.random
    in the main create race method. The default is set to 1 so if a horse has no race advantages, it can move 1-4
    increments. There are then a series of if statements that check for certain advantages per horse. It first checks
    what the race length is away from the horses preferred length. then it checks the horses rating for the specific 
    race surface. It raises the minimum increment more for a higher rating. The closer the preferred length is to the 
    real length, the more of an advantage the horse has, so it has a higher minimum increment. Same for the closer the 
    the surface rating is to 10. So for example, if the race is an 8 furlong mud race, and a horse has a preferred length 
    of 7 furlongs and a 9 mud rating it will move between 6-10. A horse that prefers 8 furlongs and has a 10 mud rating
    for the same race will move 8-12. A horse that prefers 12 furlongs and dirt for the same race will move 1-4.*/

}
