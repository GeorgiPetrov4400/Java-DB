package com.example.xml.carDealer.entities;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {

    @OneToOne
    private Car car;

    @ManyToOne
    private Customer customer;

    @Column
    private Integer discount;

    public Sale() {
    }

    public Sale(Integer discount, Car car, Customer customer) {
        this.car = car;
        this.customer = customer;
        setDiscount(discount);
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        if (customer.isYoungDriver()) {
            discount = discount + 5;
        }
        this.discount = discount;
    }

}
