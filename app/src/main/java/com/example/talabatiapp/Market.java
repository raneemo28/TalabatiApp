package com.example.talabatiapp;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

public class Market {
    private  int marketId;
    private  String marketName;
    private  String marketLocation;
    private  int marketImageResource;
    private  int marketPhoneNum;
    private  LocalTime marketOpeningTime;
    private  LocalTime marketClosingTime;
    private boolean marketAvailable;
    public int getMarketId() {
        return marketId;
    }
    public String getMarketName() {
        return marketName;
    }
    public String getMarketLocation() {
        return marketLocation;
    }
    public int getMarketPhoneNum() {
        return marketPhoneNum;
    }
    public LocalTime getMarketClosingTime() {
        return marketClosingTime;
    }
    public LocalTime getMarketOpeningTime() {
        return marketOpeningTime;
    }



    public int getMarketImageResource() { return marketImageResource; }
    public void setMarketAvailable() {
        LocalTime timeNow = LocalTime.now();
        if (timeNow.isBefore(marketOpeningTime) || timeNow.isAfter(marketClosingTime)) this.marketAvailable=false;
        else this.marketAvailable=true;
    }

    public boolean isMarketAvailable() {
        setMarketAvailable();
        return marketAvailable;
    }

    public Market(int marketId, String marketName,  String marketLocation,
                  LocalTime marketOpeningTime, LocalTime marketClosingTime, int marketPhoneNum, int marketImageResource)
    {
        this.marketId=marketId;
        this.marketName=marketName;
        this.marketLocation=marketLocation;
        this.marketPhoneNum=marketPhoneNum;
        this.marketClosingTime=marketClosingTime;
        this.marketOpeningTime=marketOpeningTime;
        this.marketImageResource=marketImageResource;
    }

}
