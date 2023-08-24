package com.mathews.daily_diet.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mathews.daily_diet.database.AppDatabase
import com.mathews.daily_diet.database.dao.MealListDAO
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
    private lateinit var addMealButton: Button

    private lateinit var  mealListDao: MealListDAO;

//    val mealA = Meal(
//        name = "X-salada",
//        eated_at = LocalDate.of(2023, 8, 8),
//        eat_at_time = "12:00",
//        status = true
//    );
//    val mealB =
//        Meal(name = "Bolo de Chocolate", eated_at = LocalDate.of(2023, 8, 5), eat_at_time = "20:00", status = true);
//    val mealC = Meal(name = "Frango grelhado", eated_at = LocalDate.of(2023, 8, 5), eat_at_time = "16:45", status = false);
//
//    val mealGroupListA = listOf(mealA, mealB, mealC);
//    val mealGroupListB = listOf(mealA, mealB, mealC);
//    val mealGroupListC = listOf(mealA, mealB, mealC);
//
//    val mealDataListA = MealList(LocalDate.of(2023, 8, 5), mealGroupListA);
//    val mealDataListB = MealList(LocalDate.of(2023, 8, 7), mealGroupListB);
//    val mealDataListC = MealList(LocalDate.of(2023, 8, 13), mealGroupListC);
//    val groupMealListData = listOf(mealDataListA, mealDataListB, mealDataListC);
//
    private val adapter = MealListGroupAdapter(context = this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(binding.root);

        setupRecyclerView();
        intentRegisterMealActivity();


    }

    override fun onResume() {
        super.onResume();

        val db =AppDatabase.instanceDb(context = this);
        mealListDao = db.mealListDao();

        adapter.updateInfos(mealListDao.indexAll());
    }

    private fun intentRegisterMealActivity() {
        addMealButton = binding.activityMainBtnAddMeal;
        addMealButton.setOnClickListener {
            val intent = Intent(this, RegisterMealActivity::class.java);
            startActivity(intent);
        }
    }

    private fun setupRecyclerView() {
        mealListGroupRecyclerView = binding.activityMainRecyclerview;
        mealListGroupRecyclerView.adapter = adapter;

    }
}