import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import people.Coach;
import people.Wrestler;
import people.relationships.Names;
import people.relationships.Team;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.*;

public class Tournament {

    public static void main(String[] args) throws FileNotFoundException {
        // Hold Tryouts and get teams
        GameState gameState = beginningPrompt();
        Team usersTeam;
        List<School> schools;
        if (gameState != null) {
            usersTeam = gameState.getUserTeam();
            schools = gameState.getSchools();
        } else {
            usersTeam = getUsersTeam();
            schools = getOtherSchoolsTeam();
        }
        setOtherSchoolsStats(schools);
        checkPointMenu(usersTeam, schools);
        System.out.println("Everything is ready, lets start your practices");
        System.out.println();
        getEnterFromUserToContinue();
        clearScreen();
        //hold 3 practices before season starts
        holdThreePractices(usersTeam);
        for (School school : schools) computerHoldThreePractices(school.getTeam());
        checkPointMenu(usersTeam, schools);
        System.out.println("Now the game season will start");
        System.out.println();
        getEnterFromUserToContinue();
        clearScreen();
        //start the game season
        for (School school : schools) {
            Team otherSchoolsTeam = school.getTeam();
            int result = match(usersTeam, otherSchoolsTeam);
            if (result == 1) {
                Coach coach = usersTeam.getCoach();
                coach.addAWinForTheTeam();
                System.out.println(usersTeam.getCoach().getSchool() + " Won " + " | vs | " + otherSchoolsTeam.getCoach().getSchool() + " Lost");
            } else {
                Coach coach = otherSchoolsTeam.getCoach();
                coach.addAWinForTheTeam();
                System.out.println(usersTeam.getCoach().getSchool() + " Lost " + " | vs | " + otherSchoolsTeam.getCoach().getSchool() + " Won");
            }
            getEnterFromUserToContinue();
            practice(usersTeam);
            computerPractice(otherSchoolsTeam);
            checkPointMenu(usersTeam, schools);
        }
        System.out.println("Ok now lets let the other schools wrestle");
        getEnterFromUserToContinue();
        for (School school : schools) {
            Team school1Team = school.getTeam();
            for (School otherSchool : schools) {
                Team school2Team = otherSchool.getTeam();
                if (school1Team != school2Team) {
                    int result;
                    result = match(school1Team, school2Team);
                    if (result == 1) {
                        Coach coach = school1Team.getCoach();
                        coach.addAWinForTheTeam();
                        System.out.println(school1Team.getCoach().getSchool() + " Won " + " | vs | " + school2Team.getCoach().getSchool() + " Lost");
                    } else {
                        Coach coach = school2Team.getCoach();
                        coach.addAWinForTheTeam();
                        System.out.println(school1Team.getCoach().getSchool() + " Lost " + " | vs | " + school2Team.getCoach().getSchool() + " Won");
                    }
                    computerPractice(school1Team);
                    computerPractice(school2Team);
                }
            }
            System.out.println();
        }
        System.out.println("Ok their done");
        getEnterFromUserToContinue();
        clearScreen();
        System.out.println("Results from Game Season");
        Coach coachForUser = usersTeam.getCoach();
        System.out.println(usersTeam.getCoach().getSchool() + " won " + coachForUser.getNumOfTimesWon() + " times");
        for (int i = 0; i < usersTeam.getWrestlers().size(); i++) {
            Wrestler wrestler = usersTeam.getWrestlers().get(i);
            System.out.println("\t" + wrestler.getName() + " won " + wrestler.getNumOfFightsWon()+ " times");
        }
        System.out.println();
            // rank teams for tournament
        List<Integer> rankings = new ArrayList<>();
        rankings.add(coachForUser.getNumOfTimesWon());
        for (School school : schools) {
            List<Wrestler> otherSchoolWrestlers = school.getWrestlers();
            Team otherSchoolTeam = school.getTeam();
            Coach coachForOtherSchool = otherSchoolTeam.getCoach();
            int timesWon = coachForOtherSchool.getNumOfTimesWon();
            while (rankings.contains(timesWon)) {
                timesWon++;
                coachForOtherSchool.addAWinForTheTeam();
            }
            rankings.add(timesWon);
            System.out.println(school.getCoach().getSchool() + " won " + coachForOtherSchool.getNumOfTimesWon() + " times");
            for (Wrestler wrestler : otherSchoolWrestlers) {
                System.out.println("\t" + wrestler.getName() + " won " + wrestler.getNumOfFightsWon() + " times");
            }
            System.out.println();
        }
        checkPointMenu(usersTeam, schools);
        //start the tournament
        System.out.println("Now its time for the tournament\n");
        getEnterFromUserToContinue();
        clearScreen();
            //sort rankings
        List<Integer> sortedRankings = getNumberOfTimesWonSorted(rankings);
        int usersRankIndex = linearSearch(sortedRankings, coachForUser.getNumOfTimesWon());
        int school1RankIndex = 0;
        int school2RankIndex = 0;
        int school3RankIndex = 0;
        int school4RankIndex = 0;
        int school5RankIndex = 0;
        int school6RankIndex = 0;
        int school7RankIndex = 0;
        int school8RankIndex = 0;
        int school9RankIndex = 0;
        int school10RankIndex = 0;
        int school11RankIndex = 0;
        int count = 1;
        for (School school : schools) {
            Coach coach = school.getCoach();
            if (count == 1) {
                school1RankIndex = linearSearch(sortedRankings, coach.getNumOfTimesWon());
            } else if (count == 2) {
                school2RankIndex = linearSearch(sortedRankings, coach.getNumOfTimesWon());
            } else if (count == 3) {
                school3RankIndex = linearSearch(sortedRankings, coach.getNumOfTimesWon());
            } else if (count == 4) {
                school4RankIndex = linearSearch(sortedRankings, coach.getNumOfTimesWon());
            } else if (count == 5) {
                school5RankIndex = linearSearch(sortedRankings, coach.getNumOfTimesWon());
            } else if (count == 6) {
                school6RankIndex = linearSearch(sortedRankings, coach.getNumOfTimesWon());
            } else if (count == 7) {
                school7RankIndex = linearSearch(sortedRankings, coach.getNumOfTimesWon());
            } else if (count == 8) {
                school8RankIndex = linearSearch(sortedRankings, coach.getNumOfTimesWon());
            } else if (count == 9) {
                school9RankIndex = linearSearch(sortedRankings, coach.getNumOfTimesWon());
            } else if (count == 10) {
                school10RankIndex = linearSearch(sortedRankings, coach.getNumOfTimesWon());
            } else {
                school11RankIndex = linearSearch(sortedRankings, coach.getNumOfTimesWon());
            }
            count++;
        }
        Team[] schoolsRanked2DArray = new Team[12];
        Wrestler[] userSchoolAsObject = usersTeam.getWrestlers().toArray(new Wrestler[usersTeam.getWrestlers().size()]);
        List<Wrestler> userSchool = new ArrayList<>();
        count = 0;
        for (Wrestler o : userSchoolAsObject) {
            userSchool.add(o);
            count++;
        }
        Team schoolTeam = new Team(usersTeam.getCoach(), userSchool);
        schoolsRanked2DArray[usersRankIndex] = schoolTeam;
        count = 1;
        for (School school : schools) {
            Team team = school.getTeam();
            if (count == 1) {
                schoolsRanked2DArray[school1RankIndex] = team;
            } else if (count == 2) {
                schoolsRanked2DArray[school2RankIndex] = team;
            } else if (count == 3) {
                schoolsRanked2DArray[school3RankIndex] = team;
            } else if (count == 4) {
                schoolsRanked2DArray[school4RankIndex] = team;
            } else if (count == 5) {
                schoolsRanked2DArray[school5RankIndex] = team;
            } else if (count == 6) {
                schoolsRanked2DArray[school6RankIndex] = team;
            } else if (count == 7) {
                schoolsRanked2DArray[school7RankIndex] = team;
            } else if (count == 8) {
                schoolsRanked2DArray[school8RankIndex] = team;
            } else if (count == 9) {
                schoolsRanked2DArray[school9RankIndex] = team;
            } else if (count == 10) {
                schoolsRanked2DArray[school10RankIndex] = team;
            } else {
                schoolsRanked2DArray[school11RankIndex] = team;
            }
            count++;
        }
        List<Team> rankedSchools = new ArrayList<>(Arrays.asList(schoolsRanked2DArray));
        List<Integer> teamIndexesForRankedSchools = new ArrayList<>();
        teamIndexesForRankedSchools.add(usersRankIndex);
        teamIndexesForRankedSchools.add(school1RankIndex);
        teamIndexesForRankedSchools.add(school2RankIndex);
        teamIndexesForRankedSchools.add(school3RankIndex);
        teamIndexesForRankedSchools.add(school4RankIndex);
        teamIndexesForRankedSchools.add(school5RankIndex);
        teamIndexesForRankedSchools.add(school6RankIndex);
        teamIndexesForRankedSchools.add(school7RankIndex);
        teamIndexesForRankedSchools.add(school8RankIndex);
        teamIndexesForRankedSchools.add(school9RankIndex);
        teamIndexesForRankedSchools.add(school10RankIndex);
        teamIndexesForRankedSchools.add(school11RankIndex);
        findWinnerOfTournament(schools, coachForUser, teamIndexesForRankedSchools, rankedSchools, usersTeam);
        // finish game
    }

