package com.skillone.designpattern.facade;

public class HomeTheaterFacade {

    //定义各个子系统的对象
    private DvdPlayer dvdPlayer;
    private PopCorn popCorn;
    private Projector projector;

    public HomeTheaterFacade() {
        this.dvdPlayer = DvdPlayer.getInstance();
        this.popCorn = PopCorn.getInstance();
        this.projector = Projector.getInstance();
    }

    public void ready() {
        popCorn.on();
        popCorn.pop();
        dvdPlayer.on();
        projector.on();
        projector.focus();
    }

    public void play() {
        dvdPlayer.play();
    }

    public void end() {
        popCorn.off();
        dvdPlayer.off();
        projector.off();
    }

}
