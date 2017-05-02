package com.example.haeyoungjoo.hackerthon;

/**
 * Created by Kang in-Chang on 2017-04-22.
 */

public class KimSJ extends Professor {
    public KimSJ(String name){
        super(name);
    }
    @Override
    public String getEmailAddress(){
        return "mailto:sangjin@koreatech.ac.kr";
    }
    @Override
    public String getTelephoneNumber(){
        return "tel:(+82)0415601490";
    }
}
