package com.mathews.daily_diet.ui.adapters

import android.content.Context
import android.graphics.PorterDuff
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mathews.daily_diet.R
import com.mathews.daily_diet.databinding.MealItemBinding
import com.mathews.daily_diet.model.Meal
import java.time.LocalDate

class DailyMealListAdapter(
    private val context: Context,
    dailyMealList: List<Meal> = emptyList()
) :
    RecyclerView.Adapter<DailyMealListAdapter.ViewHolder>() {

    private val dailyMealList = dailyMealList.toMutableList();

    inner class ViewHolder(private val binding: MealItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun attach(meal: Meal) {

            val name = binding.mealItemName;
            val eatedAt = binding.mealItemTime;
            val status = binding.mealItemStatus;

            name.text = meal.name;
            eatedAt.text = meal.eated_at.toString();

            val newDrawable = ContextCompat.getDrawable(context, R.drawable.meal_status_shape)?.mutate()
            newDrawable?.let {
                val color: Int;

                when (meal.status) {
                    true -> {
                        color = ContextCompat.getColor(
                            context,
                            R.color.green_mid
                        )
                    }
                    false -> {
                        color = ContextCompat.getColor(
                            context,
                            R.color.red_mid
                        )
                    }
                }

                it.setColorFilter(color, PorterDuff.Mode.SRC_IN)
                status.background = it
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context);
        val binding = MealItemBinding.inflate(layoutInflater, parent, false);

        return ViewHolder(binding);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = dailyMealList[position];
        Log.i("meal", meal.toString())
        holder.attach(meal);
    }

    override fun getItemCount(): Int {
        return dailyMealList.size;
    }

    fun updateInfos(meals: List<Meal>) {
        this.dailyMealList.clear();
        this.dailyMealList.addAll(meals);

        notifyDataSetChanged();
    }
}