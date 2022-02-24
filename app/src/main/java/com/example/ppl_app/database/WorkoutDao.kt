package com.example.ppl_app.database

import androidx.room.*

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workouts")
    fun getAll(): List<Workout>

    @Query("SELECT * FROM workouts WHERE workoutType IS (:workoutType)")
    suspend fun loadAllByType(workoutType: String): List<Workout>

    @Query("SELECT * FROM workouts WHERE uid IS :uid")
    fun loadById(uid:Int): Workout

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (workout: Workout)

    @Delete
    suspend fun delete(workout: Workout)
}
