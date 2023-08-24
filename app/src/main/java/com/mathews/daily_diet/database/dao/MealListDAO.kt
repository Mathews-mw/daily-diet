package com.mathews.daily_diet.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mathews.daily_diet.model.MealList

@Dao
interface MealListDAO {
    @Query("SELECT * FROM meal_list")
    fun indexAll(): List<MealList>;

    @Query("SELECT * FROM meal_list WHERE meal_list.id = :id")
    fun findById(id: Long): MealList?;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg meal: MealList);

    @Update
    fun update(meal: MealList);

    @Delete
    fun delete(meal: MealList);
}