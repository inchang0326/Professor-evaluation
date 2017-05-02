package com.example.haeyoungjoo.hackerthon;

/**
 * Created by Kang in-Chang on 2017-04-22.
 */

public class JoTH extends Professor {
    public JoTH(String name){
        super(name);
    }
    @Override
    public String getEmailAddress(){
        return "mailto:thcho@koreatech.ac.kr";
    }
    @Override
    public String getTelephoneNumber(){ return "tel:(+82)0415601351"; }
}
