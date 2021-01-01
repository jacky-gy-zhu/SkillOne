package com.trade.stock;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        List<Float[]> tradePairs = new ArrayList<>();
        tradePairs.add(new Float[]{8.711f,11.877f});
        tradePairs.add(new Float[]{13.82f,14.57f});
        tradePairs.add(new Float[]{16.66f,18.811f});
        tradePairs.add(new Float[]{13.77f,13.567f});
        tradePairs.add(new Float[]{14.734f,15.756f});
        float rate = profitRate(tradePairs);
        System.out.println((int)(rate * 100) + "%");
    }

    private static float profitRate(final List<Float[]> tradePairs) {
        float principal = 10000;
        float balance = principal;
        for (Float[] pair : tradePairs) {
            float buy = pair[0];
            float sell = pair[1];
            float quantity = balance/buy;
            balance = quantity * sell;
        }
        return (balance-principal)/principal;
    }
}
