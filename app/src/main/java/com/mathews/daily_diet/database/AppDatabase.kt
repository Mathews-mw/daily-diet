package com.mathews.daily_diet.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mathews.daily_diet.database.dao.MealDAO
import com.mathews.daily_diet.database.dao.MealListDAO
import com.mathews.daily_diet.model.Meal
import com.mathews.daily_diet.model.MealList

@Database(entities = [Meal::class, MealList::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDAO;
    abstract fun mealListDao(): MealListDAO;

    companion object {
        @Volatile
        private lateinit var db: AppDatabase;

        fun instanceDb(context: Context): AppDatabase {
            if (::db.isInitialized) return db;

            db = Room.databaseBuilder(context, AppDatabase::class.java, "dailyDiet.db")
                .allowMainThreadQueries()
                .build();

            return db;
        }
    }
}