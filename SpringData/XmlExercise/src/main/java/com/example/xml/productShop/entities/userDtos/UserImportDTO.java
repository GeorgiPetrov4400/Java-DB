package com.example.xml.productShop.entities.userDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserImportDTO {

    @XmlElement(name = "user")
    private List<UserNamesAndAgeDTO> users;

    public UserImportDTO() {
    }

    public List<UserNamesAndAgeDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserNamesAndAgeDTO> users) {
        this.users = users;
    }
}
