package com.example.demoapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OnboardingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OnboardingFragment : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnNext = view.findViewById<MaterialButton>(R.id.btnNext);
        val txtSignIn = view.findViewById<TextView>(R.id.tvSignIn);

        btnNext.setOnClickListener {
            navigateToLoginScreen();
        }

        txtSignIn.setOnClickListener {
            navigateToLoginScreen();
        }
    }

    fun navigateToLoginScreen() {//ดึงข้อมูลในไฟล์setting_preferences ว่าเก็บตัวแปรskip_onboardingไว้ไหม ถ้าเคยไปให้เพิ่มskip_onboardingในsetting_preferences เพื่อเก็บว่าเคยไปหน้านั้นแล้ว
        val sharedPref  = requireContext().getSharedPreferences("setting_preferences", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putBoolean("skip_onboarding", true)
            apply()
        }
        val navController = findNavController()
        navController.navigate(R.id.action_onboardingFragment_to_loginFragment)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OnboardingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OnboardingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}