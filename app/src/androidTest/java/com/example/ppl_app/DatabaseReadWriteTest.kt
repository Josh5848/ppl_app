package com.example.ppl_app

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4

import androidx.test.platform.app.InstrumentationRegistry

import com.example.ppl_app.database.Workout
import com.example.ppl_app.database.WorkoutDao
import com.example.ppl_app.database.WorkoutDatabase
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class DatabaseReadWriteTest {
    private lateinit var workoutDao: WorkoutDao
    private lateinit var db: WorkoutDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(
            context, WorkoutDatabase::class.java).build()
        workoutDao = db.workoutDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
     fun writeUserAndReadInList() {
        val workout = Workout(1, "workout","description","Pull",3,8,40)
        workoutDao.insert(workout)
        val workoutItem = workoutDao.loadById(1)
        assertThat(workoutItem, equalTo(workout))
    }
}