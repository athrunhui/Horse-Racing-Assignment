package horseracing;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HorseRacing {

     public static void main(String[] args) {
        boolean start = false;
        Scanner in = new Scanner(System.in);    
        HorseRacingHelper.prepareHorseRacingSimulation();
        boolean gameOver = false;
        while(!gameOver){
            HorseRacingHelper.clearConsole();
            int numHorsesInRace = (int)(Math.random()*7)+5;
           
            int raceLength = HorseRacing.setRaceLength();
            int raceType = HorseRacing.setRaceType();
           
            Race race = HorseRacingHelper.createRace(numHorsesInRace, raceLength, raceType);
            race.displayRaceInfo();

            race.startRace();
            race.displayBettingInfo();
            System.out.print("Please choose a bet type: ");
            while(!start){
            int bet = in.nextInt();
            if(bet < 4)
              race.startRace();
            if(bet == 4){
                System.out.println("Please choose 2 horses");
                System.out.println("Horse 1: ");
                int horse1 = in.nextInt();
                
                System.out.println();
            }
            }

            System.out.println("Race is Over");
            gameOver = playAgain(in);
        }

        
    }

    private static boolean playAgain(Scanner in) {
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

    public static int setRaceLength(){
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

    public static int setRaceType(){ //same logic as setRaceLength
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
