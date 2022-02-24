package com.example.ppl_app.adapters



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ppl_app.R
import com.example.ppl_app.database.Workout
import org.w3c.dom.Text

class WorkoutAdapter() : RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>(){

    var dataSet = ArrayList<Workout>()


    class WorkoutViewHolder(view:View): RecyclerView.ViewHolder(view) {
        var workoutTitle: TextView = view.findViewById(R.id.workoutTitle)
        var workoutReps: TextView = view.findViewById(R.id.workoutReps)
        var workoutSets: TextView = view.findViewById(R.id.workoutSets)

    }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.workout_item, parent, false)

        return WorkoutViewHolder(view)

    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        holder.workoutTitle.text = dataSet[position].workoutTitle
        holder.workoutReps.text = dataSet[position].workoutReps.toString()
        holder.workoutSets.text = dataSet[position].workoutSets.toString()

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setData(workouts : ArrayList<Workout>){
        dataSet = workouts
    }


    }









