package horseracing;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HorseRacing {

     public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HorseRacingHelper.prepareHorseRacingSimulation();
        boolean gameOver = false;
        boolean isFirstGame = true;
        int wallet = 50;
        int numPlayers = 1;
        while(!gameOver){
            HorseRacingHelper.clearConsole();
            int numHorsesInRace = (int)(Math.random()*7)+5; // 5-11 horses in a race
           
            int raceLength = setRaceLength();   // sets a random race length
            int raceType = setRaceType();       // sets a random race terrain
           
            Race race = HorseRacingHelper.createRace(numHorsesInRace, raceLength, raceType);
            race.displayRaceInfo();         // displays the race type, race length, all of the horses and stats
            race.displayBettingInfo();      // displays the win, place, and show odds for each horse   
            
            // if the player(s) have chosen to play again, isFirst game will be false to ensure that the players do not make more players. 
            if(isFirstGame) { 
                numPlayers = race.askNumPlayers(in);
                for(int i = 0; i < numPlayers; i++){
                    race.askPlayerName(in, i + 1, wallet);
                }    
            }
            race.betType(in); 
            
            System.out.print("\u001B[?25l");  // Hide the cursor
            race.startRace(wallet);
            System.out.println("Race is Over");
            
            gameOver = playAgain(in);
            if(!gameOver){ // if the player(s) choose to play again, the race class will remove all bets in their list and the player's list
                race.removeBets();
            }
            isFirstGame = false;
            gameOver = race.gameOver();
            if(race.gameOver()) // if the players all have no more money, the game is over since they cannot bet
                System.out.println("Player(s) have no more money left, the game is over");
        }

        
    }

    private static boolean playAgain(Scanner in) {
        System.out.print("\u001B[?25l");  // Hide the cursor
        boolean isValid = false;
        String result = "";
        while(!isValid){
            System.out.print("Play Again: (y/n): ");
            try{
                result = (in.nextLine()).toLowerCase(); //turns next thing put to lowercase
                if ((result.equals("n"))||(result.equals("y"))){
                    isValid = true; //if it is y or n continue to line 61
                }
            }catch(InputMismatchException badThing){
                System.out.println("Please enter y or n."); //if it is not y or n, prompt again
            }
        }

        if (result.equals("n")){ //if answer is n end game, otherwise play new game
            System.out.println("Thanks for playing.");
            return true;
        }
            
        return false;

    }

    private static int setRaceLength(){
        int raceLength;
        int raceLChange = (int)((Math.random()*10)%3); //gets random # then checks remainder when / by 3

        if (raceLChange == 1) // if remainders 1 set middle
            raceLength = HorseRacingHelper.MIDDLE;
        else if (raceLChange == 2) //remainder 2 set long
            raceLength = HorseRacingHelper.LONG;
        else // no remainder set short
            raceLength = HorseRacingHelper.SHORT;
        
        return raceLength; 
    }

    private static int setRaceType(){ //same logic as setRaceLength
        int raceType;
        int raceTChange = (int)((Math.random()*10)%3);

        if (raceTChange == 1)
            raceType = HorseRacingHelper.MUD;
        else if (raceTChange == 2)
            raceType = HorseRacingHelper.GRASS;
        else
            raceType = HorseRacingHelper.DIRT;

        return raceType;
    }

}
