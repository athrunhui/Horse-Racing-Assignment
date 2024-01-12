package horseracing;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HorseRacing {

     public static void main(String[] args) {
        Scanner in = new Scanner(System.in);    
        HorseRacingHelper.prepareHorseRacingSimulation();
        boolean gameOver = false;
        while(!gameOver){
            HorseRacingHelper.clearConsole();

            int numHorsesInRace = (int)(Math.random()*7)+5;

            Race race = HorseRacingHelper.createRace(numHorsesInRace, HorseRacingHelper.SHORT, HorseRacingHelper.DIRT);
            race.displayRaceInfo();

            race.startRace();

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
                result = in.nextLine();
                if ((result.equals("n"))||(result.equals("N"))||(result.equals("y"))||(result.equals("Y"))){
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
        else {
            return false;
        }       

    }

}
