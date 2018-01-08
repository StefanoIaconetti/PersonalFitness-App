package com.stefanoiaconetti.personalfitness_app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Stefano on 1/7/2018.
 */

public class CustomRecyclerViewAdapterCalories extends RecyclerView.Adapter {
        private ArrayList<CalorieLog> myCalories;

        public CustomRecyclerViewAdapterCalories(ArrayList<CalorieLog> myCalories) {
            this.myCalories = myCalories;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, null);
            CustomViewHolder viewHolder = new CustomViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            CalorieLog calories = myCalories.get(position);

            ((CustomViewHolder) holder).foodDrinkItem.setText(calories.getFoodDrinkItem());
            ((CustomViewHolder) holder).calories.setText(calories.getCalories());



        }

        @Override
        public int getItemCount() {
            if (myCalories != null) {
                return myCalories.size();
            }
            return 0;
        }

        class CustomViewHolder extends RecyclerView.ViewHolder {
            protected TextView foodDrinkItem;
            protected TextView calories;

            public CustomViewHolder(View view) {
                super(view);
                this.foodDrinkItem = view.findViewById(R.id.step);
                this.calories = view.findViewById(R.id.description);
            }

        }

    }
