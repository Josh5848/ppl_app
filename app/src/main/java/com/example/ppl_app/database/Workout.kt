package com.example.ppl_app.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    var workoutTitle: String? = null,
    var workoutDescription: String? = null,
    var workoutType: String? = null ,
    var workoutSets: Int? = null,
    var workoutReps: Int? = null,
    var workoutWeight : Int? = null)
