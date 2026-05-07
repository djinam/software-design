package raf.graffito.dsw.controller.strategy;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import raf.graffito.dsw.core.composite.abstraction.GraffNode;

import java.io.File;
import java.io.IOException;

public class JsonGraffNodeSerializer implements SerializerStrategy {
    private  ObjectMapper mapper;

    public JsonGraffNodeSerializer() {
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
    @Override
    public void save(GraffNode node, File file) {
        try {
            mapper.writeValue(file, node);
            System.out.println("Saved node to: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GraffNode load(File file) {
        try {
            return mapper.readValue(file, GraffNode.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
