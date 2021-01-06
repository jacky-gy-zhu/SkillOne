package com.trade.stock;

public class MaApp {

    public static void main(String[] args) {
        float[] preFourDayPrice = new float[]{3.56f,3.35f,3.14f,3.02f};

        System.out.println("Next MA3 = " + getNextMA(preFourDayPrice, 3));
        System.out.println("Next MA5 = " + getNextMA(preFourDayPrice, 5));
    }

    private static float getNextMA(float[] preFourDayPrice, int days) {
        days = days - 1;
        float totalDayPrice = 0;
        int i = 0;
        for (float p : preFourDayPrice) {
            totalDayPrice += p;
            i++;
            if (i >= days) {
                break;
            }
        }
        float nextMa5 = totalDayPrice/days;
        return nextMa5;
    }

}
