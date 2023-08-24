package com.mathews.daily_diet.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Entity(tableName = "meal_list")
@Parcelize
data class MealList(@PrimaryKey(autoGenerate = true) val id: Long = 0L, val created_at: LocalDate, val mealList: List<Meal>) : Parcelable {}