    private static List<Integer> moveIndexes(List<Integer> teamIndexesForRankedSchools, int indexToCheck) {
        int usersRankIndex = teamIndexesForRankedSchools.get(0);
        int school1RankIndex = teamIndexesForRankedSchools.get(1);
        int school2RankIndex = teamIndexesForRankedSchools.get(2);
        int school3RankIndex = teamIndexesForRankedSchools.get(3);
        int school4RankIndex = teamIndexesForRankedSchools.get(4);
        int school5RankIndex = teamIndexesForRankedSchools.get(5);
        int school6RankIndex = teamIndexesForRankedSchools.get(6);
        int school7RankIndex = teamIndexesForRankedSchools.get(7);
        int school8RankIndex = teamIndexesForRankedSchools.get(8);
        int school9RankIndex = teamIndexesForRankedSchools.get(9);
        int school10RankIndex = teamIndexesForRankedSchools.get(10);
        int school11RankIndex = teamIndexesForRankedSchools.get(11);
        if (usersRankIndex >= indexToCheck) {
            usersRankIndex--;
        }
        if (school1RankIndex >= indexToCheck) {
            school1RankIndex--;
        }
        if (school2RankIndex >= indexToCheck) {
            school2RankIndex--;
        }
        if (school3RankIndex >= indexToCheck) {
            school3RankIndex--;
        }
        if (school4RankIndex >= indexToCheck) {
            school4RankIndex--;
        }
        if (school5RankIndex >= indexToCheck) {
            school5RankIndex--;
        }
        if (school6RankIndex >= indexToCheck) {
            school6RankIndex--;
        }
        if (school7RankIndex >= indexToCheck) {
            school7RankIndex--;
        }
        if (school8RankIndex >= indexToCheck) {
            school8RankIndex--;
        }
        if (school9RankIndex >= indexToCheck) {
            school9RankIndex--;
        }
        if (school10RankIndex >= indexToCheck) {
            school10RankIndex--;
        }
        if (school11RankIndex >= indexToCheck) {
            school11RankIndex--;
        }
        List<Integer> updatedIndexes = new ArrayList<>();
        updatedIndexes.add(usersRankIndex);
        updatedIndexes.add(school1RankIndex);
        updatedIndexes.add(school2RankIndex);
        updatedIndexes.add(school3RankIndex);
        updatedIndexes.add(school4RankIndex);
        updatedIndexes.add(school5RankIndex);
        updatedIndexes.add(school6RankIndex);
        updatedIndexes.add(school7RankIndex);
        updatedIndexes.add(school8RankIndex);
        updatedIndexes.add(school9RankIndex);
        updatedIndexes.add(school10RankIndex);
        updatedIndexes.add(school11RankIndex);
        teamIndexesForRankedSchools.clear();
        teamIndexesForRankedSchools.addAll(updatedIndexes);
        return updatedIndexes;
    }

