package com.example.ppl_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentLegs.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentLegs : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var fab : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        val view = inflater.inflate(R.layout.fragment_legs, container, false)
        fab = view.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Leg Workouts", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()

            // Navigate to workouts activity
            view.findNavController().navigate(R.id.fragmentAddWorkout)
        }


        return view


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentLegs.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            FragmentLegs().apply {

            }
        }
    }
