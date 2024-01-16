package horseracing;
// hello there wuahosaj

public class Horse{
        private String name;
        private int mudRating;
        private int grassRating;
        private int dirtRating;
        private double preferredLength;
        private int winOdd = 6;
        private int plOdd = 5;
        private int shOdd = 4;
        private int bexOdd = 8;
        private int btriOdd = 7;
        private int exOdd = 10;
        private int triOdd = 9;
        private String win = "-1";
        private String place = "-2";
        private String show = "-2";
        private String boxExacta = "-1";
        private String boxTrifecta = "-1";
        private String exacta = "-1";
        private String trifecta = "-1";


        private int currentPosition;
        private boolean finishedRace;
        private int number;

        public Horse(String name, int mudRating, int grassRating, int dirtRating, double preferredLength) {
            this.name = name;
            this.mudRating = mudRating;
            this.grassRating = grassRating;
            this.dirtRating = dirtRating;
            this.preferredLength = preferredLength;
            this.currentPosition = 2;
            this.finishedRace = false;
            this.number = 0;
        }
        
        public int getLength(){
            return this.name.length();
            
        }
        public void setNumber(int number){
            this.number = number;
        }

        public int getNumber(){
            return this.number;
        }

        public void setRaceFinished(boolean finished){
            finishedRace = finished;
        }

        public boolean raceFinished(){
            return finishedRace;
        }
        public String getName() {
            return name;
        }

        public int getMudRating() {
            return mudRating;
        }

        public int getGrassRating() {
            return grassRating;
        }

        public int getDirtRating() {
            return dirtRating;
        }
        
        public double getPreferredLength() {
            return preferredLength;
        }

        public void incrementPosition(int inc){
            currentPosition += inc;
        }

        public int getCurrentPosition(){
            return currentPosition;
        }

        public void resetCurrenPosition(){
            currentPosition = 2;
            finishedRace = false;
        }

        public void placeBet(){
            
        }

        public String bettingWin() {
            if(HorseRacingHelper.getTerrain() == 0){
                if(getGrassRating() > 7)
                  this.winOdd -= 1;
    
                else if(getGrassRating() < 4)
                  this.winOdd += 1;
    
            } else if(HorseRacingHelper.getTerrain() == 1) {
                if(getDirtRating() > 7)
                  this.winOdd -= 1;
    
                else if(getDirtRating() < 4)
                  this.winOdd += 1;
            } else {
                if(getMudRating() > 7)
                  this.winOdd -= 1;
                else if(getMudRating() < 4)
                  this.winOdd += 1;
            }
    
            if(HorseRacingHelper.getRaceLength().equals("short")){
                if(getPreferredLength() < 7.0)
                  this.winOdd -= 1;
                else if(getPreferredLength() > 8.0)
                  this.winOdd +=1;
            } else if(HorseRacingHelper.getRaceLength().equals("long")){
                if(getPreferredLength() < 7.0)
                  this.winOdd += 1;
                else if(getPreferredLength() > 8.0)
                  this.winOdd -=1;
            }
            return this.winOdd + win;
        }

        public String bettingPlace() {
            if(HorseRacingHelper.getTerrain() == 0){
                if(getGrassRating() > 7)
                  this.plOdd -= 1;
    
                else if(getGrassRating() < 4)
                  this.plOdd += 1;
    
            } else if(HorseRacingHelper.getTerrain() == 1) {
                if(getDirtRating() > 7)
                  this.plOdd -= 1;
    
                else if(getDirtRating() < 4)
                  this.plOdd += 1;
            } else {
                if(getMudRating() > 7)
                  this.plOdd -= 1;
                else if(getMudRating() < 4)
                  this.plOdd += 1;
            }
    
            if(HorseRacingHelper.getRaceLength().equals("short")){
                if(getPreferredLength() < 7.0)
                  this.plOdd -= 1;
                else if(getPreferredLength() > 8.0)
                  this.plOdd +=1;
            } else if(HorseRacingHelper.getRaceLength().equals("long")){
                if(getPreferredLength() < 7.0)
                  this.plOdd += 1;
                else if(getPreferredLength() > 8.0)
                  this.plOdd -=1;
            }
            if(this.plOdd % 2 == 0)
                return this.plOdd / 2 + "-1";
            return this.plOdd + place;
        }
    
        public String bettingShow() {
            if(HorseRacingHelper.getTerrain() == 0){
                if(getGrassRating() > 7)
                  this.shOdd -= 1;
    
                else if(getGrassRating() < 4)
                  this.shOdd += 1;
    
            } else if(HorseRacingHelper.getTerrain() == 1) {
                if(getDirtRating() > 7)
                  this.shOdd -= 1;
    
                else if(getDirtRating() < 4)
                  this.shOdd += 1;
            } else {
                if(getMudRating() > 7)
                  this.shOdd -= 1;
                else if(getMudRating() < 4)
                  this.shOdd += 1;
            }
    
            if(HorseRacingHelper.getRaceLength().equals("short")){
                if(getPreferredLength() < 7.0)
                  this.shOdd -= 1;
                else if(getPreferredLength() > 8.0)
                  this.shOdd +=1;
            } else if(HorseRacingHelper.getRaceLength().equals("long")){
                if(getPreferredLength() < 7.0)
                  this.shOdd += 1;
                else if(getPreferredLength() > 8.0)
                  this.shOdd -=1;
            }
            if(this.shOdd % 2 == 0)
                return this.shOdd / 2 + "-1";
            return this.shOdd + show;
        }
    
        public String bettingBE(){
            return boxExacta;
        }
    
        public String bettingBT() {
            return boxTrifecta;
        }
    
        public String bettingEx(){
            return exacta;
        }
    
        public String bettingTri() {
            return trifecta;
        }
       
    }