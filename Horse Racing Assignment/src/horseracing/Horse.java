package horseracing;
// hello there wuahosaj

public class Horse{
        private String name;
        private int mudRating;
        private int grassRating;
        private int dirtRating;
        private double preferredLength;

        private int winOdd = 5;          // win odds start at 5-1 and go up or down from there
        private int plOdd = 5;           // place odds start at 5-2 and go up or down from there
        private int shOdd = 4;           // show odds start at 4-2 and go up or down from there
        private String win = "-1";
        private String place = "-2";
        private String show = "-2";
        private int wOdd;
        private int pOdd;
        private int sOdd;

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
            return this.name.length();  // get the length of the horse's name
            
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

        /* if the horse's stats for the terrain are higher than 7, 
         * their odds will go down by one.
         * 
         * if the horse's stats for the terrain are lower than 4,
         * their odds will go up by one.
         * 
         * if the horse's preferred length (short, middle, long) matches
         * the race length, their odds will go down by one
         * 
         * if the horse's preferred length is short and the race is long
         * (or vice versa), their odds will go up by one
         * 
         */
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
            wOdd = this.winOdd;
            return this.winOdd + win;
        }

        public int getWinOdds(){
          return wOdd;
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
            if(this.plOdd % 2 == 0){
              pOdd = this.plOdd;
              return this.plOdd / 2 + "-1";
            }
            pOdd = this.plOdd;
            return this.plOdd + place;
        }

        public int getPlaceOdds(){
          return pOdd;
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
            if(this.shOdd % 2 == 0){
              sOdd = this.shOdd;
              return this.shOdd / 2 + "-1";
            }
            sOdd = this.shOdd;
            return this.shOdd + show;
        }

        public int getShowOdds(){
          return sOdd;
        }
    }