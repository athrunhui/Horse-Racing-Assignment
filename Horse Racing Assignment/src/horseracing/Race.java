package horseracing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Race {
    private List<Horse> horses;
    private double raceLength; // in furlongs
    private String raceSurface; // "grass", "dirt", or "mud" (Uses HorseRacingHelper constants)
    private int currentHorse;
    private String spaceM;
    private String spaceG;
    private String spaceD;
    private String spaceL;
    private int horse;
    private int horse1;
    private int horse2;
    private int horse3;
    private int betAmt;
    private int wallet = 20;
    private int betType;
    private Bet Bet;
    private Scanner in = new Scanner(System.in);
    
    private List<Horse> results;

    public Race(List<Horse> horses, double raceLength, String raceSurface) {
        this.horses = horses;
        this.raceLength = raceLength;
        this.raceSurface = raceSurface;
        this.currentHorse = 0;
        this.results = new ArrayList<Horse>();
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public int numHorses(){
        return horses.size();
    }

    public Horse getNextHorse(){
        if (currentHorse == horses.size())
            currentHorse = 0;
        
        return horses.get(currentHorse++);
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
        System.out.println("+-------------------------+----------+------------+-----------+----------+");
        System.out.println("|          name           |Mud Rating|Grass Rating|Dirt Rating|Fav Length|");
        System.out.println("+-------------------------+----------+------------+-----------+----------+");
        for (Horse horse : horses) {
            if(horse.getNumber() >= 10)
                System.out.print("|" + horse.getNumber() + ". " + horse.getName());
            else
                System.out.print("|" + horse.getNumber() + ". " + horse.getName() + " ");
            for(int j = 0; j < 21 - horse.getLength(); j++){
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
        System.out.println("+------------------------------------------------------------------------+");
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
        System.out.println("+-----------------------+---+---+---+");
        System.out.println("|        name           | 1 | 2 | 3 |");
        System.out.println("+-----------------------+---+---+---+");
        for (Horse horse : horses) {
            System.out.print("|" + horse.getName());
            for(int j = 0; j < 23 - horse.getLength(); j++){
                System.out.print(" ");    
            }
            System.out.print("|" + horse.bettingWin());
            System.out.print("|" + horse.bettingPlace());
            System.out.print("|" + horse.bettingShow());
            System.out.println("|");           
            /*System.out.print("|" + horse.bettingShow());
            System.out.print("|" + horse.bettingShow());
            System.out.println("|" + horse.bettingShow() + "|");*/
        }
        System.out.println("+-----------------------------------+");

    }

    public void chooseBetType(){
        System.out.print("Please choose a bet type: ");
        this.betType = in.nextInt();
    }

    public int getBetType(){
        return this.betType;
    }

    public void placeBet(){
        System.out.print("Please choose a horse to bet on: ");
        this.horse = in.nextInt();
        while(this.betAmt > wallet){
            int i = 0;
            if(i > 0)
              System.out.println("Invalid Amount");
            System.out.print("Please choose a bet amount: ");
            this.betAmt = in.nextInt();
            i++;
        }
        
        this.Bet = new Bet(this.betType, this.horse, this.betAmt);
    }

    public void placeBetBox(boolean three){
        if(!three){
        System.out.print("Please choose a horse to place 1st or 2nd: ");
        this.horse1 = in.nextInt();
        System.out.print("Please choose a second horse to place 1st or 2nd: ");
        this.horse2 = in.nextInt();
        } else if(three){
            System.out.print("Please choose a horse to place 1st, 2nd, or 3rd: ");
            this.horse1 = in.nextInt();
            System.out.print("Please choose a second horse to place 1st, 2nd, or 3rd: ");
            this.horse2 = in.nextInt();
            System.out.print("Please choose a third horse to place 1st, 2nd, or 3rd: ");
            this.horse3 = in.nextInt();
        }
        while(this.betAmt > wallet){
        int i = 0;
        if(i > 0)
          System.out.println("Invalid Amount");
        System.out.print("Please choose a bet amount: ");
        this.betAmt = in.nextInt();
        i++;
        }
        
        if(!three)
          this.Bet = new Bet(this.betType, this.horse1, this.horse2, this.betAmt);
        else if(three)
          this.Bet = new Bet(this.betType, this.horse1, this.horse2, this.horse3, this.betAmt);
    }

    public void placeBetExTri(boolean three){
        in = new Scanner(System.in);
        System.out.print("Please choose a horse to place 1st: ");
        this.horse1 = in.nextInt();
        System.out.print("Please choose a second horse to place 2nd: ");
        this.horse2 = in.nextInt();
        

        if(three){
            System.out.print("Please choose a third horse to place 3rd: ");
            this.horse3 = in.nextInt();
        }
        while(this.betAmt > wallet){
        System.out.print("Please choose a bet amount: ");
        this.betAmt = in.nextInt();
        }
        if(!three)
          this.Bet = new Bet(this.betType, this.horse1, this.horse2, this.betAmt);
        
        else if(three)
          Bet = new Bet(this.betType, this.horse1, this.horse2, this.horse3, this.betAmt);        

    }

    public void displayOtherBettingInfo(){
        
    }

    public void displayResults(){
        System.out.println("\n\nRace Results");
        System.out.println("------------");
        for(int i=0; i<results.size(); i++){
            System.out.println((i+1) + ": " + results.get(i).getName() + "("+results.get(i).getNumber()+")");
        }
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

            int mover = 5;
            double lengthCheck = horse.getPreferredLength();
            int ratingCheck = 0;
            if (raceSurface == "Mud")
                ratingCheck = horse.getMudRating();
            if (raceSurface == "Grass")
                ratingCheck = horse.getGrassRating();
            if (raceSurface == "Dirt")
                ratingCheck = horse.getDirtRating();
            if (Math.abs(raceLength - lengthCheck) <= 1.5)
                mover += 3;
            if (ratingCheck >= 8)
                mover += 3;
            if (ratingCheck >= 6 && ratingCheck < 8)
                mover += 2;
            if (ratingCheck <= 5)
                mover += 1;
            
            if(!horse.raceFinished() && horse.getCurrentPosition() >= numSpaces){
                results.add(horse);
                horse.setRaceFinished(true);
            } else if(!horse.raceFinished()){
                horse.incrementPosition((int)(Math.random() * mover)); //horse movement
            }

            displayResults();
            

            if (results.size() == horses.size())
                done = true;
        }

        HorseRacingHelper.stopMusic();
    }
    // Other methods for simulating the race, calculating winners, etc., can be added as needed

    private void resetHorses() {
        for (Horse horse : horses) {
            horse.resetCurrenPosition();
        }
    }
}
