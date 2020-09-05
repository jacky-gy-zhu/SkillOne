package com.skillone.designpattern.observer;

public class Client {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditions currentConditions = new CurrentConditions();

        weatherData.registerObserver(currentConditions);

        System.out.println("通知各个观察者，看看信息");
        weatherData.setData(10f,20,30f);
    }

}
