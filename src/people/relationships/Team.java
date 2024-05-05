package people.relationships;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import people.Coach;
import people.Wrestler;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {
    private Coach coach;
    private List<Wrestler> wrestlers;

    public Team() {

    }

    public Team(Coach coach, List<Wrestler> wrestlers) {
        this.coach = coach;
        this.wrestlers = wrestlers;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public List<Wrestler> getWrestlers() {
        return wrestlers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(coach, team.coach) && Objects.equals(wrestlers, team.wrestlers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coach, wrestlers);
    }
}
