package ca.mcgill.ecse428.project.dto;


public class PlayerDto {
private String position;
private String name;
private int jerseyNumber;
private int rating;
private int totalGoals;
private int totalAssists;
private int points;


    public PlayerDto(String position, String name, int jerseyNumber, int rating, int totalGoals, int totalAssists) {
        this.position = position;
        this.name = name;
        this.jerseyNumber = jerseyNumber;
        this.rating = rating;
        this.totalGoals = totalGoals;
        this.totalAssists = totalAssists;
        this.points = totalAssists + totalGoals;
    }

}