    private static List<Integer> updateIndex(List<School> schools, Coach coachForUser, List<Team> rankedSchools, List<Integer> teamIndexesForRankedSchools, Team usersTeam) {
        int usersRankIndex = teamIndexesForRankedSchools.get(0);
        int school1RankIndex = teamIndexesForRankedSchools.get(1);
        int school2RankIndex = teamIndexesForRankedSchools.get(2);
        int school3RankIndex = teamIndexesForRankedSchools.get(3);
        int school4RankIndex = teamIndexesForRankedSchools.get(4);
        int school5RankIndex = teamIndexesForRankedSchools.get(5);
        int school6RankIndex = teamIndexesForRankedSchools.get(6);
        int school7RankIndex = teamIndexesForRankedSchools.get(7);
        int school8RankIndex = teamIndexesForRankedSchools.get(8);
        int school9RankIndex = teamIndexesForRankedSchools.get(9);
        int school10RankIndex = teamIndexesForRankedSchools.get(10);
        int school11RankIndex = teamIndexesForRankedSchools.get(11);
        List<Integer> updatedIndexes;
        if (!coachForUser.getIsTeamStillInTournament() && rankedSchools.contains(usersTeam)) {
            rankedSchools.remove(usersRankIndex);
            updatedIndexes = moveIndexes(teamIndexesForRankedSchools, usersRankIndex);
            usersRankIndex = updatedIndexes.get(0);
            school1RankIndex = updatedIndexes.get(1);
            school2RankIndex = updatedIndexes.get(2);
            school3RankIndex = updatedIndexes.get(3);
            school4RankIndex = updatedIndexes.get(4);
            school5RankIndex = updatedIndexes.get(5);
            school6RankIndex = updatedIndexes.get(6);
            school7RankIndex = updatedIndexes.get(7);
            school8RankIndex = updatedIndexes.get(8);
            school9RankIndex = updatedIndexes.get(9);
            school10RankIndex = updatedIndexes.get(10);
            school11RankIndex = updatedIndexes.get(11);
        }
        int count = 1;
        for (School school : schools) {
            if (!school.getCoach().getIsTeamStillInTournament() && rankedSchools.contains(school.team)) {
                if (count == 1) {
                    rankedSchools.remove(school1RankIndex);
                    updatedIndexes = moveIndexes(teamIndexesForRankedSchools, school1RankIndex);
                    usersRankIndex = updatedIndexes.get(0);
                    school1RankIndex = updatedIndexes.get(1);
                    school2RankIndex = updatedIndexes.get(2);
                    school3RankIndex = updatedIndexes.get(3);
                    school4RankIndex = updatedIndexes.get(4);
                    school5RankIndex = updatedIndexes.get(5);
                    school6RankIndex = updatedIndexes.get(6);
                    school7RankIndex = updatedIndexes.get(7);
                    school8RankIndex = updatedIndexes.get(8);
                    school9RankIndex = updatedIndexes.get(9);
                    school10RankIndex = updatedIndexes.get(10);
                    school11RankIndex = updatedIndexes.get(11);
                } else if (count == 2) {
                    rankedSchools.remove(school2RankIndex);
                    updatedIndexes = moveIndexes(teamIndexesForRankedSchools, school2RankIndex);
                    usersRankIndex = updatedIndexes.get(0);
                    school1RankIndex = updatedIndexes.get(1);
                    school2RankIndex = updatedIndexes.get(2);
                    school3RankIndex = updatedIndexes.get(3);
                    school4RankIndex = updatedIndexes.get(4);
                    school5RankIndex = updatedIndexes.get(5);
                    school6RankIndex = updatedIndexes.get(6);
                    school7RankIndex = updatedIndexes.get(7);
                    school8RankIndex = updatedIndexes.get(8);
                    school9RankIndex = updatedIndexes.get(9);
                    school10RankIndex = updatedIndexes.get(10);
                    school11RankIndex = updatedIndexes.get(11);
                } else if (count == 3) {
                    rankedSchools.remove(school3RankIndex);
                    updatedIndexes = moveIndexes(teamIndexesForRankedSchools, school3RankIndex);
                    usersRankIndex = updatedIndexes.get(0);
                    school1RankIndex = updatedIndexes.get(1);
                    school2RankIndex = updatedIndexes.get(2);
                    school3RankIndex = updatedIndexes.get(3);
                    school4RankIndex = updatedIndexes.get(4);
                    school5RankIndex = updatedIndexes.get(5);
                    school6RankIndex = updatedIndexes.get(6);
                    school7RankIndex = updatedIndexes.get(7);
                    school8RankIndex = updatedIndexes.get(8);
                    school9RankIndex = updatedIndexes.get(9);
                    school10RankIndex = updatedIndexes.get(10);
                    school11RankIndex = updatedIndexes.get(11);
                } else if (count == 4) {
                    rankedSchools.remove(school4RankIndex);
                    updatedIndexes = moveIndexes(teamIndexesForRankedSchools, school4RankIndex);
                    usersRankIndex = updatedIndexes.get(0);
                    school1RankIndex = updatedIndexes.get(1);
                    school2RankIndex = updatedIndexes.get(2);
                    school3RankIndex = updatedIndexes.get(3);
                    school4RankIndex = updatedIndexes.get(4);
                    school5RankIndex = updatedIndexes.get(5);
                    school6RankIndex = updatedIndexes.get(6);
                    school7RankIndex = updatedIndexes.get(7);
                    school8RankIndex = updatedIndexes.get(8);
                    school9RankIndex = updatedIndexes.get(9);
                    school10RankIndex = updatedIndexes.get(10);
                    school11RankIndex = updatedIndexes.get(11);
                } else if (count == 5) {
                    rankedSchools.remove(school5RankIndex);
                    updatedIndexes = moveIndexes(teamIndexesForRankedSchools, school5RankIndex);
                    usersRankIndex = updatedIndexes.get(0);
                    school1RankIndex = updatedIndexes.get(1);
                    school2RankIndex = updatedIndexes.get(2);
                    school3RankIndex = updatedIndexes.get(3);
                    school4RankIndex = updatedIndexes.get(4);
                    school5RankIndex = updatedIndexes.get(5);
                    school6RankIndex = updatedIndexes.get(6);
                    school7RankIndex = updatedIndexes.get(7);
                    school8RankIndex = updatedIndexes.get(8);
                    school9RankIndex = updatedIndexes.get(9);
                    school10RankIndex = updatedIndexes.get(10);
                    school11RankIndex = updatedIndexes.get(11);
                } else if (count == 6) {
                    rankedSchools.remove(school6RankIndex);
                    updatedIndexes = moveIndexes(teamIndexesForRankedSchools, school6RankIndex);
                    usersRankIndex = updatedIndexes.get(0);
                    school1RankIndex = updatedIndexes.get(1);
                    school2RankIndex = updatedIndexes.get(2);
                    school3RankIndex = updatedIndexes.get(3);
                    school4RankIndex = updatedIndexes.get(4);
                    school5RankIndex = updatedIndexes.get(5);
                    school6RankIndex = updatedIndexes.get(6);
                    school7RankIndex = updatedIndexes.get(7);
                    school8RankIndex = updatedIndexes.get(8);
                    school9RankIndex = updatedIndexes.get(9);
                    school10RankIndex = updatedIndexes.get(10);
                    school11RankIndex = updatedIndexes.get(11);
                } else if (count == 7) {
                    rankedSchools.remove(school7RankIndex);
                    updatedIndexes = moveIndexes(teamIndexesForRankedSchools, school7RankIndex);
                    usersRankIndex = updatedIndexes.get(0);
                    school1RankIndex = updatedIndexes.get(1);
                    school2RankIndex = updatedIndexes.get(2);
                    school3RankIndex = updatedIndexes.get(3);
                    school4RankIndex = updatedIndexes.get(4);
                    school5RankIndex = updatedIndexes.get(5);
                    school6RankIndex = updatedIndexes.get(6);
                    school7RankIndex = updatedIndexes.get(7);
                    school8RankIndex = updatedIndexes.get(8);
                    school9RankIndex = updatedIndexes.get(9);
                    school10RankIndex = updatedIndexes.get(10);
                    school11RankIndex = updatedIndexes.get(11);
                } else if (count == 8) {
                    rankedSchools.remove(school8RankIndex);
                    updatedIndexes = moveIndexes(teamIndexesForRankedSchools, school8RankIndex);
                    usersRankIndex = updatedIndexes.get(0);
                    school1RankIndex = updatedIndexes.get(1);
                    school2RankIndex = updatedIndexes.get(2);
                    school3RankIndex = updatedIndexes.get(3);
                    school4RankIndex = updatedIndexes.get(4);
                    school5RankIndex = updatedIndexes.get(5);
                    school6RankIndex = updatedIndexes.get(6);
                    school7RankIndex = updatedIndexes.get(7);
                    school8RankIndex = updatedIndexes.get(8);
                    school9RankIndex = updatedIndexes.get(9);
                    school10RankIndex = updatedIndexes.get(10);
                    school11RankIndex = updatedIndexes.get(11);
                } else if (count == 9) {
                    rankedSchools.remove(school9RankIndex);
                    updatedIndexes = moveIndexes(teamIndexesForRankedSchools, school9RankIndex);
                    usersRankIndex = updatedIndexes.get(0);
                    school1RankIndex = updatedIndexes.get(1);
                    school2RankIndex = updatedIndexes.get(2);
                    school3RankIndex = updatedIndexes.get(3);
                    school4RankIndex = updatedIndexes.get(4);
                    school5RankIndex = updatedIndexes.get(5);
                    school6RankIndex = updatedIndexes.get(6);
                    school7RankIndex = updatedIndexes.get(7);
                    school8RankIndex = updatedIndexes.get(8);
                    school9RankIndex = updatedIndexes.get(9);
                    school10RankIndex = updatedIndexes.get(10);
                    school11RankIndex = updatedIndexes.get(11);
                } else if (count == 10) {
                    rankedSchools.remove(school10RankIndex);
                    updatedIndexes = moveIndexes(teamIndexesForRankedSchools, school10RankIndex);
                    usersRankIndex = updatedIndexes.get(0);
                    school1RankIndex = updatedIndexes.get(1);
                    school2RankIndex = updatedIndexes.get(2);
                    school3RankIndex = updatedIndexes.get(3);
                    school4RankIndex = updatedIndexes.get(4);
                    school5RankIndex = updatedIndexes.get(5);
                    school6RankIndex = updatedIndexes.get(6);
                    school7RankIndex = updatedIndexes.get(7);
                    school8RankIndex = updatedIndexes.get(8);
                    school9RankIndex = updatedIndexes.get(9);
                    school10RankIndex = updatedIndexes.get(10);
                    school11RankIndex = updatedIndexes.get(11);
                } else {
                    rankedSchools.remove(school11RankIndex);
                    updatedIndexes = moveIndexes(teamIndexesForRankedSchools, school11RankIndex);
                    usersRankIndex = updatedIndexes.get(0);
                    school1RankIndex = updatedIndexes.get(1);
                    school2RankIndex = updatedIndexes.get(2);
                    school3RankIndex = updatedIndexes.get(3);
                    school4RankIndex = updatedIndexes.get(4);
                    school5RankIndex = updatedIndexes.get(5);
                    school6RankIndex = updatedIndexes.get(6);
                    school7RankIndex = updatedIndexes.get(7);
                    school8RankIndex = updatedIndexes.get(8);
                    school9RankIndex = updatedIndexes.get(9);
                    school10RankIndex = updatedIndexes.get(10);
                    school11RankIndex = updatedIndexes.get(11);
                }
            }
            count++;
        }
        List<Integer> finishedUpdatedIndexes = new ArrayList<>();
        finishedUpdatedIndexes.add(usersRankIndex);
        finishedUpdatedIndexes.add(school1RankIndex);
        finishedUpdatedIndexes.add(school2RankIndex);
        finishedUpdatedIndexes.add(school3RankIndex);
        finishedUpdatedIndexes.add(school4RankIndex);
        finishedUpdatedIndexes.add(school5RankIndex);
        finishedUpdatedIndexes.add(school6RankIndex);
        finishedUpdatedIndexes.add(school7RankIndex);
        finishedUpdatedIndexes.add(school8RankIndex);
        finishedUpdatedIndexes.add(school9RankIndex);
        finishedUpdatedIndexes.add(school10RankIndex);
        finishedUpdatedIndexes.add(school11RankIndex);
        return finishedUpdatedIndexes;
    }

