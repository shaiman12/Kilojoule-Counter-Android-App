package com.example.kilojoulecounter;


import android.content.res.Resources;

public class DiaryEntry {
    private String dateToday;
    private int breakfast;
    private int lunch;
    private int dinner;
    private int snacks;
    private int foodTotal;
    private int gym;
    private int sports;
    private int jogging;
    private int exTotal;
    private int netTotal;



    public DiaryEntry(String dateToday, int breakfast, int lunch, int dinner, int snacks, int foodTotal, int gym, int sports, int jogging, int exTotal, int netTotal) {
        this.dateToday = dateToday;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.snacks = snacks;
        this.foodTotal = foodTotal;
        this.gym = gym;
        this.sports = sports;
        this.jogging = jogging;
        this.exTotal = exTotal;
        this.netTotal = netTotal;

    }

    public String getDateToday() {
        return dateToday;
    }

    public void setDateToday(String dateToday) {
        this.dateToday = dateToday;
    }

    public int getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(int breakfast) {
        this.breakfast = breakfast;
    }

    public int getLunch() {
        return lunch;
    }

    public void setLunch(int lunch) {
        this.lunch = lunch;
    }

    public int getDinner() {
        return dinner;
    }

    public void setDinner(int dinner) {
        this.dinner = dinner;
    }

    public int getSnacks() {
        return snacks;
    }

    public void setSnacks(int snacks) {
        this.snacks = snacks;
    }

    public int getFoodTotal() {
        return foodTotal;
    }

    public void setFoodTotal(int foodTotal) {
        this.foodTotal = foodTotal;
    }

    public int getGym() {
        return gym;
    }

    public void setGym(int gym) {
        this.gym = gym;
    }

    public int getSports() {
        return sports;
    }

    public void setSports(int sports) {
        this.sports = sports;
    }

    public int getJogging() {
        return jogging;
    }

    public void setJogging(int jogging) {
        this.jogging = jogging;
    }

    public int getExTotal() {
        return exTotal;
    }

    public void setExTotal(int exTotal) {
        this.exTotal = exTotal;
    }

    public int getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(int netTotal) {
        this.netTotal = netTotal;
    }




public String toString(){
        return "Date:  " +dateToday+"\n\n" +
                "Breakfast:  " + breakfast +
                "\nLunch:  " + lunch +
                "\nDinner:  " + dinner +
                "\nSnacks:  " + snacks+
                "\nTotal Food Kilojoules:  "+foodTotal+
                "\n\nGym:  " +gym +
                "\nSports: " +sports+
                "\nJogging:  " +jogging+


                "\nExercise Kilojoules:  "+exTotal+"\n\nNett Kilojoule Intake:  "+netTotal;
}








}
