package ca.mcgill.ecse428.mcfantasy.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SeasonStats{
   private Integer year;

public void setYear(Integer value) {
    this.year = value;
}
@Id
public Integer getYear() {
    return this.year;
}
private Integer goals;

public void setGoals(Integer value) {
    this.goals = value;
}
public Integer getGoals() {
    return this.goals;
}
private Integer assists;

public void setAssists(Integer value) {
    this.assists = value;
}
public Integer getAssists() {
    return this.assists;
}
}
