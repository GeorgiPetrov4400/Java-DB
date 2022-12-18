package softuni.exam.instagraphlite.models.dtos.userDtos;

import softuni.exam.instagraphlite.models.dtos.pictureDtos.PicturePathNameDTO;
import softuni.exam.instagraphlite.models.entities.Picture;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserImportDTO {

    @Size(min = 2, max = 18)
    @NotNull
    private String username;

    @Size(min = 4)
    @NotNull
    private String password;

    @NotNull
    private PicturePathNameDTO profilePicture;

    public UserImportDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PicturePathNameDTO getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(PicturePathNameDTO profilePicture) {
        this.profilePicture = profilePicture;
    }
}
