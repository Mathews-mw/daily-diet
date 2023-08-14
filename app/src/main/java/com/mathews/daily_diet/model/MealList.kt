package com.mathews.daily_diet.model

import java.time.LocalDate

data class MealList(val created_at: LocalDate, val mealList: List<Meal>) {}