package com.stefanoiaconetti.personalfitness_app;

/**
 * Created by Stefano on 1/7/2018.
 */

public class CalorieLog {

    private String foodDrinkItem;
    private String calories;

    public CalorieLog(){

    }

    public CalorieLog(String foodDrinkItem, String calories){
        this.foodDrinkItem = foodDrinkItem;
        this.calories = calories;
    }

    public String getFoodDrinkItem() { return foodDrinkItem;}

    public void setFoodDrinkItem(String foodDrinkItem){ this.foodDrinkItem = foodDrinkItem;}

    public String getCalories() { return calories;}

    public void setCalories(String calories){ this.calories = calories;}
}
