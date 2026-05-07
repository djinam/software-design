package raf.graffito.dsw.controller.proxy.model;

import java.util.ArrayList;
import java.util.List;

public class ImageGallery {
    private List<ImageProxy> images = new ArrayList<>();

    public void addImage(ImageProxy proxy) {
        images.add(proxy);
    }

    public List<ImageProxy> getImages() {
        return images;
    }
}
