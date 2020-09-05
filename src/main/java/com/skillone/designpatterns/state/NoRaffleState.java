package com.skillone.designpatterns.state;

public class NoRaffleState extends State {

    RaffleActivity raffleActivity;

    public NoRaffleState(RaffleActivity raffleActivity) {
        this.raffleActivity = raffleActivity;
    }

    @Override
    public void deduceMoney() {

    }

    @Override
    public boolean raffle() {
        return false;
    }

    @Override
    public void dispensePrize() {

    }
}
