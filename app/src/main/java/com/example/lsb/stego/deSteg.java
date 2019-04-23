package com.example.lsb.stego;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.io.*;
import java.util.ArrayList;

public class deSteg {
    private Bitmap stegImage;

    public deSteg(Bitmap stegImage) {
        this.stegImage = stegImage;
    }

    public StringBuilder getText(){return readAllText();}

    private StringBuilder readAllText() {
        ArrayList<Character> sym = new ArrayList<>();
        String red;
        String green;
        String blue;
        StringBuilder allPix = new StringBuilder();
        char checkOut;
        boolean exit = true;
        StringBuilder endWord = new StringBuilder();

        for (int x = 0; x < stegImage.getWidth(); x++) {
            for (int y = 0; y < stegImage.getHeight(); y++) {
                if (exit) {
                    red = Integer.toBinaryString(Color.red(stegImage.getPixel(x,y)));
                    sym.add(red.charAt(red.length() - 1));
                    allPix.append(red.charAt(red.length() - 1));
                    if (sym.size() % 11 == 0) {
                        checkOut = (char) Integer.parseInt(allPix.toString(), 2);
                        endWord.append(checkOut);
                        allPix.delete(0, allPix.length());
                        if (checkOut == '~') exit = false;
                    }
                    green = Integer.toBinaryString(Color.green(stegImage.getPixel(x,y)));
                    sym.add(green.charAt(green.length() - 1));
                    allPix.append(green.charAt(green.length() - 1));
                    if (sym.size() % 11 == 0) {
                        checkOut = (char) Integer.parseInt(allPix.toString(), 2);
                        allPix.delete(0, allPix.length());
                        endWord.append(checkOut);
                        if (checkOut == '~') exit = false;
                    }
                    blue = Integer.toBinaryString(Color.blue(stegImage.getPixel(x,y)));
                    sym.add(blue.charAt(blue.length() - 1));
                    allPix.append(blue.charAt(blue.length() - 1));
                    if (sym.size() % 11 == 0) {
                        checkOut = (char) Integer.parseInt(allPix.toString(), 2);
                        endWord.append(checkOut);
                        allPix.delete(0, allPix.length());
                        if (checkOut == '~') exit = false;
                    }
                } else break;
            }
        }
        return endWord;
    }
}