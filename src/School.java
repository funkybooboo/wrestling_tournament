import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import people.Coach;
import people.Wrestler;
import people.relationships.Names;
import people.relationships.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@JsonIgnoreProperties(ignoreUnknown = true)
public class School {
    private final String schoolName = Names.getNextSchoolName();
    Random random = new Random();
    int coachAgeHigh = 65;
    int coachAgeLow = 25;
    int coachAge;
    int wrestlerAgeHigh = 19;
    int wrestlerAgeLow = 14;
    int wrestlerAge = random.nextInt(wrestlerAgeHigh - wrestlerAgeLow) + wrestlerAgeLow;
    Team team;

    public School() {
        coachAge = random.nextInt(coachAgeHigh - coachAgeLow) + coachAgeLow;
        Coach coach = new Coach(coachAge, Names.getNextPersonName(), schoolName);
        Wrestler wrestler1 = new Wrestler(wrestlerAge, Names.getNextPersonName(), schoolName);
        Wrestler wrestler2 = new Wrestler(wrestlerAge, Names.getNextPersonName(), schoolName);
        Wrestler wrestler3 = new Wrestler(wrestlerAge, Names.getNextPersonName(), schoolName);
        Wrestler wrestler4 = new Wrestler(wrestlerAge, Names.getNextPersonName(), schoolName);
        Wrestler wrestler5 = new Wrestler(wrestlerAge, Names.getNextPersonName(), schoolName);
        List<Wrestler> wrestlers = new ArrayList<>();
        wrestlers.add(wrestler1);
        wrestlers.add(wrestler2);
        wrestlers.add(wrestler3);
        wrestlers.add(wrestler4);
        wrestlers.add(wrestler5);
        team = new Team(coach, wrestlers);
    }

    public Coach getCoach() {
        return team.getCoach();
    }

    public List<Wrestler> getWrestlers() {
        return team.getWrestlers();
    }

    public Team getTeam() {
        return team;
    }

    public String getSchoolName() {
        return schoolName;
    }



}
