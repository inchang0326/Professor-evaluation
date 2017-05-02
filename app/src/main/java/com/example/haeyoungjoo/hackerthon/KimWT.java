package com.example.haeyoungjoo.hackerthon;

/**
 * Created by Kang in-Chang on 2017-04-22.
 */

public class KimWT extends Professor {
    public KimWT(String name){
        super(name);
    }
    @Override
    public String getEmailAddress(){
        return "mailto:wtkim@koreatech.ac.kr";
    }
    @Override
    public String getTelephoneNumber(){
        return "tel:(+82)0415601485";
    }
}
