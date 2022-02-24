package com.example.ppl_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.ppl_app.FragmentLegs.Companion.newInstance
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class FragmentWorkouts : Fragment() {
    // TODO: Rename and change types of parameters

    private var tabTitles: Array<String> = arrayOf("Push", "Pull", "Legs")
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        val view = inflater.inflate(R.layout.fragment_workouts, container, false)

        tabLayout = view.findViewById(R.id.tab_layout)!!
        viewPager2 = view.findViewById(R.id.view_pager)!!

        viewPager2.adapter = FragmentAdapter(this)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()


        return view


    }


    class FragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> FragmentPush.newInstance()
                1 -> FragmentPull.newInstance()
                2 -> FragmentLegs.newInstance()
                else -> FragmentPush.newInstance()
            }
        }


    }
}