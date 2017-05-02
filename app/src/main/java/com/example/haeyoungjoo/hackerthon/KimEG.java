package com.example.haeyoungjoo.hackerthon;

/**
 * Created by Kang In-Chang on 2017-04-22.
 */

public class KimEG extends Professor{
    public KimEG(String name){
        super(name);
    }
    @Override
    public String getEmailAddress(){
        return "mailto:egkim@koreatech.ac.kr";
    }
    @Override
    public String getTelephoneNumber(){
        return "tel:(+82)0415601350";
    }
}
