package horseracing;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Race {
    private List<Horse> horses;
    private List<Bet> bets;
    private static List<Player> players;
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
        if (players == null)
            players = new ArrayList<Player>(); // creates somewhere to hold all the players
    }

    /* The checkBets method resolves all of the bets that the player(s) have placed. Firstly, it shuffles through each player and
     * each of the bets they have placed. Next, it finds out what type of bet it was through the numbers assigned to each bet
     * (e.g. win = 1, place = 2, bet = 3). Then, it checks to see if the player won the bet by checking the results List which holds 
     * the info about which horse won the race. Finally, it gets the odds from the horse class which calculates the odds of each horse
     * and displays it in the displayOdds table and multiplies it based on the type of bet then adds the return amount to their wallet.
     * How the multiplications work assuming each bet has won.
     * Win bet - multiplies bet amount by win odd   Place bet - multiplies bet amount by place odd  Show bet - multiplies bet by show odd
     * Boxed Exacta - multiplies bet amount by place odd of the top 2 horses    
     * Boxed Trifecta - multiplies bet amount by show odd of the top 3 horses
     * Exacta - multiplies bet amount by win odd of the top 2 horses    Trifecta - multiplies bet amount by win odd of the top 3 horses
     */
    public void checkBets(){
        displayBetResults();
        for(Player player : players){
            System.out.println();
            System.out.println("Player " + player.getPlayerNumber() + " - " + player.getPlayerName() + ": ");
            System.out.println("----------------------");
            for (Bet bet : player.getPlayerBets()) { 
                int wallet = player.getWallet();
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
                        System.out.println("You lost your $" + bet.getBetAmount() + " show bet on " + bet.getHorse().getName());
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
                System.out.println();     
                player.updateWallet(wallet);   
            }
                
                System.out.println();
                System.out.println("Player " + player.getPlayerNumber() + " - " + player.getPlayerName() + " has $" +
                player.getWallet() + " in their wallet.");
        }
        /*System.out.println();
        System.out.println("Wallet: $" + wallet); // prints amt of money in the wallet
        System.out.println();*/ 
    }

    public boolean gameOver(){
        int i = 0;
        for(Player player : players){
            if(player.getWallet() == 0)
                i++;
        }
        if(i == players.size())
            return true;
        return false;
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

    public void removeBets(){ // removes all bets from the player class and all the bets in this race class.
        for(Player player : players){
            player.removeBets();
        }
        this.bets.removeAll(this.bets);
    }

    public int numHorses(){ // number of horses in the list
        return horses.size();
    }

    public int numBets(){ // number of bets in the list
        return bets.size();
    }

    public int numPlayers(){ // number of players in the list
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

    public Player getNextPlayer(){ // gets the next player in the list starting at 0
        if(currentPlayer == players.size())
            currentPlayer = 0;
        return players.get(currentPlayer++);
    }

    public double getRaceLength() { // gets the race length
        return raceLength;
    }

    public String getRaceSurface() { // gets the race terrain
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

    public void displayBettingInfo(){ // displays each horse and their odds of getting the win, place, and show bet
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
        for (Horse horse : horses) { // iterates through the horse list and prints all horses and their info in the table
            if(horse.getNumber() >= 10)
              System.out.print("|" + horse.getNumber() + ". " + horse.getName());
            else
              System.out.print("|" + horse.getNumber() + ". " + horse.getName() + " ");
            for(int j = 0; j < 23 - horse.getLength(); j++){
                System.out.print(" ");    
            }
            System.out.print("| " + horse.bettingWin() + " "); // gets the odds of the win, place, and show bets from the horse class
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
    }

    public void chooseBetType(String player){ // prompts the user to choose a bet type from 1-7
        System.out.println(player);
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

    /* iterates through all the players and makes sure they each get to bet.
     * First, the method prints which player's turn it is to make the bet and asks the player what kind of bet they want to make
     * Second, the method goes to a placeBet(e.g. BoxEx, Tri) method depending on what bet the player wants to place.
     * Third, the placeBet(X) method returns the bet amount, subtracts it from the player's wallet and updates that player's wallet.
     * Fourth, the method adds the bet to the player class's bets List to assign each bet to the corresponding player.
     * Fifth, the method prints the player's remaining balance and prompts them to bet again.
     * However, if the player does not have enough money, it doesn't allow them to bet and tells them that they are broke.
     */
    public void betType(Scanner in){
        boolean doneBets = false;
        System.out.println();
        System.out.println("Please make all selections in numbers.");
        System.out.println("e.g. selecting win bet = 1, selecting horse to bet on = 3");
        System.out.println();
        for(Player player : players){
            doneBets = false;
            while(!doneBets){
            if(player.getWallet() > 0){ // as long as the player has money, let them bet
            String playerInfo = "Player " + player.getPlayerNumber() + " - " + player.getPlayerName() + ": ";
            chooseBetType(playerInfo);                // prompts the player to choose a bet type
            int wallet = player.getWallet();

            if (getBetType() < 4)           // if the bet is a win, place, or show bet
                wallet = placeBet(wallet, player);                   // they will be prompted to place a bet amt on a horse

            else if(getBetType() == 4)      // if the bet is a boxed exacta
                wallet = placeBetBoxEx(wallet, player);              // they will be prompted to place a bet on 2 horses

            else if(getBetType() == 5)      // if the bet is a boxed trifecta
                wallet = placeBetBoxTri(wallet, player);             // they will be prompted to place a bet on 3 horses

            else if(getBetType() == 6)      // if the bet is an exacta
                wallet = placeBetEx(wallet, player);                 // they will be prompted to place a bet on 2 horses

            else if(getBetType() == 7)      // if the bet is a trifecta
                wallet = placeBetTri(wallet, player);                // they will be prompted to place a bet on 3 horses
                
            player.updateWallet(wallet);
            player.addBets(getNextBet());
            System.out.println("You have $" + player.getWallet() + " left");
            doneBets = doneBets(in).equals("n");
            System.out.println();
            }
            else if(player.getWallet() == 0){ // if the player has run out of money, skip their bet and move to the next player or start game
                doneBets = true;
                System.out.println("Player " + player.getPlayerNumber() + " - " + player.getPlayerName() +
                " has no more money. " + player.getPlayerName() + " cannot make a bet");
                System.out.println();
            }
        }
        }   
    }

    public String askDoneBets(Scanner in){ // prompts the player if they are done betting and ensures that the input is valid
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
        System.out.println();
        return result;
    }

    public int askNumPlayers(Scanner in){ // asks how many players are playing the horse racing game
        int numPlayers = HorseRacingHelper.getNumericInput(1, 10, in, "Please input the amount of players (1-10): ");
        return numPlayers;
    }

    public void askPlayerName(Scanner in, int playerNumber, int wallet){ // asks the name of each player and creates a new player
        boolean isValid = false;
        int i = playerNumber;

        while(!isValid){
            System.out.println("Player " + i + ": ");
            System.out.print("Input a player name between 3 and 15 characters: ");
            
            result = in.nextLine(); //turns next thing put to lowercase
            if ((result.length() > 2) && (result.length() < 15)){
                isValid = true; //if it is y or n continue to line 61
            } else{
                System.out.println("Name is not in the specified range.");
                System.out.println();
            }
            for(int j = 0; j < players.size(); j++){
                if(players.get(j).getPlayerName().equals(result)){
                    System.out.println("Name has already been chosen. Please input another name.");
                    System.out.println();
                    isValid = false;
                }
            }
        }
            players.add(new Player(wallet, result, i));
            System.out.println();
        
    }

    public int placeBet(int wallet, Player player){ // method for win, place, or show bets

        int horseNumber = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a horse to bet on: ");
        Horse horse = horses.get(horseNumber - 1); // prompts the user to choose a horse from 1-# of horses in the race
        // but the list of horses starts at 0 so subtracts one to get(horse)

        System.out.println("You have $" + wallet); // displays amount of money
        this.betAmt = HorseRacingHelper.getNumericInput(1, wallet, in, "Please choose a bet amount: "); 
        wallet -= this.betAmt;      // prompts user to bet money and subtracts it from the wallet

        bets.add(new Bet(this.betType, horse, this.betAmt, player)); // creates a new bet in the list to hold the betting info
    
        return wallet;
    }

    public int placeBetBoxEx(int wallet, Player player){ // method for boxed exacta and prompts 2 horses instead of 1, otherwise same logic as placeBet()

        int horseNumber1 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a horse place 1st or 2nd: ");
        Horse horse1 = horses.get(horseNumber1 - 1);
        int horseNumber2 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a second horse place 1st or 2nd: ");
        Horse horse2 = horses.get(horseNumber2 - 1); 

        System.out.println("You have $" + wallet);
        this.betAmt = HorseRacingHelper.getNumericInput(1, wallet, in, "Please choose a bet amount: ");   
        wallet -= this.betAmt;
        bets.add(new Bet(this.betType, horse1, horse2, this.betAmt, player));
        
        return wallet;
    }

    public int placeBetBoxTri(int wallet, Player player){ // method for boxed trifecta and prompts 3 horses, otherwise same logic  

        int horseNumber1 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a horse place 1st, 2nd, or 3rd: ");
        Horse horse1 = horses.get(horseNumber1 - 1);
        int horseNumber2 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a second horse place 1st, 2nd, or 3rd: ");
        Horse horse2 = horses.get(horseNumber2 - 1);
        int horseNumber3 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a third horse place 1st, 2nd, or 3rd: ");
        Horse horse3 = horses.get(horseNumber3 - 1);

        System.out.println("You have $" + wallet);
        this.betAmt = HorseRacingHelper.getNumericInput(1, wallet, in, "Please choose a bet amount: ");   
        wallet -= this.betAmt;
        bets.add(new Bet(this.betType, horse1, horse2, horse3, this.betAmt, player));

        return wallet;
    }

    public int placeBetEx(int wallet, Player player){ // method for exacta and prompts 2 horses, otherwise same logic

        int horseNumber1 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a horse to place 1st: ");
        Horse horse1 = horses.get(horseNumber1 - 1);
        int horseNumber2 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a horse to place 2nd: ");
        Horse horse2 = horses.get(horseNumber2 - 1);
        

        System.out.println("You have $" + wallet);
        this.betAmt = HorseRacingHelper.getNumericInput(1, wallet, in, "Please choose a bet amount: ");   
        wallet -= this.betAmt;

        bets.add(new Bet(this.betType, horse1, horse2, this.betAmt, player));  
        
        return wallet;
    }

    public int placeBetTri(int wallet, Player player){ // method for trifecta and prompts 3 horses, otherwise same logic

        int horseNumber1 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a horse to place 1st: ");
        Horse horse1 = horses.get(horseNumber1 - 1);
        int horseNumber2 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a horse to place 2nd: ");
        Horse horse2 = horses.get(horseNumber2 - 1);
        int horseNumber3 = HorseRacingHelper.getNumericInput(1, horses.size(), in, "Please choose a horse place 3rd: ");
        Horse horse3 = horses.get(horseNumber3 - 1);
        

        System.out.println("You have $" + wallet);
        this.betAmt = HorseRacingHelper.getNumericInput(1, wallet, in, "Please choose a bet amount: ");   
        wallet -= this.betAmt;

        bets.add(new Bet(this.betType, horse1, horse2, horse3, this.betAmt, player));    
        
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
        int mover = 1; // default is 1
        double lengthCheck = horse.getPreferredLength(); //get horse pref length
        int ratingCheck = getCurrentRating(horse);
        double lengthAdv = (Math.abs(raceLength - lengthCheck));
        if (lengthAdv == 0) //if pref length is race length, raise min increment
            mover += 3;
        else if (lengthAdv <= 1.5 && lengthAdv > 0) //if pref length is 1.5 away from race length, raise min increment
            mover += 2;
        else if (lengthAdv <= 2.5 && lengthAdv > 1.5) //if pref length is 2.5-1.5 away from race length, raise min increment
            mover += 1;
        mover += (ratingCheck/2);
        return mover;
      }

    private int getCurrentRating(Horse horse){
        if (raceSurface == "Mud") // depending on race surface, will get the horses rating
            return horse.getMudRating(); 
        else if (raceSurface == "Grass")
            return horse.getGrassRating();
        else if (raceSurface == "Dirt")
            return horse.getDirtRating();
        return 0;
    }

    /*this method sets the movement for each horse that affects how quickly they move across the screen,
    i.e. how likely they are to win. the method returns a minimum movement number that is used in the math.random
    in the main create race method. The default is set to 1 so if a horse has no race advantages, it can move 1-5
    increments. There are then a series of if statements that check for certain advantages per horse. It first checks
    what the race length is away from the horses preferred length. It raises the minimum increment more for a higher rating.
    The closer the preferred length is to the real length, the more of an advantage the horse has, so it has a higher
    minimum increment. Same for the closer the surface rating is to 10. the minimum gets raised by dividing the rating by 2
    so the higher the rating, the more gets added as the quotient is higher*/

}
