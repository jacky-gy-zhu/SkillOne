package com.trade.stock;

public class GoldCut {

    public static void main(String[] args) {
        float top = 68.81f;
        float start = 55.64f;
        calcPotentialBottomValue(top, start);
    }

    private static void calcPotentialBottomValue(float top, float start) {
        System.out.println("0.382 -> " + calcBottom(0.382f, top, start));
        System.out.println("0.5 -> " + calcBottom(0.5f, top, start));
        System.out.println("0.618 -> " + calcBottom(0.618f, top, start));
    }

    private static float calcBottom(float goldRate, float top, float start) {
        return top - ((top-start)/goldRate);
    }
}
