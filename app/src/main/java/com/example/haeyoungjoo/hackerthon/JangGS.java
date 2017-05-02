package com.example.haeyoungjoo.hackerthon;

/**
 * Created by Kang in-Chang on 2017-04-22.
 */

public class JangGS extends Professor {
    public JangGS(String name){
        super(name);
    }
    @Override
    public String getEmailAddress(){
        return "mailto:ksjang@koreatech.ac.kr";
    }
    @Override
    public String getTelephoneNumber(){ return "tel:(+82)0415601352"; }
}
