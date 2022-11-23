package com.example.xml.productShop.entities.userDtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithProductsWrapperDTO {

    @XmlAttribute
    private Integer count;

    @XmlElement(name = "user")
    private List<UserWithProductsDTO> users;

    public UserWithProductsWrapperDTO() {
    }

    public UserWithProductsWrapperDTO(List<UserWithProductsDTO> users) {
        this.users = users;
        this.count = users.size();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<UserWithProductsDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithProductsDTO> users) {
        this.users = users;
    }
}