    private static void findWinnerOfTournament(List<School> schools, Coach coachForUser, List<Integer> teamIndexesForRankedSchools, List<Team> rankedSchools, Team usersTeam) {
        System.out.println("First matches are starting with 12 schools\n");
        tournamentMatch(rankedSchools.get(0), rankedSchools.get(11));
        tournamentMatch(rankedSchools.get(1), rankedSchools.get(10));
        tournamentMatch(rankedSchools.get(2), rankedSchools.get(9));
        tournamentMatch(rankedSchools.get(3), rankedSchools.get(8));
        tournamentMatch(rankedSchools.get(4), rankedSchools.get(7));
        tournamentMatch(rankedSchools.get(5), rankedSchools.get(6));
        List<Integer> updatedIndexes1 = updateIndex(schools, coachForUser, rankedSchools, teamIndexesForRankedSchools, usersTeam);
        System.out.println("Second round of matches with 6 schools left\n");
        tournamentMatch(rankedSchools.get(0), rankedSchools.get(5));
        tournamentMatch(rankedSchools.get(1), rankedSchools.get(4));
        tournamentMatch(rankedSchools.get(2), rankedSchools.get(3));
        List<Integer> updatedIndexes2 = updateIndex(schools, coachForUser, rankedSchools, updatedIndexes1, usersTeam);
        System.out.println("Third round of matches with 3 schools left\n");
        tournamentMatch(rankedSchools.get(0), rankedSchools.get(1));
        List<Integer> updatedIndexes3 = updateIndex(schools, coachForUser, rankedSchools, updatedIndexes2, usersTeam);
        System.out.println("Fourth round of matches with 2 schools left\n");
        tournamentMatch(rankedSchools.get(0), rankedSchools.get(1));
        List<Integer> updatedIndexes4 = updateIndex(schools, coachForUser, rankedSchools, updatedIndexes3, usersTeam);
        System.out.println("We have a winner of the Wrestling Tournament");
        getEnterFromUserToContinue();
        clearScreen();
        System.out.println(rankedSchools.get(0).getCoach().getSchool() + " is the Winner");
        System.out.println("Thanks for playing :)");
    }

