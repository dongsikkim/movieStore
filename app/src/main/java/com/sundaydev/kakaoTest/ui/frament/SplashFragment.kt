package com.sundaydev.kakaoTest.ui.frament

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.sundaydev.kakaoTest.R
import com.sundaydev.kakaoTest.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentSplashBinding = FragmentSplashBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navOptions = NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build()
        Handler().postDelayed({ findNavController().navigate(R.id.action_splashFragment_to_movieFragment, null, navOptions) }, 1000)
    }
}