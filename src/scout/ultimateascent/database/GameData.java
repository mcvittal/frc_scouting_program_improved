/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scout.ultimateascent.database;

import scout.ultimateascent.coreutils.FileIO;
import scout.ultimateascent.coreutils.Global;
import scout.ultimateascent.gui.ScoutingScreen;

/**
 *
 * @author Alex
 */
public class GameData {
    public Object [] gameData = new Object[19];
    public Object [] rawData = new Object[27];
    public Object [] calculatedData = new Object[20];
    public Object [] blankData = new Object[19];
    
    private static String database = "";    
    private static int auton1;
    private static int auton2 = 0;
    private static int auton3 = 0;
    private static int autonG = 0;
    private static int autonM = 0;    
    private static int tele1 = 0;
    private static int tele2 = 0;
    private static int tele3 = 0;
    private static int tele5 = 0;
    private static int teleM = 0;    
    private static  int fail = 0;
    private static int driverSkill = 0;        
    private static String comments = "";    
    private static int climbAttempted = 0;
    private static int climbSuccessful = 0;
    
    private static double climbTime = 0.0;
    private static int climbPoints = 0;
    
    private static int groundPickup = 0;    
    private static int fullCourt = 0;
    private static int feederStation = 0;
    private static int feedTrips = 0;
    
    private static int defense = 0;
    
    private static int pushRobots = 0;
    private static int blockFCS = 0;
    private static int blockNFCS = 0;
    private static int easyBlock = 0;
    private static int foulPoints = 0;
    private static String path = "";
    private static String team = "";
    private static int victory = 0;
    
    private static double gamesPlayed = 0.0;
    private static double autonAvgScore = 0.0;
    private static double autonAvgGround = 0.0;
    private static double autonAccuracy = 0.0;
    private static double teleopAvg = 0.0;
    private static double teleopAccuracy = 0.0;
    private static double chanceOfFailure = 0.0;
    private static double avgDriverSkill = 0.0;
    private static double avgClimbTime = 0.0;
    private static double avgClimbPoints = 0.0;
    private static double avgFeederTrips = 0.0;
    private static double chanceOfDefense = 0.0;
    private static double avgFouls = 0.0;
    private static double avgWins = 0.0;
    
    public boolean isHidden = false;
    
    
    
    public GameData(String database, String team){
        
        resetData();
        
        this.database = database;
        
        path = Global.prefix + database + "/" +team + "/gameData/";
        
        this.team = team;
        System.out.println(path + "games.txt");
        double [] games = FileIO.getContentsOfNumberFile(path + "games.txt");
        gamesPlayed = games.length / 26;
        
        setValuesFromFile(games);  
        
        calculateValues();
        saveStatsToArray();
        createRawArray();
        createCalculatedArray();
        isHidden();
        blankData[0] = this.team;
        for(int x = 1; x < blankData.length; x++){
            blankData[x] = 0;
        }
        
        
    }
    private GameData(){
        //You are not allowed to create a default constructor.
    }
    
    public String [] getComments(){
        
        return FileIO.getContentsOfFile(Global.prefix + database + "/" + team + "/gameData/comments.txt");
    }
    
