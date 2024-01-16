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

            Race race = HorseRacingHelper.createRace(numHorsesInRace, HorseRacingHelper.SHORT, HorseRacingHelper.DIRT);
            race.displayRaceInfo();
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
                result = (in.nextLine()).toLowerCase();
                if ((result.equals("n"))||(result.equals("y"))){
                    isValid = true;
                }
            }catch(InputMismatchException badThing){
                System.out.println("Please enter y or n.");
            }
        }

        if (result.equals("n")){
            System.out.println("Thanks for playing.");
            return true;
        }    
        return false;

    }

}
