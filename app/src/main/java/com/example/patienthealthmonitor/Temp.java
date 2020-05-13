package com.example.patienthealthmonitor;

public class Temp {
    int _id;
    String _temp;
    String _hum;
    String _time;
    String _bps;
    String _bp;
    public Temp(){   }
    public Temp(int id, String temp,String hum,String bps,String bp, String _time){
        this._id = id;
        this._temp = temp;
        this._hum = hum;
        this._bps = bps;
        this._bp = bp;
        this._time = _time;
    }

    public Temp(String temp,String hum,String bps,String bp){
        this._temp = temp;
        this._hum = hum;
        this._bps = bps;
        this._bp = bp;
        this._time = _time;
    }
    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String gettemp(){
        return this._temp;
    }

    public void settemp(String b){
        this._temp = b;
    }

    public String getbps(){
        return this._bps;
    }

    public void setbps(String c){
        this._bps = c;
    }

    public String getbp(){
        return this._bp;
    }

    public void setbp(String d){
        this._bp = d;
    }

    public String gethum(){
        return this._hum;
    }

    public void sethum(String a){
        this._hum = a;
    }

    public String gettime(){
        return this._time;
    }

    public void settime(String time){
        this._time = time;
    }

}
