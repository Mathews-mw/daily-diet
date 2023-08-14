package com.mathews.daily_diet.model

import java.time.LocalDate

data class Meal(val name: String, val eated_at: LocalDate, val status: Boolean) {}