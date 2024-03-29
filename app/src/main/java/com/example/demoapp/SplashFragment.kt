package com.example.demoapp

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    fun navigateToOnboardingScreen() {
        val navController = findNavController()
        navController.navigate(R.id.action_splashFragment_to_onboardingFragment)
    }

    fun navigateToLoginScreen() {
        val navController = findNavController()
        navController.navigate(R.id.action_splashFragment_to_loginFragment)
    }

    fun waitDelayAndNavigateToNextScreen() {
        //กำหนดให้เมื่อเปิดแอพแล้วจะเลือกว่าจะไปหน้า Onbording หรือ Login ถ้าเคยไปหน้าOnbording จะเข้าไปหน้า login เลย
        Handler(Looper.getMainLooper()).postDelayed({
            val sharedPref  = requireContext().getSharedPreferences("setting_preferences", Context.MODE_PRIVATE)
            // get saved skip setting from local database Default: False if not exist
            var isSkip = sharedPref.getBoolean("skip_onboarding", false)
            if (isSkip) {//ถ้ามีการเก็บskip_onboardingไว้จะไปหน้าlogin
                navigateToLoginScreen()
            } else {//ถ้าไม่เก็บskip_onboardingจะไปหน้าOnboarding
                navigateToOnboardingScreen()
            }
        }, 3000) // 3000 milliseconds delay (3 seconds)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        waitDelayAndNavigateToNextScreen();//สั่งให้หน่วง3วิ
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment //การลิงค์ไปที่ fragment_splash.xml
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SplashFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SplashFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}