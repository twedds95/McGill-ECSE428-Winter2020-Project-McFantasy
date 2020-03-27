package ca.mcgill.ecse428.project.dto;

public class AppUserDto {
    private String name;
    private String email;
    private byte[] profilePicture;

    public AppUserDto(String name, String email, byte[] profilePicture) {
        this.name = name;
        this.email = email;
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }
}
