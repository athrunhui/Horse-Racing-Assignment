package horseracing;

import java.util.ArrayList;
import java.util.List;

public class Race {
    private List<Horse> horses;
    private double raceLength; // in furlongs
    private String raceSurface; // "grass", "dirt", or "mud" (Uses HorseRacingHelper constants)
    private int currentHorse;
    private String spaceM;
    private String spaceG;
    private String spaceD;
    private String spaceL;
    private int wallet = 20;
    
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
            if(horse.getNumber() == 10)
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
            System.out.print("|" + horse.bettingShow());            
            /*System.out.print("|" + horse.bettingShow());
            System.out.print("|" + horse.bettingShow());
            System.out.println("|" + horse.bettingShow() + "|");*/
        }
        System.out.println("+----------------------------------------------------------------------+");

    }

    public void displayBoxExInfo(){
        
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
           

            if(!horse.raceFinished() && horse.getCurrentPosition() >= numSpaces){
                results.add(horse);
                horse.setRaceFinished(true);
            } else if(!horse.raceFinished()){
                horse.incrementPosition((int)(Math.random() * 4)); //horse movement
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
