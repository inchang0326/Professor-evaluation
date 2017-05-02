package com.example.haeyoungjoo.hackerthon;

/**
 * Created by Kang In-Chang on 2017-04-22.
 */

public class KwonOY extends Professor {
    public KwonOY(String name){
        super(name);
    }
    @Override
    public String getEmailAddress(){
        return "mailto:oykwon@koreatech.ac.kr";
    }
    @Override
    public String getTelephoneNumber(){ return "tel:(+82)0415601354"; }
}
