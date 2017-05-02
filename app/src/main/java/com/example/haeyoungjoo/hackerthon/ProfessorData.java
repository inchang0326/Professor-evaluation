package com.example.haeyoungjoo.hackerthon;

/**
 * Created by Kang In-Chang on 2017-04-22.
 */

public enum ProfessorData {
    최영규{
        public int getDrawable(){
            return R.drawable.cyg;
        }
        public int getLayout(){ return R.layout.cyg; }
        public String getDesc(){ return "최영규"; }
        public String getURL(){ return url+"cyg"; }
        public int[] getRatingBar(){
            int[] ratingBar = new int[3];
            ratingBar[0] = R.id.cyg_cplusplus_Average;
            ratingBar[1] = R.id.cyg_datastructure_Average;
            ratingBar[2] = R.id.cyg_graphic_Average;
            return ratingBar;
        }
    }, 정구철{
        public int getDrawable(){ return R.drawable.jgc; }
        public int getLayout(){ return R.layout.jgc; }
        public String getDesc(){ return "정구철"; }
        public String getURL(){ return url+"jgc"; }
        public int[] getRatingBar(){
            int[] ratingBar = new int[2];
            ratingBar[0] = R.id.jgc_computerEngineerBasic_Average;
            ratingBar[1] = R.id.jgc_C_Average;
            return ratingBar;
        }
    }, 장경식{
        public int getDrawable(){
            return R.drawable.jgs;
        }
        public int getLayout(){ return R.layout.jgs; }
        public String getDesc(){ return "장경식"; }
        public String getURL(){ return url+"jgs"; }
        public int[] getRatingBar(){
            int[] ratingBar = new int[2];
            ratingBar[0] = R.id.jgs_microprocessor_Average;
            ratingBar[1] = R.id.jgs_embedded_Average;
            return ratingBar;
        }
    }, 김윤상{
        public int getDrawable(){
            return R.drawable.kys;
        }
        public int getLayout(){ return R.layout.kys; }
        public String getDesc(){
            return "김윤상";
        }
        public String getURL(){ return url+"kys"; }
        public int[] getRatingBar(){
            int[] ratingBar = new int[3];
            ratingBar[0] = R.id.kys_base_electric_electron_Average;
            ratingBar[1] = R.id.ys_computer_architecture_Average;
            ratingBar[2] = R.id.kys_animation_Average;
            return ratingBar;
        }
    }, 이재협{
        public int getDrawable(){
            return R.drawable.ljh;
        }
        public int getLayout(){ return R.layout.ljh; }
        public String getDesc(){
            return "이재협";
        }
        public String getURL(){ return url+"ljh"; }
        public int[] getRatingBar(){
            int[] ratingBar = new int[3];
            ratingBar[0] = R.id.ljh_C_Average;
            ratingBar[1] = R.id.ljh_Cplusplus_Average;
            ratingBar[2] = R.id.ljh_java_Average;
            return ratingBar;
        }
    }, 윤한경{
        public int getDrawable(){ return R.drawable.yhg; }
        public int getLayout(){ return R.layout.uhg; }
        public String getDesc(){
            return "윤한경";
        }
        public String getURL(){ return url+"uhg"; }
        public int[] getRatingBar(){
            int[] ratingBar = new int[2];
            ratingBar[0] = R.id.uhg_ai_Average;
            ratingBar[1] = R.id.uhg_trash_Average;
            return ratingBar;
        }
    }, 한연희{
        public int getDrawable(){
            return R.drawable.hyh;
        }
        public int getLayout(){ return R.layout.hanyunhee; }
        public String getDesc(){ return "한연희"; }
        public String getURL(){ return url+"hyh"; }
        public int[] getRatingBar() {
            int[] ratingBar = new int[3];
            ratingBar[0] = R.id.hyh_network_Average;
            ratingBar[1] = R.id.hyh_script_Average;
            ratingBar[2] = R.id.hyh_spring_Average;
            return ratingBar;
        }
    }, 조재수{
        public int getDrawable(){
            return R.drawable.jjs;
        }
        public int getLayout(){ return R.layout.jojaesu; }
        public String getDesc(){
            return "조재수";
        }
        public String getURL(){ return url+"jjs"; }
        public int[] getRatingBar(){
            int[] ratingBar = new int[4];
            ratingBar[0] = R.id.jjs_cplusplus_Average;
            ratingBar[1] = R.id.jjs_digital_Average;
            ratingBar[2] = R.id.jjs_digitalsignal_Average;
            ratingBar[3] = R.id.jjs_c_Average;
            return ratingBar;
        }
    }, 주영복{
        public int getDrawable(){
            return R.drawable.jyb;
        }
        public int getLayout(){ return R.layout.jyb; }
        public String getDesc(){
            return "주영복";
        }
        public String getURL(){ return url+"jyb"; }
        public int[] getRatingBar(){
            int[] ratingBar = new int[4];
            ratingBar[0] = R.id.jyb_window_Average;
            ratingBar[1] = R.id.jyb_digital_Average;
            ratingBar[2] = R.id.jyb_java_Average;
            ratingBar[3] = R.id.jyb_datastructure_Average;
            return ratingBar;
        }
    }, 조태훈{
        public int getDrawable(){ return R.drawable.jth; }
        public int getLayout(){ return R.layout.jth; }
        public String getDesc(){
            return "조태훈";
        }
        public String getURL(){ return url+"jth"; }
        public int[] getRatingBar(){
            int[] ratingBar = new int[2];
            ratingBar[0] = R.id.jth_c_Average;
            ratingBar[1] = R.id.jth_computervision_Average;
            return ratingBar;
        }
    }, 강형주{
        public int getDrawable(){
            return R.drawable.khj;
        }
        public int getLayout(){ return R.layout.kanghj; }
        public String getDesc(){ return "강형주"; }
        public String getURL(){ return url+"khj"; }
        public int[] getRatingBar(){
            int[] ratingBar = new int[4];
            ratingBar[0] = R.id.khj_baseelectricelectron_Average;
            ratingBar[1] = R.id.khj_c_Average;
            ratingBar[2] = R.id.khj_digital_Average;
            ratingBar[3] = R.id.khj_c_Average;
            return ratingBar;
        }
    }, 이강환{
        public int getDrawable(){ return R.drawable.lgh; }
        public int getLayout(){ return R.layout.leekh; }
        public String getDesc(){
            return "이강환";
        }
        public String getURL(){ return url+"lkh"; }
        public int[] getRatingBar(){
            int[] ratingBar = new int[3];
            ratingBar[0] = R.id.lkh_base_Average;
            ratingBar[1] = R.id.lkh_digital_Average;
            ratingBar[2] = R.id.lkh_digitalsystem_Average;
            return ratingBar;
        }
    }, 강승우{
        public int getDrawable(){
            return R.drawable.ksw;
        }
        public int getLayout(){ return R.layout.kangsw; }
        public String getDesc(){
            return "강승우";
        }
        public String getURL(){ return url+"ksw"; }
        public int[] getRatingBar(){
            int[] ratingBar = new int[4];
            ratingBar[0] = R.id.ksw_computer_Average;
            ratingBar[1] = R.id.ksw_mobile_Average;
            ratingBar[2] = R.id.ksw_mobilesystem_Average;
            ratingBar[3] = R.id.ksw_script_Average;
            return ratingBar;
        }
    }, 김은경{
        public int getDrawable(){
            return R.drawable.keg;
        }
        public int getLayout(){ return R.layout.kimek; }
        public String getDesc(){
            return "김은경";
        }
        public String getURL(){ return url+"kek"; }
        public int[] getRatingBar(){
            int[] ratingBar = new int[2];
            ratingBar[0] = R.id.kek_creative_Average;
            ratingBar[1] = R.id.kek_database_Average;
            return ratingBar;
        }
    }, 김상진{
        public int getDrawable(){
            return R.drawable.ksj;
        }
        public int getLayout(){ return R.layout.kimsj; }
        public String getDesc(){
            return "김상진";
        }
        public String getURL(){ return url+"ksj"; }
        public int[] getRatingBar(){
            int[] ratingBar = new int[3];
            ratingBar[0] = R.id.ksj_java_Average;
            ratingBar[1] = R.id.ksj_object_Average;
            ratingBar[2] = R.id.ksj_protect_Average;
            return ratingBar;
        }
    }, 김상연{
        public int getDrawable(){
            return R.drawable.ksy;
        }
        public int getLayout(){ return R.layout.kimsy; }
        public String getDesc(){
            return "김상연";
        }
        public String getURL(){ return url+"ksy"; }
        public int[] getRatingBar(){
            int[] ratingBar = new int[2];
            ratingBar[0] = R.id.ksy_microprocessor_Average;
            ratingBar[1] = R.id.ksy_smart_Average;
            return ratingBar;
        }
    }, 김원태{
        public int getDrawable(){ return R.drawable.kwt; }
        public int getLayout(){ return R.layout.kimwt; }
        public String getDesc(){
            return "김원태";
        }
        public String getURL() {return url+"kwt"; }
        public int[] getRatingBar(){
            int[] ratingBar = new int[4];
            ratingBar[0] = R.id.kwt_base_Average;
            ratingBar[1] = R.id.kwt_data_Average;
            ratingBar[2] = R.id.kwt_network_Average;
            ratingBar[3] = R.id.kwt_smart_Average;
            return ratingBar;
        }
    }, 권오영{
        public int getDrawable(){ return R.drawable.koy; }
        public int getLayout(){ return R.layout.kwonoy; }
        public String getDesc(){
            return "권오영";
        }
        public String getURL(){ return url+"koy"; }
        public int[] getRatingBar(){
            int[] ratingBar = new int[4];
            ratingBar[0] = R.id.koy_compile_Average;
            ratingBar[1] = R.id.koy_embeded_Average;
            ratingBar[2] = R.id.koy_operation_Average;
            ratingBar[3] = R.id.koy_script_Average;
            return ratingBar;
        }
    }, 민준기{
        public int getDrawable(){ return R.drawable.mjg; }
        public int getLayout(){ return R.layout.minjk; }
        public String getDesc(){
            return "민준기";
        }
        public String getURL(){ return url+"mjk"; }
        public int[] getRatingBar(){
            int[] ratingBar = new int[2];
            ratingBar[0] = R.id.mjk_database_Average;
            ratingBar[1] = R.id.mjk_software_Average;
            return ratingBar;
        }
    }, 무하마드{
        public int getDrawable(){
            return R.drawable.mohamed;
        }
        public int getLayout(){ return R.layout.mohamad; }
        public String getDesc(){ return "무하마드"; }
        public String getURL(){ return url+"mhmd"; }
        public int[] getRatingBar(){
            int[] ratingBar = new int[4];
            ratingBar[0] = R.id.mhmd_algo_Average;
            ratingBar[1] = R.id.mhmd_databasepro_Average;
            ratingBar[2] = R.id.mhmd_datastructure_Average;
            ratingBar[3] = R.id.mhmd_java_Average;
            return ratingBar;
        }
    }, 문일영{
        public int getDrawable(){
            return R.drawable.miy;
        }
        public int getLayout(){ return R.layout.mooniy; }
        public String getDesc(){
            return "문일영";
        }
        public String getURL(){ return url+"miy"; }
        public int[] getRatingBar(){
            int[] ratingBar = new int[3];
            ratingBar[0] = R.id.miy_web_Average;
            ratingBar[1] = R.id.miy_advancedweb_Average;
            ratingBar[2] = R.id.miy_mobile_Average;
            return ratingBar;
        }
    };

    private static final String url = "http://jhy753.dothome.co.kr/avgCalculater.php?professorName=";

    public abstract int getDrawable();
    public abstract int getLayout();
    public abstract String getDesc();
    public abstract String getURL();
    public abstract int[] getRatingBar();
}