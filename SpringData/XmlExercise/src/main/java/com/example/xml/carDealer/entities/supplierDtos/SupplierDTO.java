package com.example.xml.carDealer.entities.supplierDtos;

import com.example.xml.carDealer.entities.partDto.PartDTO;

import java.util.Set;

public class SupplierDTO {

    private String name;

    private boolean isImporter;

    Set<PartDTO> parts;

    public SupplierDTO() {
    }

    public SupplierDTO(String name, boolean isImporter, Set<PartDTO> parts) {
        this.name = name;
        this.isImporter = isImporter;
        this.parts = parts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }

    public Set<PartDTO> getParts() {
        return parts;
    }

    public void setParts(Set<PartDTO> parts) {
        this.parts = parts;
    }
}
