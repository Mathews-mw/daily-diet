package com.mathews.daily_diet.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.mathews.daily_diet.R
import com.mathews.daily_diet.database.AppDatabase
import com.mathews.daily_diet.database.dao.MealDAO
import com.mathews.daily_diet.database.dao.MealListDAO
import com.mathews.daily_diet.databinding.ActivityRegisterMealBinding
import com.mathews.daily_diet.model.Meal
import com.mathews.daily_diet.model.MealList
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RegisterMealActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityRegisterMealBinding.inflate(layoutInflater);
    }

    private lateinit var buttonInside: Button;
    private lateinit var buttonOutside: Button;
    private lateinit var buttonCreateMeal: Button;
    private lateinit var textInputDate: TextInputEditText;
    private lateinit var textInputName: TextInputEditText;
    private lateinit var textInputTime: TextInputEditText;
    private lateinit var textInputDescription: TextInputEditText;

    private var isDietCorrect = false;
    private var mealList: MutableList<Meal> = mutableListOf()

    private lateinit var mealDao: MealDAO;
    private lateinit var mealListDao: MealListDAO;

    private val db by lazy {
        AppDatabase.instanceDb(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root);

        setupAppBar();

        mealDao = db.mealDao();
        mealListDao = db.mealListDao();

        buttonInside = binding.activityRegisterMealBtnInside;
        buttonOutside = binding.activityRegisterMealBtnOutside;
        buttonCreateMeal = binding.activityRegisterMealBtnCreate;
        textInputDate = binding.activityRegisterMealDateTextInput;
        textInputName = binding.activityRegisterMealNameTextInput;
        textInputTime = binding.activityRegisterMealTimeTextInput;
        textInputDescription = binding.activityRegisterMealDescriptionTextInput;

        buttonInside.setOnClickListener {
            isDietCorrect = true;

            buttonInside.backgroundTintList = getColorStateList(R.color.green_mid);
            buttonOutside.backgroundTintList = getColorStateList(R.color.gray_5);
        }
        buttonOutside.setOnClickListener {
            isDietCorrect = false;

            buttonOutside.backgroundTintList = getColorStateList(R.color.red_mid);
            buttonInside.backgroundTintList = getColorStateList(R.color.gray_5);
        }

        handlerCreateMeal();
    }

    private fun setupAppBar() {
        supportActionBar?.apply {
            title = "Nova refeição"
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, R.color.gray_4));
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handlerCreateMeal() {
        buttonCreateMeal.setOnClickListener {
            val name = textInputName.text.toString();
            val description = textInputDescription.text.toString();
            val date = textInputDate.text.toString();
            val time = textInputTime.text.toString();


            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            val dateFormatted = LocalDate.parse(date, formatter);

            val newMeal = Meal(name = name, description = description, eated_at = dateFormatted, eat_at_time = time, status = isDietCorrect);
            val mealListGroup = MealList(created_at = LocalDate.now(), mealList = mealList);

            mealDao.save(newMeal);
            mealListDao.save(mealListGroup)

            finish();
        }
    }
}