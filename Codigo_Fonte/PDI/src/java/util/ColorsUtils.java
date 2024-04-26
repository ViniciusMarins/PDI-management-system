/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.Color;
import java.util.Random;
/**
 *
 * @author Vinicius
 */
public class ColorsUtils {
    
//    public static Color generateColorForArea(String area) {
//        int hash = area.hashCode();
//        float hue = (hash & 0xFF) / 255.0f;
//        return Color.getHSBColor(hue, 0.7f, 0.7f);
//    }

    public static Color generateColorForArea(String area) {
        Random random = new Random(area.hashCode()*12);

        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        return new Color(red*8/10, green*9/10, blue);
    }

    public static String getRGBAColor(Color color, double alpha) {
        return "rgba(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ", " + alpha + ")";
    }

    public static String getRGBColor(Color color) {
        return "rgb(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ")";
    }

}