    private void createCalculatedArray(){
        calculatedData[0] = autonAvgScore;
        calculatedData[1] = (int) autonAccuracy;
        calculatedData[2] = (int) teleopAvg;
        calculatedData[3] = (int) teleopAccuracy;
        calculatedData[4] = (int) ((fail/gamesPlayed)*100);
        calculatedData[5] = (int) avgDriverSkill;
        calculatedData[6] = (climbTime / gamesPlayed);
        calculatedData[7] = (climbPoints / gamesPlayed);
        
    }
    private void createRawArray(){
        rawData[0] = (int)gamesPlayed;
        rawData[1] = auton1;
        rawData[2] = auton2;
        rawData[3] = auton3;
        rawData[4] = autonG;
        rawData[5] = autonM;
        rawData[6] = tele1;
        rawData[7] = tele2;
        rawData[8] = tele3;
        rawData[9] = tele5;
        rawData[10] = teleM;
        rawData[11] = fail;
        rawData[12] = driverSkill;
        rawData[13] = climbAttempted;
        rawData[14] = climbSuccessful;
        rawData[15] = climbPoints;
        rawData[16] = groundPickup;
        rawData[17] = fullCourt;
        rawData[18] = feederStation;
        rawData[19] = feedTrips;
        rawData[20] = defense;
        rawData[21] = pushRobots;
        rawData[22] = blockFCS;
        rawData[23] = blockNFCS;
        rawData[24] = easyBlock;
        rawData[25] = foulPoints;
        rawData[26] = victory;
        
        
    }
    private void resetData(){
        database = "";
        auton1 = 0;
        auton2 = 0;
        auton3 = 0;
        autonG = 0;
        autonM = 0;
        tele1 = 0;
        tele2 = 0;
        tele3 = 0;
        tele5 = 0;
        teleM = 0;
        fail = 0;
        driverSkill = 0;
        comments = "";
        climbAttempted = 0;
        climbSuccessful = 0;
        climbTime = 0;
        climbPoints = 0;
        groundPickup = 0;
        fullCourt = 0;
        feederStation = 0;
        feedTrips = 0;
        defense = 0;
        pushRobots = 0;
        blockFCS = 0;
        blockNFCS = 0;
        easyBlock = 0;
        foulPoints = 0;
        path = "";
        victory = 0;
        gamesPlayed = 0;
        autonAvgScore = 0;
        autonAvgGround = 0;
        autonAccuracy = 0;
        teleopAvg = 0;
        teleopAccuracy = 0;
        chanceOfFailure = 0;
        avgDriverSkill = 0;
        
        avgClimbTime = 0;
        avgFeederTrips = 0;
        chanceOfDefense = 0;
        avgWins = 0;
    }
    private void calculateValues(){
        autonAvgScore = (auton1 * 2 + auton2 * 4 + auton3 * 6) / gamesPlayed;
        autonAvgGround = autonG / gamesPlayed;
        if(autonM == 0){
            if((auton1 + auton2 + auton3) == 0){
                autonAccuracy = 0;
            }
            else{
                autonAccuracy = 100;
            }
        }
        else{
            autonAccuracy = Math.round((((double) autonM))/(auton1 + auton2 + auton3 + autonM) * 100);
        }
        teleopAvg = (tele1 + tele2 * 2 + tele3 * 3 + tele5 * 5)/ gamesPlayed;
        if(teleM == 0){
            if((tele1 + tele2 + tele3 + tele5) == 0){
                teleopAccuracy = 0;
                
            }
            else{
                
                teleopAccuracy = 50;
                
            }
        }
        else{
            
            teleopAccuracy = Math.round((((double) teleM))/(tele1 + tele2 + tele3 + tele5 + teleM)*100);
            
        }
        chanceOfFailure = (fail / gamesPlayed) * 100;
        avgDriverSkill = driverSkill / gamesPlayed;
        
        avgClimbTime = climbTime / gamesPlayed;
        avgClimbPoints = climbPoints / gamesPlayed;
        avgFeederTrips = feedTrips / gamesPlayed;
        chanceOfDefense = defense / gamesPlayed;
        avgFouls = foulPoints /gamesPlayed;
        avgWins = victory / gamesPlayed;
    }
    private void setValuesFromFile(double [] data){
        for(int y = 0; y < gamesPlayed; y++){
            int x = 0;
            auton1 = (int) (auton1 + data[(x + (y * 27))]);x++;
            auton2 = (int) (auton2 + data[(x + (y * 27))]);x++;
            auton3 = (int) (auton3 + data[(x + (y * 27))]);x++;
            autonG = (int) (autonG + data[(x + (y * 27))]);x++;
            autonM = (int) (autonM + data[(x + (y * 27))]);x++;
            tele1 = (int) (tele1 + data[(x + (y * 27))]);x++;
            tele2 = (int) (tele2 + data[(x + (y * 27))]);x++;
            tele3 = (int) (tele3 + data[(x + (y * 27))]);x++;
            tele5 = (int) (tele5 + data[(x + (y * 27))]);x++;
            teleM = (int) (teleM + data[(x + (y * 27))]);x++;
            fail = (int) (fail + data[(x + (y * 27))]);x++;
            driverSkill = (int)(driverSkill + data[x + (y * 27)]); x++;
            climbAttempted = (int)(climbAttempted + data[x + (y * 27)]); x++;
            climbSuccessful = (int)(climbSuccessful + data[x + (y * 27)]); x++;
            climbTime = (climbTime + data[x + (y * 27)]); x++;
            climbPoints = (int)(climbPoints + data[x + (y * 27)]);x++;
            groundPickup = (int)(groundPickup + data[x + (y * 27)]); x++;
            fullCourt = (int)(fullCourt + data[x + (y * 27)]); x++;
            feederStation = (int)(feederStation + data[x + (y * 27)]); x++;
            feedTrips = (int)(feedTrips + data[x + (y * 27)]); x++;
            defense = (int)(defense + data[x + (y * 27)]); x++;
            pushRobots = (int)(pushRobots + data[x + (y * 27)]); x++;
            blockFCS = (int)(blockFCS + data[x + (y * 27)]); x++;
            blockNFCS = (int)(blockNFCS + data[x + (y * 27)]); x++;
            easyBlock = (int)(easyBlock + data[x + (y * 27)]); x++;
            foulPoints = (int)(foulPoints + data[x + (y * 27)]); x++;
            victory = (int)(victory + data[x + (y * 27)]); x++;          
        }
    }
    private void saveStatsToArray(){
        gameData[0] = Integer.parseInt(team);
        gameData[1] = (int) gamesPlayed;
        gameData[2] = victory;
        gameData[3] = (int)Math.round(tele5/gamesPlayed);
        gameData[4] = (int) Math.round(tele3/gamesPlayed);
        gameData[5] = (int) Math.round(tele2/gamesPlayed);
        gameData[6] = (int) Math.round(tele1/gamesPlayed);
        gameData[7] = (int) Math.round((tele5 + tele3 + tele2 + tele1)/gamesPlayed);
        gameData[8] = (int) Math.round(teleopAccuracy);
        gameData[9] = (int) Math.round(feedTrips);
        gameData[10] = (int) Math.round(auton3/gamesPlayed);
        gameData[11] = (int) Math.round(auton2/gamesPlayed);
        gameData[12] = (int) Math.round(auton1/gamesPlayed);
        gameData[13] = (int) Math.round((auton3 + auton2 + auton1)/gamesPlayed);
        gameData[14] = (int) Math.round(autonAccuracy);
        gameData[15] = (int) Math.round(avgClimbPoints);
        gameData[16] = (int) Math.round(avgDriverSkill);
        if(fullCourt > 0){
            gameData[17] = 1;
        }
        else{
            gameData[17] = 0;
        }
        gameData[18] = (int) Math.round(chanceOfFailure);
        
    }   
    public static void readFromGUI(ScoutingScreen s){
        database = s.path + s.txtTeamNum.getText() + "/";
        path = s.path;
        team = s.txtTeamNum.getText();
        comments = s.txtComments.getText();
        auton1 = s.auton1;
        auton2 = s.auton2;
        auton3 = s.auton3;
        autonG = s.autonGround;
        autonM = s.autonMissed;
        
        tele1 = s.tele1;
        tele2 = s.tele2;
        tele3 = s.tele3;
        tele5 = s.tele5;
        teleM = s.teleMissed;
        if(s.chkMalfunction.isSelected()){
            fail = 1;
        }
        else
        {
            fail = 0;
        }
        driverSkill = Integer.parseInt(s.cboDriverSkill.getSelectedItem().toString());
        comments = s.txtComments.getText() + "<br>";
        if(s.chkClimbAttempted.isSelected()){
            climbAttempted = 1;
            if(s.chkClimbSuccessful.isSelected()){
                climbSuccessful = 1;
                climbTime = Double.parseDouble(s.txtClimbTime.getText());
                climbPoints = Integer.parseInt(s.cboClimb.getSelectedItem().toString());
            }
        }
        if(s.chkGroundPickup.isSelected()){
            groundPickup = 1;
        }
        else
            groundPickup = 0;
        if(s.chkFullCourtShooter.isSelected()){
            fullCourt = 1;
        }
        else
            fullCourt = 0;
        if(s.chkFeederStation.isSelected()){
            feederStation = 1;
            feedTrips = Integer.parseInt(s.cboFeederTrips.getSelectedItem().toString());
        }
        else
        {
            feederStation = 0;
            feedTrips = 0;
        }
        if(s.chkDefense.isSelected()){
            defense = 1;
        }
        else
            defense = 0;
        if(s.chkPush.isSelected()){
            pushRobots = 1;
        }
        else
            pushRobots = 0;
        if(s.chkBlockFullCourtShooters.isSelected()){
            blockFCS = 1;
        }
        else
            blockFCS = 0;
        if(s.chkBlockNonFullCourtShooters.isSelected()){
            blockNFCS = 1;
        }
        else
            blockNFCS = 0;
        if(s.chkEasilyBlocked.isSelected()){
            easyBlock = 1;
        }
        else
            easyBlock = 0;
        foulPoints = Integer.parseInt(s.txtFouls.getText());
        if(s.chkWin.isSelected()){
            victory = 1;
        }
        else
            victory = 0;
        
        FileIO.writeGameToFile(comments, generateArray(), database, path, team);
        
    }
    private static double [] generateArray(){
        double [] session = new double[27];
        session[0] = auton1;
        session[1] = auton2;
        session[2] =auton3;
        session[3] =autonG;
        session[4] =autonM;    
        session[5] =tele1;
        session[6] =tele2;
        session[7] =tele3;
        session[8] =tele5;
        session[9] =teleM;    
        session[10] =fail;
        session[11] =driverSkill;
        session[12] =climbAttempted;
        session[13] =climbSuccessful;
        session[14] =climbTime;
        session[15] =climbPoints;
        session[16]= groundPickup;
        session[17] =fullCourt;
        session[18] =feederStation;
        session[19] =feedTrips;
        session[20] =defense;
        session[21] =pushRobots;
        session[22] =blockFCS;
        session[23] =blockNFCS;
        session[24] =easyBlock;
        session[25] =foulPoints;
        session[26] =victory;
        
        return session;
    }   

    private void isHidden() {
        if(FileIO.getContentsOfFile(Global.prefix + database + "/" + team + "/gameData/isHidden.txt")[0].equals("false")){
            isHidden = false;
        }
        else
            isHidden = true;
    }
}