package be;

import javafx.scene.image.Image;

import java.io.InputStream;

public class ImageWithText extends Image {
    private Image view;
    private String name;

    public ImageWithText(String url, String name) {
        super(url);
        this.name = name;
    }

    public ImageWithText(String url) {
        super(url);
    }

    public ImageWithText(String url, boolean backgroundLoading) {
        super(url, backgroundLoading);
    }

    public ImageWithText(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth) {
        super(url, requestedWidth, requestedHeight, preserveRatio, smooth);
    }

    public ImageWithText(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth, boolean backgroundLoading) {
        super(url, requestedWidth, requestedHeight, preserveRatio, smooth, backgroundLoading);
    }

    public ImageWithText(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth, String name) {
        super(url, requestedWidth, requestedHeight, preserveRatio, smooth);
        this.name = name;
    }

    public ImageWithText(InputStream is) {
        super(is);
    }

    public ImageWithText(InputStream is, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth) {
        super(is, requestedWidth, requestedHeight, preserveRatio, smooth);
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
