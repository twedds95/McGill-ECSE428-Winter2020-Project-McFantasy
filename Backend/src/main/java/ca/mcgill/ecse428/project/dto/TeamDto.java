package ca.mcgill.ecse428.project.dto;

public class TeamDto {
    private int teamID;
    private String name;
    private int wins;
    private int losses;
    private int ties;
    private int points;
    private int totalRating;
    private AppUserDto userDto;

    public TeamDto(int teamID, String name, int wins, int losses, int ties, int points, int totalRating, AppUserDto userDto) {
        this.teamID = teamID;
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
        this.points = points;
        this.totalRating = totalRating;
        this.userDto = userDto;
    }

    public int getTeamID() {
        return teamID;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getTies() {
        return ties;
    }

    public int getPoints() {
        return points;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public AppUserDto getUserDto() {
        return userDto;
    }
}
