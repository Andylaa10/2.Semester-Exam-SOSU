package be.Utilities;

import javafx.scene.image.Image;

import java.io.InputStream;

public class ImageWithText extends Image {
    private Image view;
    private String name;
    
    public ImageWithText(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth, String name) {
        super(url, requestedWidth, requestedHeight, preserveRatio, smooth);
        this.name = name;
    }

    public Image getView() {
        return view;
    }

    public void setView(Image view) {
        this.view = view;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
