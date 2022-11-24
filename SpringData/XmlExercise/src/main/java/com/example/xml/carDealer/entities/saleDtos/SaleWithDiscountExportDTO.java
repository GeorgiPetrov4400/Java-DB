package com.example.xml.carDealer.entities.saleDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleWithDiscountExportDTO {

    @XmlElement(name = "sale")
    List<SaleWithDiscountDTO> sales;

    public SaleWithDiscountExportDTO() {
    }

    public SaleWithDiscountExportDTO(List<SaleWithDiscountDTO> sales) {
        this.sales = sales;
    }

    public List<SaleWithDiscountDTO> getSales() {
        return sales;
    }

    public void setSales(List<SaleWithDiscountDTO> sales) {
        this.sales = sales;
    }
}
