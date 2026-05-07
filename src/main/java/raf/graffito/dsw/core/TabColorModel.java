package raf.graffito.dsw.core;

import lombok.Getter;

import java.awt.*;
@Getter
public class TabColorModel {
    private String selectedColor;

    public void setSelectedColorString(String selectedColor) {
        this.selectedColor = selectedColor;
    }
}
