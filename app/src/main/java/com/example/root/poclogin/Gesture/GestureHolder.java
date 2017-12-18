package com.example.root.poclogin.Gesture;

import android.gesture.Gesture;

/**
 * Created by root on 17/12/17.
 */

public class GestureHolder {
    private Gesture gesture;
    private String gestureNaam;

    public GestureHolder(Gesture gesture, String naam){
        this.gesture = gesture;
        this.gestureNaam = naam;
    }

    public Gesture getGesture(){
        return gesture;
    }

    public void setGesture(Gesture gesture){
        this.gesture = gesture;
    }

    public String getNaam(){
        return gestureNaam;
    }

    public void setNaam(String naam){
        this.gestureNaam = naam;
    }

}
