package com.example.haeyoungjoo.hackerthon;

/**
 * Created by Kang in-Chang on 2017-04-22.
 */

public class CSEProfessorGenerator extends ProfessorGenerator{
    @Override
    protected Professor selectProfessor(ProfessorData name){
        switch (name){
            case 최영규: return new ChoiYG(name.getDesc());
            case 정구철: return new JeongGC(name.getDesc());
            case 장경식: return new JangGS(name.getDesc());
            case 김윤상: return new KimYS(name.getDesc());
            case 이재협: return new LeeJH(name.getDesc());
            case 윤한경: return new YunHG(name.getDesc());
            case 한연희: return new HanYH(name.getDesc());
            case 조재수: return new JoJS(name.getDesc());
            case 주영복: return new JooYB(name.getDesc());
            case 조태훈: return new JoTH(name.getDesc());
            case 강형주: return new KangHJ(name.getDesc());
            case 이강환: return new LeeGH(name.getDesc());
            case 강승우: return new KangSW(name.getDesc());
            case 김은경: return new KimEG(name.getDesc());
            case 김상진: return new KimSJ(name.getDesc());
            case 김상연: return new KimSY(name.getDesc());
            case 김원태: return new KimWT(name.getDesc());
            case 권오영: return new KwonOY(name.getDesc());
            case 민준기: return new MinJK(name.getDesc());
            case 무하마드: return new Mohamed(name.getDesc());
            case 문일영: return new MoonIY(name.getDesc());
            default: throw new IllegalArgumentException(name + " 교수님은 명단에 없습니다.");
        }
    }
}
