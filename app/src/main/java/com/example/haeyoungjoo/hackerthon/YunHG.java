package com.example.haeyoungjoo.hackerthon;

/**
 * Created by Kang in-Chang on 2017-04-22.
 */

public class YunHG extends Professor {
    public YunHG(String name){
        super(name);
    }
    @Override
    public String getEmailAddress(){
        return "mailto:hkyun@koreatech.ac.kr";
    }
    @Override
    public String getTelephoneNumber(){
        return "tel:(+82)0415601182";
    }
}
