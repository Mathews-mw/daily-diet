package com.mathews.daily_diet.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mathews.daily_diet.databinding.ActivityMainBinding
import com.mathews.daily_diet.model.Meal
import com.mathews.daily_diet.model.MealList
import com.mathews.daily_diet.ui.adapters.DailyMealListAdapter
import com.mathews.daily_diet.ui.adapters.MealListGroupAdapter
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater);
    }

    private lateinit var mealListGroupRecyclerView: RecyclerView;

    val mealA = Meal(
        name = "X-salada",
        eated_at = LocalDate.of(2023, 8, 8),
        status = true
    );
    val mealB =
        Meal(name = "Bolo de Chocolate", eated_at = LocalDate.of(2023, 8, 5), status = true);
    val mealC = Meal(name = "Frango grelhado", eated_at = LocalDate.of(2023, 8, 5), status = false);

    val mealGroupListA = listOf(mealA, mealB, mealC);
    val mealGroupListB = listOf(mealA, mealB, mealC);
    val mealGroupListC = listOf(mealA, mealB, mealC);

    val mealDataListA = MealList(LocalDate.of(2023, 8, 5), mealGroupListA);
    val mealDataListB = MealList(LocalDate.of(2023, 8, 7), mealGroupListB);
    val mealDataListC = MealList(LocalDate.of(2023, 8, 13), mealGroupListC);
    val groupMealListData = listOf(mealDataListA, mealDataListB, mealDataListC);

    private val adapter = MealListGroupAdapter(context = this, mealListGroup = groupMealListData);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(binding.root);

        Log.i("mealDataList", groupMealListData.toString());
        Log.i("test", "apenas um log de teste");
        setupRecyclerView();
    }

    override fun onResume() {
        super.onResume();
    }

    private fun setupRecyclerView() {
        mealListGroupRecyclerView = binding.activityMainRecyclerview;
        mealListGroupRecyclerView.adapter = adapter;

    }
}