package com.example.recruiter.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.recruiter.R
import com.example.recruiter.adapter.viewPagerAdapter
import com.example.recruiter.databinding.FragmentViewPagerBinding

class ViewPagerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  = inflater.inflate(R.layout.fragment_view_pager, container, false)
        val viewPager2 = view.findViewById<ViewPager2>(R.id.viewPager2)
        val list : ArrayList<Fragment> = arrayListOf(OnboardingScreenOne(),OnboardingScreenTwo())
        val adapter = viewPagerAdapter(list,requireActivity().supportFragmentManager,lifecycle)
        viewPager2.adapter = adapter
        return view
    }

}