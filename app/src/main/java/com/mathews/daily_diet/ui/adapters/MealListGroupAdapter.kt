package com.mathews.daily_diet.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mathews.daily_diet.databinding.DayListBinding
import com.mathews.daily_diet.model.MealList

class MealListGroupAdapter(
    private val context: Context,
    mealListGroup: List<MealList> = emptyList()
) : RecyclerView.Adapter<MealListGroupAdapter.ViewHolder>() {

    private val mealListGroup = mealListGroup.toMutableList();

    inner class ViewHolder(private val binding: DayListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val dailyMealReciclerView: RecyclerView = binding.dayListRecyclerView;
        val dayListDate: TextView = binding.dayListDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context);
        val binding = DayListBinding.inflate(layoutInflater, parent, false);

        return ViewHolder(binding);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mealList = mealListGroup[position];

        val dailyMealAdapter =
            DailyMealListAdapter(context = context, dailyMealList = mealList.mealList);

        holder.dayListDate.text = mealList.created_at.toString();

        holder.dailyMealReciclerView.apply {
            layoutManager = LinearLayoutManager(context);
            adapter = dailyMealAdapter;
        }
    }

    override fun getItemCount(): Int {
        return mealListGroup.size;
    }

    fun updateInfos(mealList: List<MealList>) {
        this.mealListGroup.clear();
        this.mealListGroup.addAll(mealList);

        notifyDataSetChanged();
    }
}