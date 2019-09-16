package com.example.ersinceylan.myapplication;

/**
 * Created by Ersin.Ceylan on 8/22/2017.
 */

public class BillClass
{
    private String date;
    private String billNo;
    private String kind;
    private String client;
    private String amount;
    private String place;
    private String chapter;

    public BillClass(String date, String billNo, String kind, String client, String amount, String place, String chapter) {
        this.date = date;
        this.billNo = billNo;
        this.kind = kind;
        this.client = client;
        this.amount = amount;
        this.place = place;
        this.chapter = chapter;
    }

    public BillClass()
    {}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

}
