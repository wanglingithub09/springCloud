package com.test.util;

import java.awt.Color;
import java.awt.color.ColorSpace;

/**
 * 二维码颜色
 */
public class QrCodeColor extends Color {

    public QrCodeColor(int r, int g, int b) {
        super(r, g, b);
    }

    public QrCodeColor(int r, int g, int b, int a) {
        super(r, g, b, a);
    }

    public QrCodeColor(int rgb) {
        super(rgb);
    }

    public QrCodeColor(int rgba, boolean hasalpha) {
        super(rgba, hasalpha);
    }

    public QrCodeColor(float r, float g, float b) {
        super(r, g, b);
    }

    public QrCodeColor(float r, float g, float b, float a) {
        super(r, g, b, a);
    }

    public QrCodeColor(ColorSpace cspace, float[] components, float alpha) {
        super(cspace, components, alpha);
    }
}
