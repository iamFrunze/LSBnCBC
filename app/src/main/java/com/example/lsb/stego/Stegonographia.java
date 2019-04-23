package com.example.lsb.stego;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.io.*;
import java.util.ArrayList;

public class Stegonographia {


    private Bitmap originalImage;
    private String text;


    private String textToCrypt = null;


    public Stegonographia(Bitmap originalImage, String text) {
        this.originalImage = originalImage;
        this.text = text;
    }

    public Bitmap getLSB_Method() {return changeBit();}

    private Bitmap changeBit() {
/******************** Заполнение ArrayList битами исходного текста******************************************************/
        ArrayList<Character> bitText = new ArrayList<>();
        for (int indexOfSymbol = 0; indexOfSymbol < textToCrypt.length(); indexOfSymbol++) {
            for (int i = 0; i < 11; i++) {                                                              // 11 - количество бит русского алфавита
                if (getArrayBit(indexOfSymbol)[i] == '0' || getArrayBit(indexOfSymbol)[i] == '1') {
                    bitText.add(getArrayBit(indexOfSymbol)[i]);
                }
            }
        }
/***********************************************************************************************************************/
        int red = 0;
        int green = 0;
        int blue = 0;
        ArrayList<Integer> rgb = new ArrayList<>();
        int indexOfArrayList = 0;
        for (int x = 0; x < originalImage.getWidth(); x++)
            for (int y = 0; y < originalImage.getHeight(); y++) {
/****************************** Красный цвет пикселя *******************************************************************/
                if (indexOfArrayList < bitText.size()) {
                    if (bitText.get(indexOfArrayList).equals('0')) {
                        red = Color.red(originalImage.getPixel(x, y)) & 254;
                        rgb.add(red);
                        ++indexOfArrayList;
                    } else if (bitText.get(indexOfArrayList).equals('1')) {
                        red = Color.red(originalImage.getPixel(x, y)) | 1;
                        rgb.add(red);
                        ++indexOfArrayList;
                    }
                }
/****************************** Зеленый цвет пикселя *******************************************************************/
                if (indexOfArrayList < bitText.size()) {
                    if (bitText.get(indexOfArrayList).equals('0')) {
                        green = Color.green(originalImage.getPixel(x, y)) & 254;
                        rgb.add(green);
                        ++indexOfArrayList;
                    } else if (bitText.get(indexOfArrayList).equals('1')) {
                        green = Color.green(originalImage.getPixel(x, y)) | 1;
                        rgb.add(green);
                        ++indexOfArrayList;
                    }
                }
/****************************** Голубой цвет пикселя *******************************************************************/
                if (indexOfArrayList < bitText.size()) {
                    if (bitText.get(indexOfArrayList).equals('0')) {
                        blue = Color.blue(originalImage.getPixel(x, y)) & 254;
                        rgb.add(blue);
                        originalImage.setPixel(x, y, Color.rgb(red, green, blue));
                        ++indexOfArrayList;
                    } else if (bitText.get(indexOfArrayList).equals('1')) {
                        blue = new Color().blue(originalImage.getPixel(x, y)) | 1;
                        rgb.add(blue);
                        originalImage.setPixel(x, y, Color.rgb(red, green, blue));
                        ++indexOfArrayList;
                    }
                }
                if (indexOfArrayList == bitText.size()) break;
            }

        return originalImage;

    }


    /******************** Получение бинарного значения каждого символа**************************************************/
    private char[] getArrayBit(int index) {
        String bitText = String.format("%11s", Integer.toBinaryString(textToCrypt.charAt(index))).replace(' ', '0');
        char[] bit = new char[32];
        for (int i = 0; i < bitText.length(); i++) {
            bit[i] = bitText.charAt(i);
        }
        return bit;
    }


}

