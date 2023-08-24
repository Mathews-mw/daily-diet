package com.mathews.daily_diet.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mathews.daily_diet.model.Meal

@Dao
interface MealDAO {
    @Query("SELECT * FROM meal")
    fun indexAll(): List<Meal>;

    @Query("SELECT * FROM meal WHERE meal.id = :id")
    fun findById(id: Long): Meal?;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg meal: Meal);

    @Update
    fun update(meal: Meal);

    @Delete
    fun delete(meal: Meal);
}