package ca.mcgill.ecse428.project.dto;



public class LeagueDto {
   private String name;
   private AppUserDto user;

   public LeagueDto(String name, AppUserDto user) {
      this.name = name;
      this.user = user;
   }

   public String getName() {
      return name;
   }

   public AppUserDto getUser() {
      return user;
   }
}
