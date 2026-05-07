package raf.graffito.dsw.controller.strategy;

import raf.graffito.dsw.core.composite.abstraction.GraffNode;

import java.io.File;

public interface SerializerStrategy {
    void save(GraffNode project, File file);
    GraffNode load(File file);
}
