package com.example.haeyoungjoo.hackerthon;

/**
 * Created by Kang In-Chang on 2017-04-22.
 */

public class MinJK extends Professor {
    public MinJK(String name){
        super(name);
    }
    @Override
    public String getEmailAddress(){
        return "mailto:jkmin@koreatech.ac.kr";
    }
    @Override
    public String getTelephoneNumber(){ return "tel:(+82)0415601494"; }
}
