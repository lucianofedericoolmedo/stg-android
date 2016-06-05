package com.ingsoft.magic_assistant.data;

/**
 * Created by luciano on 05/06/16.
 */
public abstract class Life {

    public Integer rep;
    public Integer venonLevel;

    public Life(){
        this.rep = 20;
        this.venonLevel = 0;
    }

    public boolean venonIsTooHigh(){
        return this.venonLevel == 14;
    }

    public void decrementVenon() {
        this.venonLevel = this.venonLevel -1;
    }

    public void incrementVenon() {
        this.venonLevel = this.venonLevel + 1;
    }

    public boolean hasNoMoreLifesLeft(){
        return this.rep == 0;
    }

    public boolean isEnding(){
        return this.hasNoMoreLifesLeft() || this.venonIsTooHigh();
    }

    public abstract String lose_message();

    public String lose_title() {
        return "Juego Finalizado";
    }

    @Override
    public String toString(){
        return String.valueOf(this.rep);
    }

    public void decrement() {
        this.rep = this.rep -1;
    }

    public void increment() {
        this.rep = this.rep + 1;
    }
}


