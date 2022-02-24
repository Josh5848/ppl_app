package com.example.ppl_app

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RestrictTo
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.test.core.app.ActivityScenario.launch
import com.example.ppl_app.adapters.WorkoutAdapter
import com.example.ppl_app.database.Workout
import com.example.ppl_app.database.WorkoutDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.*

class FragmentPush : Fragment() {
    private lateinit var fab : FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var workoutAdapter: WorkoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        val view = inflater.inflate(R.layout.fragment_push, container, false)
        // setup workout adapater
        workoutAdapter = WorkoutAdapter()
        recyclerView = view.findViewById(R.id.recycler_view_push)


        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        MainScope().async {
            val push_workouts = WorkoutDatabase.invoke(requireContext()).workoutDao().loadAllByType("Push")
            workoutAdapter!!.setData(push_workouts as ArrayList<Workout>)
            recyclerView.adapter = workoutAdapter
            Log.d(TAG, "onCreateView: " + push_workouts.toString())
        }





        fab = view.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Push Workouts", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()

            // Navigate to workouts activity
            view.findNavController().navigate(R.id.fragmentAddWorkout)
        }


        return view


    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FragmentPush().apply {

            }




    }

}