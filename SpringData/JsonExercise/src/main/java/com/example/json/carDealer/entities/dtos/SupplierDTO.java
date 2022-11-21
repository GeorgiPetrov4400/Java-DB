package com.example.json.carDealer.entities.dtos;

import java.util.Set;

public class SupplierDTO {

    private String name;

    private boolean isImporter;

    Set<PartDto> parts;

    public SupplierDTO() {
    }

    public SupplierDTO(String name, boolean isImporter, Set<PartDto> parts) {
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

    public Set<PartDto> getParts() {
        return parts;
    }

    public void setParts(Set<PartDto> parts) {
        this.parts = parts;
    }
}
