package com.example.json.carDealer.entities.dtos;


public class SupplierInputDTO {

    private String name;

    private boolean isImporter;

    public SupplierInputDTO() {
    }

    public SupplierInputDTO(String name, boolean isImporter) {
        this.name = name;
        this.isImporter = isImporter;
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
}
