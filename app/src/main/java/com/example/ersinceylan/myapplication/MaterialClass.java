package com.example.ersinceylan.myapplication;

/**
 * Created by Ersin.Ceylan on 8/11/2017.
 */

public class MaterialClass
{
    private String kod;
    private String aciklama;
    private int check;

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public MaterialClass(String aciklama, String kod) {
        this.kod = kod;
        this.aciklama=aciklama;
        check=0;
    }

    public MaterialClass(){
        this.kod="asd";
        this.aciklama="asda";
        check=0;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Kod : " + kod + "Açıklama : " + aciklama);

        return stringBuilder.toString();
    }
}
