package com.nivek.kevoweather;

import java.util.ArrayList;
import java.util.List;

public class NigerianState {

    private String latitude ;
    private String longitude ;
    private String name ;
    private String desc ;
    private String lowTemp ;
    private String highTemp ;
    private String icon ;

    public static NigerianState[] nigerianStates ={
            new NigerianState("Lagos" , "6.454066","3.394673"),
            new NigerianState("Kano" , "12.002381","8.51316") ,
            new NigerianState("Abuja","9.083333","7.533333"),
            new NigerianState("Kaduna","10.526413","7.438795"),
            new NigerianState("Benin City","6.338153","5.625749"),
            new NigerianState("Ikare","7.525913","5.753417") ,
            new NigerianState("Port Harcourt","4.777423","7.013404")
    } ;

    private NigerianState(String name , String latitude , String longitude){
        this.name = name ;
        this.latitude = latitude ;
        this.longitude = longitude ;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(String lowTemp) {
        this.lowTemp = lowTemp;
    }

    public String getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(String highTemp) {
        this.highTemp = highTemp;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
