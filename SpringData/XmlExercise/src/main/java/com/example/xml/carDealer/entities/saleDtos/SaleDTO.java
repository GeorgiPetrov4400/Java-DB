package com.example.xml.carDealer.entities.saleDtos;

import com.example.xml.carDealer.entities.carDtos.CarAttributesDTO;
import com.example.xml.carDealer.entities.carDtos.CarDTO;
import com.example.xml.carDealer.entities.customerDtos.CustomerDTO;

import java.math.BigDecimal;

public class SaleDTO {

    private Integer discount;
    private CarDTO car;
    private CustomerDTO customer;

    public SaleDTO() {
    }

    public SaleDTO(Integer discount, CarDTO car, CustomerDTO customer) {
        this.discount = discount;
        this.car = car;
        this.customer = customer;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public SaleWithDiscountDTO saleWithDiscountDTO() {
        CarAttributesDTO car = new CarAttributesDTO(getCar().getMake(), getCar().getModel(), getCar().getTravelledDistance());

        BigDecimal price = this.car.getCarPrice();
        BigDecimal priceAfterDiscount = price.subtract(price.multiply(BigDecimal.valueOf(discount / 100.0)));

        return new SaleWithDiscountDTO(car, customer.getName(), discount / 100.0, price, priceAfterDiscount);
    }
}
