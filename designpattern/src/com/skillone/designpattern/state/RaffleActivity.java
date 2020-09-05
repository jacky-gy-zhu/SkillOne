package com.skillone.designpattern.state;

public class RaffleActivity {

    State state = null;
    int count = 0;

    public RaffleActivity(int count) {
        this.count = count;
    }

    public void deduceMoney() {
        state.deduceMoney();
    }

    public void raffle() {
        if (state.raffle()) {
            state.dispensePrize();
        }
    }

    public void dispensePrize() {

    }

}
