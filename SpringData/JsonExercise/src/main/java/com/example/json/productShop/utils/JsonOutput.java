package com.example.json.productShop.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public enum JsonOutput {
    ;
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void writeJsonToFile(List<?> objects, Path filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath.toFile());

        gson.toJson(objects, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }
}
