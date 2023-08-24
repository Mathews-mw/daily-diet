package com.mathews.daily_diet.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Entity(tableName = "meal")
@Parcelize
data class Meal(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val description: String? = null,
    val eated_at: LocalDate,
    val eat_at_time: String,
    val status: Boolean
) : Parcelable {}