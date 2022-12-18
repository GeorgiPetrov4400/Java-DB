package softuni.exam.instagraphlite.models.dtos.pictureDtos;

import softuni.exam.instagraphlite.models.entities.Picture;

import javax.validation.constraints.NotNull;

public class PicturePathNameDTO {

    @NotNull
    private String path;

    public PicturePathNameDTO() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
