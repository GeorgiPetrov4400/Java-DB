package com.example.json.productShop.entities.dtos;

import java.util.List;

public class UserWithProductsWrapperDTO {

    private Integer usersCount;

    private List<UserWithProductsDTO> users;

    public UserWithProductsWrapperDTO() {
    }

    public UserWithProductsWrapperDTO(List<UserWithProductsDTO> users) {
        this.users = users;
        this.usersCount = users.size();
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public List<UserWithProductsDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithProductsDTO> users) {
        this.users = users;
    }
}
