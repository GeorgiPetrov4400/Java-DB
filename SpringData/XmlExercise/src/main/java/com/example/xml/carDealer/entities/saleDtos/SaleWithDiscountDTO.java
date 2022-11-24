package com.example.xml.carDealer.entities.saleDtos;

import com.example.xml.carDealer.entities.carDtos.CarAttributesDTO;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleWithDiscountDTO {

    @XmlElement
    private CarAttributesDTO car;

    @XmlElement(name = "customer-name")
    private String customerName;

    @XmlElement
    private double discount;

    @XmlElement
    private BigDecimal price;

    @XmlElement(name = "price-with-discount")
    private BigDecimal priceWithDiscount;

    public SaleWithDiscountDTO() {
    }

    public SaleWithDiscountDTO(CarAttributesDTO car, String customerName, double discount, BigDecimal price, BigDecimal priceWithDiscount) {
        this.car = car;
        this.customerName = customerName;
        this.discount = discount;
        this.price = price;
        this.priceWithDiscount = priceWithDiscount;
    }

    public CarAttributesDTO getCar() {
        return car;
    }

    public void setCar(CarAttributesDTO car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
