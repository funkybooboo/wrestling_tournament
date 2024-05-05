package people;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coach extends Person {
    private int numOfTimesWon = 0;
    private boolean isTeamStillInTournament = true;

    public Coach() {

    }

    public Coach(int age, String name, String school) {
        super(age, name, school);
    }

    public int getNumOfTimesWon() {
        return numOfTimesWon;
    }
    public void addAWinForTheTeam() {
        numOfTimesWon++;
    }

    public boolean getIsTeamStillInTournament() {
        return isTeamStillInTournament;
    }
    public void teamIsOutOfTournament() {
        isTeamStillInTournament = false;
    }

}
