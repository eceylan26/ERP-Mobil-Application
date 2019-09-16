package com.example.ersinceylan.myapplication;

/**
 * Created by Ersin.Ceylan on 8/11/2017.
 */

public class CariAccClass
{
    private String isim;
    private String unvan;
    private String mevduat;
    private String kod;

    public CariAccClass()
    {

    }

    public CariAccClass(String isim, String unvan, String mevduat, String kod) {
        this.isim = isim;
        this.unvan = unvan;
        this.mevduat = mevduat;
        this.kod = kod;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }

    public String getMevduat() {
        return mevduat;
    }

    public void setMevduat(String mevduat) {
        this.mevduat = mevduat;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("İsim : " + isim + "\n"+"Ünvan : " + unvan + "\n"+"Mevduat : " + mevduat +"\n"
        + "E-is Kodu : " + kod );

        return stringBuilder.toString();
    }
}
