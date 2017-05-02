package com.example.haeyoungjoo.hackerthon;

import java.io.Serializable;

/**
 * Created by Kang in-Chang on 2017-04-22.
 */

public abstract class Professor implements Serializable {
    private String name;
    public Professor(String name){
        this.name = name;
    }
    public String getName(){ return name; }
    public abstract String getTelephoneNumber();
    public abstract String getEmailAddress();
}