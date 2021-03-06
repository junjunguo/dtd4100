package e2011;

import java.util.ArrayList;
import java.util.List;

public class LeagueTable implements Listener {
    
    private List<MatchResult>    matchtable;
    private List<String>         teamtable;
    private List<LeagueTableRow> pointtable;
    
    /**
     * 
     * @param teamName
     *            Variable-Length Argument Lists (String... teamName)
     */
    public LeagueTable(String... teamName) {
        matchtable = new ArrayList<MatchResult>();
        teamtable = new ArrayList<String>();
        for (String s : teamName) {
            teamtable.add(s);
        }
        initPointTable();
    }
    
    public List<MatchResult> getMatchtable() {
        return matchtable;
    }
    
    public List<String> getTeamtable() {
        return teamtable;
    }

    public void addMatch(String homeTeam, String awayTeam) {
        if (!teamtable.contains(homeTeam) || !teamtable.contains(awayTeam)) {
            throw new IllegalArgumentException("Illegal teams!");
        }
        MatchResult tr = new TennisResult(homeTeam, awayTeam);
        tr.addListener(this);
        matchtable.add(tr);
    }
    
    /**
     * 
     * @param homeTeam
     * @param awayTeam
     * @param homeGoals
     * @param awayGoals
     * @return true if result added and pointtable sorted, false teams do not
     *         have a match, no result added
     */
    public boolean addMatchResult(String homeTeam, String awayTeam,
            int homeGoals, int awayGoals) {
        for (MatchResult mr : matchtable) {
            if (mr.getHomeTeam() == homeTeam && mr.getAwayTeam() == awayTeam) {
                mr.setResult(homeGoals, awayGoals);
                sortPointTable();
                return true;
            }
        }
        return false;
    }
    
    public int getParticipantPoints(MatchResult matchResult, String participant) {
        if (matchResult.isWinner(participant)) {
            return 3;
        }
        if (matchResult.isDraw()) {
            return 1;
        }
        return 0;
    }
    
    /**
     * sort matchtable with teams with more goals on the top
     */
    private void sortPointTable() {
        initPointTable();
        for (int i = 0; i < pointtable.size() - 1; i++) {
            LeagueTableRow element = pointtable.get(i);
            for (int j = i + 1; j < pointtable.size(); j++) {
                if (element.getPoints() < pointtable.get(j).getPoints()) {
                    element = pointtable.get(j);
                }
            }
            pointtable.remove(element);
            pointtable.add(i, element);
        }
    }
    
    private void initPointTable() {
        pointtable = new ArrayList<LeagueTableRow>();
        for (String s : teamtable) {
            pointtable.add(new FootballLeagueTable(this, s));
        }
    }
    
    @Override
    public void changed(MatchResult matchResult) {
        System.out.println(matchResult.toString());
    }
    
    public String toString() {
        String s = "Team List: ";
        for (String team : teamtable) {
            s += team + "; ";
        }
        s += "\n";
        s += "Home team | AwayTeam " + "\n";
        for (MatchResult mr : matchtable) {
            s += "    " + mr.getHomeTeam() + " vs " + mr.getAwayTeam() + "\n";
        }
        s += "Team point:" + "\n";
        for (LeagueTableRow tp : this.pointtable) {
            s += "    Team: " + tp.getTeamName() + "; Points:" + tp.getPoints()
                    + "; Draws: " + tp.getDraws() + "; Victories: "
                    + tp.getVictories() + "\n";
        }
        return s;
    }
}