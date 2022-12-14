package com.example.json.productShop.entities.dtos;

import java.math.BigDecimal;

public class CategoryStatistics {

    private String category;

    private long productsCount;

    private double averagePrice;

    private BigDecimal totalRevenue;

    public CategoryStatistics() {
    }

    public CategoryStatistics(String category, long productsCount, double averagePrice, BigDecimal totalRevenue) {
        this.category = category;
        this.productsCount = productsCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(long productsCount) {
        this.productsCount = productsCount;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    // interface print with iter
    //    String getCategory();
    //
    //    long getProductsCount();
    //
    //    BigDecimal getAveragePrice();
    //
    //    BigDecimal getTotalRevenue();
}