    private static int linearSearch(List<Integer> list, int key){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i) == key){
                return i;
            }
        }
        return -1;
    }

    private static List<Integer> getNumberOfTimesWonSorted(List<Integer> rankings) {
        Object[] rankingsArrayAsObject = rankings.toArray();
        int[] rankingsArray = new int[12];
        int count = 0;
        for (Object o : rankingsArrayAsObject) {
            int i = (int) o;
            rankingsArray[count] = i;
            count++;
        }
        quicksort(rankingsArray);
        rankings.clear();
        for (int i : rankingsArray) {
            rankings.add(i);
        }
        return rankings;
    }

    private static void tournamentMatch(Team team1, Team team2) {
        int result;
        result = match(team1, team2);
        if (result == 1) {
            Coach coach = team2.getCoach();
            coach.teamIsOutOfTournament();
        } else {
            Coach coach = team1.getCoach();
            coach.teamIsOutOfTournament();
        }
    }

    private static int match(Team team1, Team team2) {
        int numOfTimesTeam1Won = 0;
        int numOfTimesTeam2Won = 0;
        List<Wrestler> wrestlers1 = team1.getWrestlers();
        List<Wrestler> sortedWrestlers1 = sortWrestlersFromWorstToBest(wrestlers1);
        // 5 = worst
        // 1 = best
        Wrestler team1Wrestler5 = sortedWrestlers1.get(0);
        Wrestler team1Wrestler4 = sortedWrestlers1.get(1);
        Wrestler team1Wrestler3 = sortedWrestlers1.get(2);
        Wrestler team1Wrestler2 = sortedWrestlers1.get(3);
        Wrestler team1Wrestler1 = sortedWrestlers1.get(4);
        List<Wrestler> wrestlers2 = team2.getWrestlers();
        List<Wrestler> sortedWrestlers2 = sortWrestlersFromWorstToBest(wrestlers2);
        // 5 = worst
        // 1 = best
        Wrestler team2Wrestler5 = sortedWrestlers2.get(0);
        Wrestler team2Wrestler4 = sortedWrestlers2.get(1);
        Wrestler team2Wrestler3 = sortedWrestlers2.get(2);
        Wrestler team2Wrestler2 = sortedWrestlers2.get(3);
        Wrestler team2Wrestler1 = sortedWrestlers2.get(4);
        int result1 = wrestle(team1Wrestler1, team2Wrestler1);
        int result2 = wrestle(team1Wrestler2, team2Wrestler2);
        int result3 = wrestle(team1Wrestler3, team2Wrestler3);
        int result4 = wrestle(team1Wrestler4, team2Wrestler4);
        int result5 = wrestle(team1Wrestler5, team2Wrestler5);
        // 1 = team1 won
        // 2 = team2 won
        if (result1 == 1) {
            numOfTimesTeam1Won++;
        } else {
            numOfTimesTeam2Won++;
        }
        if (result2 == 1) {
            numOfTimesTeam1Won++;
        } else {
            numOfTimesTeam2Won++;
        }
        if (result3 == 1) {
            numOfTimesTeam1Won++;
        } else {
            numOfTimesTeam2Won++;
        }
        if (result4 == 1) {
            numOfTimesTeam1Won++;
        } else {
            numOfTimesTeam2Won++;
        }
        if (result5 == 1) {
            numOfTimesTeam1Won++;
        } else {
            numOfTimesTeam2Won++;
        }
        if (numOfTimesTeam1Won > numOfTimesTeam2Won) {
            return 1;
        } else {
            return 2;
        }
    }

    private static int wrestle(Wrestler wrestler1, Wrestler wrestler2) {
        int numOfTimesWrestler1Won = 0;
        int numOfTimesWrestler2Won = 0;
        Random random = new Random();
        int wrestler1RandomNum = random.nextInt(100);
        int wrestler2RandomNum = random.nextInt(100);
        if (wrestler1RandomNum > wrestler2RandomNum) {
            wrestler1.changeMoral(1);
            numOfTimesWrestler1Won++;
        } else {
            wrestler2.changeMoral(1);
            numOfTimesWrestler2Won++;
        }
        if (wrestler1.getHeight() > wrestler2.getHeight()) {
            wrestler1.changeMoral(1);
            numOfTimesWrestler1Won++;
        } else {
            wrestler2.changeMoral(1);
            numOfTimesWrestler2Won++;
        }
        if (wrestler1.getWeight() > wrestler2.getWeight()) {
            wrestler1.changeMoral(1);
            numOfTimesWrestler1Won++;
        } else {
            wrestler2.changeMoral(1);
            numOfTimesWrestler2Won++;
        }
        if (wrestler1.getBackArchScore() > wrestler2.getBackArchScore()) {
            wrestler1.changeMoral(1);
            numOfTimesWrestler1Won++;
        } else {
            wrestler2.changeMoral(1);
            numOfTimesWrestler2Won++;
        }
        if (wrestler1.getBackStepScore() > wrestler2.getBackStepScore()) {
            wrestler1.changeMoral(1);
            numOfTimesWrestler1Won++;
        } else {
            wrestler2.changeMoral(1);
            numOfTimesWrestler2Won++;
        }
        if (wrestler1.getLevelChangeScore() > wrestler2.getLevelChangeScore()) {
            wrestler1.changeMoral(1);
            numOfTimesWrestler1Won++;
        } else {
            wrestler2.changeMoral(1);
            numOfTimesWrestler2Won++;
        }
        if (wrestler1.getLiftingScore() > wrestler2.getLiftingScore()) {
            wrestler1.changeMoral(1);
            numOfTimesWrestler1Won++;
        } else {
            wrestler2.changeMoral(1);
            numOfTimesWrestler2Won++;
        }
        if (wrestler1.getMotionScore() > wrestler2.getMotionScore()) {
            wrestler1.changeMoral(1);
            numOfTimesWrestler1Won++;
        } else {
            wrestler2.changeMoral(1);
            numOfTimesWrestler2Won++;
        }
        if (wrestler1.getPenetrationScore() > wrestler2.getPenetrationScore()) {
            wrestler1.changeMoral(1);
            numOfTimesWrestler1Won++;
        } else {
            wrestler2.changeMoral(1);
            numOfTimesWrestler2Won++;
        }
        if (wrestler1.getStanceScore() > wrestler2.getStanceScore()) {
            wrestler1.changeMoral(1);
            numOfTimesWrestler1Won++;
        } else {
            wrestler2.changeMoral(1);
            numOfTimesWrestler2Won++;
        }
        if (wrestler1.getMoral() > wrestler2.getMoral()) {
            numOfTimesWrestler1Won++;
        } else {
            numOfTimesWrestler2Won++;
        }
        if (numOfTimesWrestler1Won > numOfTimesWrestler2Won) {
            wrestler1.addAWin();
            return 1;
        } else {
            wrestler2.addAWin();
            return 2;
        }
    }

    private static List<Wrestler> sortWrestlersFromWorstToBest(List<Wrestler> wrestlers) {
        int wrestlerScore1 = wrestlers.get(0).getOverAllScore();
        int wrestlerScore2 = wrestlers.get(1).getOverAllScore();
        int wrestlerScore3 = wrestlers.get(2).getOverAllScore();
        int wrestlerScore4 = wrestlers.get(3).getOverAllScore();
        int wrestlerScore5 = wrestlers.get(4).getOverAllScore();
        int[] wrestlersScoresArray = new int[5];
        wrestlersScoresArray[0] = wrestlerScore1;
        wrestlersScoresArray[1] = wrestlerScore2;
        wrestlersScoresArray[2] = wrestlerScore3;
        wrestlersScoresArray[3] = wrestlerScore4;
        wrestlersScoresArray[4] = wrestlerScore5;
        quicksort(wrestlersScoresArray);
        List<Wrestler> sortedWrestlers = new ArrayList<>();
        if (wrestlersScoresArray[0] == wrestlerScore1) {
            sortedWrestlers.add(wrestlers.get(0));
        } else if (wrestlersScoresArray[0] == wrestlerScore2) {
            sortedWrestlers.add(wrestlers.get(1));
        } else if (wrestlersScoresArray[0] == wrestlerScore3) {
            sortedWrestlers.add(wrestlers.get(2));
        } else if (wrestlersScoresArray[0] == wrestlerScore4) {
            sortedWrestlers.add(wrestlers.get(3));
        } else if (wrestlersScoresArray[0] == wrestlerScore5) {
            sortedWrestlers.add(wrestlers.get(4));
        }
        if (wrestlersScoresArray[1] == wrestlerScore1) {
            sortedWrestlers.add(wrestlers.get(0));
        } else if (wrestlersScoresArray[1] == wrestlerScore2) {
            sortedWrestlers.add(wrestlers.get(1));
        } else if (wrestlersScoresArray[1] == wrestlerScore3) {
            sortedWrestlers.add(wrestlers.get(2));
        } else if (wrestlersScoresArray[1] == wrestlerScore4) {
            sortedWrestlers.add(wrestlers.get(3));
        } else if (wrestlersScoresArray[1] == wrestlerScore5) {
            sortedWrestlers.add(wrestlers.get(4));
        }
        if (wrestlersScoresArray[2] == wrestlerScore1) {
            sortedWrestlers.add(wrestlers.get(0));
        } else if (wrestlersScoresArray[2] == wrestlerScore2) {
            sortedWrestlers.add(wrestlers.get(1));
        } else if (wrestlersScoresArray[2] == wrestlerScore3) {
            sortedWrestlers.add(wrestlers.get(2));
        } else if (wrestlersScoresArray[2] == wrestlerScore4) {
            sortedWrestlers.add(wrestlers.get(3));
        } else if (wrestlersScoresArray[2] == wrestlerScore5) {
            sortedWrestlers.add(wrestlers.get(4));
        }
        if (wrestlersScoresArray[3] == wrestlerScore1) {
            sortedWrestlers.add(wrestlers.get(0));
        } else if (wrestlersScoresArray[3] == wrestlerScore2) {
            sortedWrestlers.add(wrestlers.get(1));
        } else if (wrestlersScoresArray[3] == wrestlerScore3) {
            sortedWrestlers.add(wrestlers.get(2));
        } else if (wrestlersScoresArray[3] == wrestlerScore4) {
            sortedWrestlers.add(wrestlers.get(3));
        } else if (wrestlersScoresArray[3] == wrestlerScore5) {
            sortedWrestlers.add(wrestlers.get(4));
        }
        if (wrestlersScoresArray[4] == wrestlerScore1) {
            sortedWrestlers.add(wrestlers.get(0));
        } else if (wrestlersScoresArray[4] == wrestlerScore2) {
            sortedWrestlers.add(wrestlers.get(1));
        } else if (wrestlersScoresArray[4] == wrestlerScore3) {
            sortedWrestlers.add(wrestlers.get(2));
        } else if (wrestlersScoresArray[4] == wrestlerScore4) {
            sortedWrestlers.add(wrestlers.get(3));
        } else if (wrestlersScoresArray[4] == wrestlerScore5) {
            sortedWrestlers.add(wrestlers.get(4));
        }
        return sortedWrestlers;
    }

    private static void quicksort(int[] array) {
        quicksort(array, 0, array.length - 1);
    }

    private static void quicksort(int[] array, int lowIndex, int highIndex) {

        if (lowIndex >= highIndex) {
            return;
        }
        int pivotIndex = new Random().nextInt(highIndex - lowIndex) + lowIndex;
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, highIndex);

        int leftPointer = partition(array, lowIndex, highIndex, pivot);

        quicksort(array, lowIndex, leftPointer - 1);
        quicksort(array, leftPointer + 1, highIndex);
    }

    private static int partition(int[] array, int lowIndex, int highIndex, int pivot) {
        int leftPointer = lowIndex;
        int rightPointer = highIndex;

        while (leftPointer < rightPointer) {
            while (array[leftPointer] <= pivot && leftPointer < rightPointer) {
                leftPointer++;
            }
            while (array[rightPointer] >= pivot && leftPointer < rightPointer) {
                rightPointer--;
            }

            swap(array, leftPointer, rightPointer);

        }
        swap(array, leftPointer, highIndex);
        return leftPointer;
    }

    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private static void holdThreePractices(Team team) {
        for (int i = 0; i < 3; i++) {
            practice(team);
        }
    }

    private static void practice(Team team) {
        boolean done = false;
        int userChoice;
        Scanner scan = new Scanner(System.in);
        Coach coach = team.getCoach();
        List<Wrestler> wrestlers = team.getWrestlers();
        do {
            System.out.println("What do you want to do for practice?");
            System.out.println("Run                 (1)");
            System.out.println("Hit the gym         (2)");
            System.out.println("Wrestling matches   (3)");
            System.out.print("Make a selection: ");
            userChoice = scan.nextInt();
            if (userChoice > 4 || userChoice < 1) {
                System.out.println("Invalid Input");
            } else {
                done = true;
            }
        } while (!done);
        if (userChoice == 1) {
            done = false;
            int numOfMiles;
            do {
                System.out.print("How many miles: ");
                numOfMiles = scan.nextInt();
                if (numOfMiles > 25 || numOfMiles < 1) {
                    System.out.println("That's unreasonable");
                } else {
                    done = true;
                }
            } while (!done);
            if (numOfMiles <= 5) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeMotionScore(1);
                    wrestler.changePenetrationScore(1);
                    wrestler.changeStanceScore(1);
                    wrestler.changeWeight(-1);
                    wrestler.changeMoral(-1);
                }
            } else if (numOfMiles <= 10) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeMotionScore(2);
                    wrestler.changePenetrationScore(2);
                    wrestler.changeStanceScore(2);
                    wrestler.changeWeight(-1);
                    wrestler.changeMoral(-2);
                }
            } else if (numOfMiles <= 15) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeMotionScore(3);
                    wrestler.changePenetrationScore(3);
                    wrestler.changeStanceScore(3);
                    wrestler.changeWeight(-2);
                    wrestler.changeMoral(-3);
                }
            } else if (numOfMiles <= 20) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeMotionScore(4);
                    wrestler.changePenetrationScore(4);
                    wrestler.changeStanceScore(4);
                    wrestler.changeWeight(-2);
                    wrestler.changeMoral(-4);
                }
            } else {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeMotionScore(5);
                    wrestler.changePenetrationScore(5);
                    wrestler.changeStanceScore(5);
                    wrestler.changeWeight(-3);
                    wrestler.changeMoral(-5);
                }
            }
            System.out.println("Teams done running");
        } else if (userChoice == 2) {
            done = false;
            int timeAtGym;
            do {
                System.out.print("Minutes at gym: ");
                timeAtGym = scan.nextInt();
                if (timeAtGym > 240 || timeAtGym < 1) {
                    System.out.println("That's unreasonable");
                } else {
                    done = true;
                }
            } while (!done);
            if (timeAtGym <= 30) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeLiftingScore(1);
                    wrestler.changeLevelChangeScore(1);
                    wrestler.changeWeight(1);
                }
            } else if (timeAtGym <= 60) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeLiftingScore(2);
                    wrestler.changeLevelChangeScore(2);
                    wrestler.changeWeight(1);
                    wrestler.changeMoral(-1);
                }
            } else if (timeAtGym <= 90) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeLiftingScore(3);
                    wrestler.changeLevelChangeScore(3);
                    wrestler.changeWeight(2);
                    wrestler.changeMoral(-2);
                }
            } else if (timeAtGym <= 120) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeLiftingScore(4);
                    wrestler.changeLevelChangeScore(4);
                    wrestler.changeWeight(2);
                    wrestler.changeMoral(-3);
                }
            } else if (timeAtGym <= 150) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeLiftingScore(5);
                    wrestler.changeLevelChangeScore(5);
                    wrestler.changeWeight(3);
                    wrestler.changeMoral(-4);
                }
            } else if (timeAtGym <= 180) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeLiftingScore(6);
                    wrestler.changeLevelChangeScore(6);
                    wrestler.changeWeight(3);
                    wrestler.changeMoral(-5);
                }
            } else if (timeAtGym <= 210) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeLiftingScore(7);
                    wrestler.changeLevelChangeScore(7);
                    wrestler.changeWeight(4);
                    wrestler.changeMoral(-6);
                }
            } else {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeLiftingScore(8);
                    wrestler.changeLevelChangeScore(8);
                    wrestler.changeWeight(4);
                    wrestler.changeMoral(-7);
                }
            }
            System.out.println("Teams done at the gym");
        } else if (userChoice == 3) {
            done = false;
            int timeWrestling;
            do {
                System.out.print("Minutes wrestling: ");
                timeWrestling = scan.nextInt();
                if (timeWrestling > 240 || timeWrestling < 1) {
                    System.out.println("That's unreasonable");
                } else {
                    done = true;
                }
            } while (!done);
            if (timeWrestling <= 30) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeBackArchScore(1);
                    wrestler.changeBackStepScore(1);
                }
            } else if (timeWrestling <= 60) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeBackArchScore(2);
                    wrestler.changeBackStepScore(2);
                    wrestler.changeMoral(-1);
                }
            } else if (timeWrestling <= 90) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeBackArchScore(3);
                    wrestler.changeBackStepScore(3);
                    wrestler.changeMoral(-2);
                }
            } else if (timeWrestling <= 120) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeBackArchScore(4);
                    wrestler.changeBackStepScore(4);
                    wrestler.changeMoral(-3);
                }
            } else if (timeWrestling <= 150) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeBackArchScore(5);
                    wrestler.changeBackStepScore(5);
                    wrestler.changeMoral(-4);
                }
            } else if (timeWrestling <= 180) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeBackArchScore(6);
                    wrestler.changeBackStepScore(6);
                    wrestler.changeMoral(-5);
                }
            } else if (timeWrestling <= 210) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeBackArchScore(7);
                    wrestler.changeBackStepScore(7);
                    wrestler.changeMoral(-6);
                }
            } else {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeBackArchScore(8);
                    wrestler.changeBackStepScore(8);
                    wrestler.changeMoral(-7);
                }
            }
            System.out.println("Teams done wrestling");
        }
        System.out.println("\n");
    }

    private static void computerHoldThreePractices(Team team) {
        for (int i = 0; i < 3; i++) {
            computerPractice(team);
        }
    }

    private static void computerPractice(Team team) {
        int userChoice;
        Random random = new Random();
        Coach coach = team.getCoach();
        List<Wrestler> wrestlers = team.getWrestlers();
        userChoice = random.nextInt(4);
        if (userChoice == 1) {
            int numOfMiles = random.nextInt(26);
            if (numOfMiles <= 5) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeMotionScore(1);
                    wrestler.changePenetrationScore(1);
                    wrestler.changeStanceScore(1);
                    wrestler.changeWeight(-1);
                    wrestler.changeMoral(-1);
                }
            } else if (numOfMiles <= 10) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeMotionScore(2);
                    wrestler.changePenetrationScore(2);
                    wrestler.changeStanceScore(2);
                    wrestler.changeWeight(-1);
                    wrestler.changeMoral(-2);
                }
            } else if (numOfMiles <= 15) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeMotionScore(3);
                    wrestler.changePenetrationScore(3);
                    wrestler.changeStanceScore(3);
                    wrestler.changeWeight(-2);
                    wrestler.changeMoral(-3);
                }
            } else if (numOfMiles <= 20) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeMotionScore(4);
                    wrestler.changePenetrationScore(4);
                    wrestler.changeStanceScore(4);
                    wrestler.changeWeight(-2);
                    wrestler.changeMoral(-4);
                }
            } else {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeMotionScore(5);
                    wrestler.changePenetrationScore(5);
                    wrestler.changeStanceScore(5);
                    wrestler.changeWeight(-3);
                    wrestler.changeMoral(-5);
                }
            }
        } else if (userChoice == 2) {
            int timeAtGym = random.nextInt(241);
            if (timeAtGym <= 30) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeLiftingScore(1);
                    wrestler.changeLevelChangeScore(1);
                    wrestler.changeWeight(1);
                }
            } else if (timeAtGym <= 60) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeLiftingScore(2);
                    wrestler.changeLevelChangeScore(2);
                    wrestler.changeWeight(1);
                    wrestler.changeMoral(-1);
                }
            } else if (timeAtGym <= 90) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeLiftingScore(3);
                    wrestler.changeLevelChangeScore(3);
                    wrestler.changeWeight(2);
                    wrestler.changeMoral(-2);
                }
            } else if (timeAtGym <= 120) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeLiftingScore(4);
                    wrestler.changeLevelChangeScore(4);
                    wrestler.changeWeight(2);
                    wrestler.changeMoral(-3);
                }
            } else if (timeAtGym <= 150) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeLiftingScore(5);
                    wrestler.changeLevelChangeScore(5);
                    wrestler.changeWeight(3);
                    wrestler.changeMoral(-4);
                }
            } else if (timeAtGym <= 180) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeLiftingScore(6);
                    wrestler.changeLevelChangeScore(6);
                    wrestler.changeWeight(3);
                    wrestler.changeMoral(-5);
                }
            } else if (timeAtGym <= 210) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeLiftingScore(7);
                    wrestler.changeLevelChangeScore(7);
                    wrestler.changeWeight(4);
                    wrestler.changeMoral(-6);
                }
            } else {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeLiftingScore(8);
                    wrestler.changeLevelChangeScore(8);
                    wrestler.changeWeight(4);
                    wrestler.changeMoral(-7);
                }
            }
        } else if (userChoice == 3) {
            int timeWrestling = random.nextInt(241);
            if (timeWrestling <= 30) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeBackArchScore(1);
                    wrestler.changeBackStepScore(1);
                }
            } else if (timeWrestling <= 60) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeBackArchScore(2);
                    wrestler.changeBackStepScore(2);
                    wrestler.changeMoral(-1);
                }
            } else if (timeWrestling <= 90) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeBackArchScore(3);
                    wrestler.changeBackStepScore(3);
                    wrestler.changeMoral(-2);
                }
            } else if (timeWrestling <= 120) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeBackArchScore(4);
                    wrestler.changeBackStepScore(4);
                    wrestler.changeMoral(-3);
                }
            } else if (timeWrestling <= 150) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeBackArchScore(5);
                    wrestler.changeBackStepScore(5);
                    wrestler.changeMoral(-4);
                }
            } else if (timeWrestling <= 180) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeBackArchScore(6);
                    wrestler.changeBackStepScore(6);
                    wrestler.changeMoral(-5);
                }
            } else if (timeWrestling <= 210) {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeBackArchScore(7);
                    wrestler.changeBackStepScore(7);
                    wrestler.changeMoral(-6);
                }
            } else {
                coach.changeMoral(-1);
                for (Wrestler wrestler : wrestlers) {
                    wrestler.changeBackArchScore(8);
                    wrestler.changeBackStepScore(8);
                    wrestler.changeMoral(-7);
                }
            }
        }
    }

    private static void checkPointMenu(Team team, List<School> schools) throws FileNotFoundException {
        boolean done = false;
        while (!done) {
            System.out.println("\tCheck Point Menu");
            System.out.println("Continue Hit Enter");
            System.out.println("Schedule     (2)");
            System.out.println("Save game    (3)");
            System.out.println("Team Stats   (4)");
            System.out.println("Quit         (5)");
            System.out.print("Make a selection: ");
            Scanner scanner = new Scanner(System.in);
            String quit = scanner.nextLine();
            if (quit.equals("")) {
                done = true;
            } else {
                int userInput = Integer.parseInt(quit);
                if (userInput == 2) {
                    gameSchedule();
                } else if (userInput == 3) {
                    saveGame(team, schools);
                    System.out.println("Ok saved");
                } else if (userInput == 4) {
                    printUsersTeam(team);
                } else if (userInput == 5) {
                    System.exit(0);
                } else {
                    System.out.println("Error");
                }
            }
        }
    }

    private static void setOtherSchoolsStats(List<School> schools) {
        for (School school : schools) {
            Team team = school.getTeam();
            setRandomTeamStats(team);
        }
    }

    private static GameState beginningPrompt() throws FileNotFoundException {
        boolean done = false;
        while (!done) {
            wrestlingTitle();
            System.out.println("Instructions (1)");
            System.out.println("Schedule     (2)");
            System.out.println("New game     (3)");
            System.out.println("Load game    (4)");
            System.out.println("Quit         (5)");
            System.out.print("Make a selection: ");
            Scanner scanner = new Scanner(System.in);
            int userInput = scanner.nextInt();
            if (userInput == 1) {
                instructionsToPlay();
            } else if (userInput == 2) {
                gameSchedule();
            } else if (userInput == 3) {
                done = true;
            } else if (userInput == 4) {
                return getSavedGame();
            } else if (userInput == 5) {
                System.exit(0);
            }
            else {
                System.out.println("Error");
            }
        }
        return null;
    }

    private static Team readTeam(File file) {
        if (file.exists()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.readValue(file, Team.class);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private static void saveTeam(File file, Team team) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file, team);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static List<School> readSchools(File file) {
        if (file.exists()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.readValue(file, new TypeReference<List<School>>(){});
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private static void saveSchools(File file, List<School> schools) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file, schools);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveGame(Team usersTeam, List<School> schools) {
        File file = new File("src/txtFiles/savedTeam");
        saveTeam(file, usersTeam);
        file = new File("src/txtFiles/savedSchools");
        saveSchools(file, schools);
    }

    private static GameState getSavedGame() {
        File file = new File("src/txtFiles/savedTeam");
        Team userTeam = readTeam(file);
        file = new File("src/txtFiles/savedSchools");
        List<School> schools = readSchools(file);
        return new GameState(userTeam, schools);
    }

    private static void instructionsToPlay() throws FileNotFoundException {
        printTxtFile("src/txtFiles/InstructionsOnHowToPlay");
    }

    private static void gameSchedule() throws FileNotFoundException {
        printTxtFile("src/txtFiles/GameSchedule");
    }

    private static void printTxtFile(String filePath) throws FileNotFoundException {
        File scheduleFile = new File(filePath);
        try (Scanner scanner = new Scanner(scheduleFile)) {
            System.out.println();
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                System.out.println(s);
            }
            System.out.println();
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter if done: ");
            String userDone = scan.nextLine();
            if (userDone.equals(""));
            else {
                printTxtFile(filePath);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void wrestlingTitle() {
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("|                                                                                     __    |");
        System.out.println("|    _ _ _             _   _ _            _____                                   _  |  |   |");
        System.out.println("|   | | | |___ ___ ___| |_| |_|___ ___   |_   _|___ _ _ ___ ___ ___ _____ ___ ___| |_|  |   |");
        System.out.println("|   | | | |  _| -_|_ -|  _| | |   | . |    | | | . | | |  _|   | .'|     | -_|   |  _|__|   |");
        System.out.println("|   |_____|_| |___|___|_| |_|_|_|_|_  |    |_| |___|___|_| |_|_|__,|_|_|_|___|_|_|_| |__|   |");
        System.out.println("|                                 |___|                                                     |");
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println();
    }

    private static Team getUsersTeam() {
        Coach playerCoach = getPlayerCoach();
        String school = playerCoach.getSchool();
        Team usersTeam = getWrestlersForPlayer(school, playerCoach);
        usersTeam.setCoach(playerCoach);
        getEnterFromUserToContinue();
        return usersTeam;
    }

    private static void getEnterFromUserToContinue() {
        boolean done = false;
        Scanner scan = new Scanner(System.in);
        while (!done) {
            System.out.println("\nHit twice if it doesnt continue");
            System.out.print("Enter to continue: ");
            String enter = scan.nextLine();
            if (Objects.equals(enter, "")) {
                done = true;
            }
        }
        System.out.println();
    }

    private static Team getWrestlersForPlayer(String school, Coach coach) {
        System.out.println("Great now its time to hold tryouts for your team");
        Random random = new Random();
        int wrestlerAgeHigh = 19;
        int wrestlerAgeLow = 14;
        int wrestlerAge = random.nextInt(wrestlerAgeHigh - wrestlerAgeLow) + wrestlerAgeLow;
        List<Wrestler> tryoutTeam = new ArrayList<>();
        Wrestler wrestler1 = new Wrestler(wrestlerAge, Names.getNextPersonName(), school);
        Wrestler wrestler2 = new Wrestler(wrestlerAge, Names.getNextPersonName(), school);
        Wrestler wrestler3 = new Wrestler(wrestlerAge, Names.getNextPersonName(), school);
        Wrestler wrestler4 = new Wrestler(wrestlerAge, Names.getNextPersonName(), school);
        Wrestler wrestler5 = new Wrestler(wrestlerAge, Names.getNextPersonName(), school);
        Wrestler wrestler6 = new Wrestler(wrestlerAge, Names.getNextPersonName(), school);
        Wrestler wrestler7 = new Wrestler(wrestlerAge, Names.getNextPersonName(), school);
        Wrestler wrestler8 = new Wrestler(wrestlerAge, Names.getNextPersonName(), school);
        Wrestler wrestler9 = new Wrestler(wrestlerAge, Names.getNextPersonName(), school);
        Wrestler wrestler10 = new Wrestler(wrestlerAge, Names.getNextPersonName(), school);
        tryoutTeam.add(wrestler1);
        tryoutTeam.add(wrestler2);
        tryoutTeam.add(wrestler3);
        tryoutTeam.add(wrestler4);
        tryoutTeam.add(wrestler5);
        tryoutTeam.add(wrestler6);
        tryoutTeam.add(wrestler7);
        tryoutTeam.add(wrestler8);
        tryoutTeam.add(wrestler9);
        tryoutTeam.add(wrestler10);
        Team team = new Team(coach, tryoutTeam);
        setRandomTeamStats(team);
        System.out.println("You have 10 Wrestlers that showed up and you get to pick 5 that you like the most");
        getEnterFromUserToContinue();
        printUsersTeam(team);
        System.out.println("Ok its time to pick!");
        Team usersTeam = collectUsersTeam(team);
        System.out.println("Ok here is your team!");
        printUsersTeam(usersTeam);
        return usersTeam;
    }

    private static void setRandomTeamStats(Team team) {
        Random random = new Random();
        int moral;
        Coach coach = team.getCoach();
        moral = random.nextInt(10 - 5) + 5;
        coach.setMoral(moral);
        for (Wrestler wrestler : team.getWrestlers()) {
            moral = random.nextInt(10 - 5) + 5;
            wrestler.setMoral(moral);
            int numForBackArchSore = random.nextInt(100);
            int numForBackStepScore = random.nextInt(100);
            int numForLiftingScore = random.nextInt(100);
            int numForMotionScore = random.nextInt(100);
            int numForPenetrationScore = random.nextInt(100);
            int numForStanceScore = random.nextInt(100);
            int numForLevelChangeScore = random.nextInt(100);
            wrestler.setBackArchScore(numForBackArchSore);
            wrestler.setBackStepScore(numForBackStepScore);
            wrestler.setLiftingScore(numForLiftingScore);
            wrestler.setMotionScore(numForMotionScore);
            wrestler.setPenetrationScore(numForPenetrationScore);
            wrestler.setStanceScore(numForStanceScore);
            wrestler.setLevelChangeScore(numForLevelChangeScore);
        }
    }

    private static Team collectUsersTeam(Team team) {
        boolean done = false;
        int userChoice1 = 1;
        int userChoice2 = 2;
        int userChoice3 = 3;
        int userChoice4 = 4;
        int userChoice5 = 5;
        while (!done) {
            System.out.println("Enter (1) for wrestler 1, (2) for wrestler 2, and so on");
            System.out.print("Pick 5 (Ex. 1 2 6 8 10): " );
            Scanner scan = new Scanner(System.in);
            userChoice1 = scan.nextInt() - 1;
            userChoice2 = scan.nextInt() - 1;
            userChoice3 = scan.nextInt() - 1;
            userChoice4 = scan.nextInt() - 1;
            userChoice5 = scan.nextInt() - 1;
            if (userChoice1 < 0 || userChoice1 > 9 ||
                    userChoice2 < 0 || userChoice2 > 9 ||
                    userChoice3 < 0 || userChoice3 > 9 ||
                    userChoice4 < 0 || userChoice4 > 9 ||
                    userChoice5 < 0 || userChoice5 > 9) {
                System.out.println("Invalid input");
            }else {
                done = true;
            }
        }
        List<Wrestler> wrestlers = new ArrayList<>();
        wrestlers.add(team.getWrestlers().get(userChoice1));
        wrestlers.add(team.getWrestlers().get(userChoice2));
        wrestlers.add(team.getWrestlers().get(userChoice3));
        wrestlers.add(team.getWrestlers().get(userChoice4));
        wrestlers.add(team.getWrestlers().get(userChoice5));
        return new Team(team.getCoach(), wrestlers);
    }

    private static void printUsersTeam(Team team) {
        Coach coach = team.getCoach();
        System.out.println(coach.getSchool());
        System.out.println();
        System.out.println("            Coach");
        System.out.println("name                : " + coach.getName());
        System.out.println("Number of Team wins : " + coach.getNumOfTimesWon());
        System.out.println("age                 : " + coach.getAge());
        System.out.println("moral               : " + coach.getMoral());
        System.out.println();
        int i = 1;
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        for (Wrestler wrestler : team.getWrestlers()) {
            System.out.println("            Wrestler " + i);
            System.out.println("name                 : " + wrestler.getName());
            System.out.println("Number of Fights Won : " + wrestler.getNumOfFightsWon());
            System.out.println("moral                : " + wrestler.getMoral());
            System.out.println("height               : " + decimalFormat.format(wrestler.getHeight()));
            System.out.println("weight               : " + wrestler.getWeight());
            System.out.println("stance score         : " + wrestler.getStanceScore());
            System.out.println("motion score         : " + wrestler.getMotionScore());
            System.out.println("level change score   : " + wrestler.getLevelChangeScore());
            System.out.println("penetration score    : " + wrestler.getPenetrationScore());
            System.out.println("lifting score        : " + wrestler.getLiftingScore());
            System.out.println("back-step score      : " + wrestler.getBackStepScore());
            System.out.println("back-arch score      : " + wrestler.getBackArchScore());
            System.out.println();
            i++;
        }
    }

    private static List<School> getOtherSchoolsTeam() {
        List<School> schools = new ArrayList<>();
        School school2 = new School();
        School school3 = new School();
        School school4 = new School();
        School school5 = new School();
        School school6 = new School();
        School school7 = new School();
        School school8 = new School();
        School school9 = new School();
        School school10 = new School();
        School school11 = new School();
        School school12 = new School();
        schools.add(school2);
        schools.add(school3);
        schools.add(school4);
        schools.add(school5);
        schools.add(school6);
        schools.add(school7);
        schools.add(school8);
        schools.add(school9);
        schools.add(school10);
        schools.add(school11);
        schools.add(school12);
        System.out.println("Ok the other schools are ready too!");
        return schools;
    }

    private static Coach getPlayerCoach() {
        Scanner scanner = new Scanner(System.in);
        int age;
        String name;
        String schoolName;
        System.out.println("Great lets get your charicter made");
        while (true) {
            System.out.print("Age: ");
            age = scanner.nextInt();
            if (age > 65 || age < 19) {
                System.out.println("Coaches arent that age. Try again");
            } else {
                break;
            }
        }
        scanner.nextLine();
        while (true) {
            System.out.print("Name: ");
            name = scanner.nextLine();
            if (Objects.equals(name, "")) {
                System.out.println("No Input");
            } else {
                break;
            }
        }
        while (true) {
            System.out.print("School Name: ");
            schoolName = scanner.nextLine();
            if (Objects.equals(schoolName, "")) {
                System.out.println("No Input");
            } else {
                break;
            }
        }
        return new Coach(age, name, schoolName);
    }

    private static void clearScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    static class GameState {
        private Team userTeam;
        private List<School> schools;
        public GameState(Team userTeam, List<School> schools) {
            this.userTeam = userTeam;
            this.schools = schools;
        }

        public Team getUserTeam() {
            return userTeam;
        }

        public void setUserTeam(Team userTeam) {
            this.userTeam = userTeam;
        }

        public List<School> getSchools() {
            return schools;
        }

        public void setSchools(List<School> schools) {
            this.schools = schools;
        }
    }

}
