package com.example.ppl_app
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.test.core.app.ActivityScenario.launch
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.example.ppl_app.database.WorkoutDatabase
import com.example.ppl_app.database.Workout

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FragmentAddWorkout : Fragment() {
    // Form com.example.ppl_app.database variables
    private lateinit var titleInput : TextInputEditText
    private lateinit var titleInputLayout: TextInputLayout

    private lateinit var descriptionInput : TextInputEditText
    private lateinit var descriptionInputLayout : TextInputLayout

    private lateinit var typeInput : AutoCompleteTextView
    private lateinit var typeInputLayout: TextInputLayout

    private lateinit var setsInput : TextInputEditText
    private lateinit var setsInputLayout : TextInputLayout

    private lateinit var repsInput : TextInputEditText
    private lateinit var repsInputLayout : TextInputLayout

    private lateinit var weightInput : TextInputEditText
    private lateinit var weightInputLayout : TextInputLayout

    // Binding for text input edit text


    private val db = context?.let { WorkoutDatabase(it) }

    private val workoutDao = db?.workoutDao()

    private lateinit var newWorkout : Workout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This callback will only be called when MyFragment is at least Started.
        setHasOptionsMenu(true)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                    view?.findNavController()?.navigateUp()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_workout, container, false)
        typeInput = view.findViewById(R.id.workoutTypeInput)
        typeInputLayout = view.findViewById(R.id.typeInputLayout)

        titleInput = view.findViewById(R.id.workoutTitleInput)
        titleInputLayout = view.findViewById(R.id.titleInputLayout)

        descriptionInput = view.findViewById(R.id.workoutDescriptionInput)
        descriptionInputLayout = view.findViewById(R.id.descriptionInputLayout)

        setsInput    = view.findViewById(R.id.workoutSetsInput)
        setsInputLayout = view.findViewById(R.id.setsInputLayout)

        repsInput = view.findViewById(R.id.workoutRepsInput)
        repsInputLayout = view.findViewById(R.id.repsInputLayout)

        weightInput = view.findViewById(R.id.workoutWeightInput)
        weightInputLayout = view.findViewById(R.id.weightInputLayout)

        val stringArray = resources.getStringArray(R.array.ppl_strings)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,stringArray)
        typeInput.setAdapter(arrayAdapter)

        newWorkout = Workout(0,"","","",1,1,1)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.save_menu_button -> if(isValidate()){

                Log.d(TAG, "onOptionsItemSelected: Saved!")
                view?.let {
                    Snackbar.make(it, "Workout Saved", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show()
                    // Create object to save to com.example.ppl_app.database

                    newWorkout.workoutTitle = titleInput.text?.trim().toString();
                    newWorkout.workoutDescription = descriptionInput.text?.trim().toString()
                    newWorkout.workoutType = typeInput.text?.trim().toString()

                    newWorkout.workoutSets = Integer.parseInt(setsInput.text?.trim().toString())
                    newWorkout.workoutReps = Integer.parseInt(repsInput.text?.trim().toString())
                    newWorkout.workoutWeight = Integer.parseInt(weightInput.text?.trim().toString())
                    Log.d(TAG, "onOptionsItemSelected: " + newWorkout.workoutType.toString())

                    GlobalScope.launch {
                        WorkoutDatabase.invoke(requireContext()).workoutDao().insert(newWorkout)
                    }
                    Log.d(TAG, "onOptionsItemSelected: Workout Insert!")
                    findNavController().navigate(R.id.fragmentWorkouts);
                }
            }
            // Todo Add data to com.example.ppl_app.database for later use
            //Must build Entity object for Workout then save Workout object to com.example.ppl_app.database
            // However before i can save i must validate the user input to ensure all fields have input
            // Except Description field



        }

        return super.onOptionsItemSelected(item)
    }


    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentAddWorkout().apply {

            }
    }


    /**
     * applying text watcher on each text field
     */


    private fun validateTitle(): Boolean {
        if (titleInput.text.toString().trim().isEmpty()) {
            titleInputLayout.error = "Required Field!"
            titleInput.requestFocus()
            return false
        } else {
            titleInputLayout.isErrorEnabled = false
        }
        return true
    }
    private fun validateType(): Boolean {
        if (typeInput.text.toString().trim().isEmpty()) {
            typeInputLayout.error = "Required Field!"
            typeInput.requestFocus()
            return false
        } else {
            typeInputLayout.isErrorEnabled = false
        }
        return true
    }

    private fun validateReps(): Boolean {
        if (repsInput.text.toString().trim().isEmpty()) {
            repsInputLayout.error = "Must specify rep amount"
            repsInput.requestFocus()
            return false

        } else if (Integer.parseInt(repsInput.text.toString().trim()) <= 0) {
            repsInputLayout.error = "Reps must be greater than 0"
            repsInput.requestFocus()
            return false

        }else {
            repsInputLayout.isErrorEnabled = false
        }
        return true
    }
    private fun validateSets(): Boolean {
        if (setsInput.text.toString().trim().isEmpty()) {
            setsInputLayout.error = "Must specify set amount"
            setsInput.requestFocus()
            return false
        } else if (Integer.parseInt(setsInput.text.toString().trim()) <= 0) {
            setsInputLayout.error = "Sets must be greater than 0"
            setsInput.requestFocus()
            return false

        }else {
            setsInputLayout.isErrorEnabled = false
        }
        return true
    }

    private fun validateWeight(): Boolean {
        if (weightInput.text.toString().trim().isEmpty()) {
            weightInputLayout.error = "Must specify weight"
            weightInput.requestFocus()
            return false
        } else if (Integer.parseInt(weightInput.text.toString().trim()) <= 0) {
            weightInputLayout.error = "Weight must be greater than 0"
            weightInput.requestFocus()
            return false

        }else {
            weightInputLayout.isErrorEnabled = false
        }
        return true
    }


    fun isValidate(): Boolean = validateTitle() && validateType() && validateSets() && validateReps() && validateWeight()
}


