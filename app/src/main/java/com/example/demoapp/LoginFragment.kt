package com.example.demoapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.demoapp.Util.getLoading
import com.google.android.material.button.MaterialButton
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var tvTitle: TextView
    private lateinit var btnAuthenticate: MaterialButton
    private lateinit var tvDescription: TextView
    private lateinit var tvSignInSignUp: TextView
    private var mode: Int = 1 // mode 1 = Login UI, mode 2 = Register UI

    private lateinit var mAuth: FirebaseAuth //class libary ของ firebase
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        //initial Firebase
        mAuth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvTitle = view.findViewById(R.id.tvTitle)
        btnAuthenticate = view.findViewById(R.id.btnAuthenticate)
        tvDescription = view.findViewById(R.id.tvDescription)
        tvSignInSignUp = view.findViewById(R.id.tvSignInSignUp)

        etEmail = view.findViewById(R.id.etEmail) //เชื่อมตัวแปรกับในหน้าlayer (view)
        etPassword = view.findViewById(R.id.etPassword)

        tvSignInSignUp.setOnClickListener { //เมื่อคลิก
            if (mode != 1) mode = 1
            else mode = 2
            switchMode(mode)
        }

        switchMode(mode)
        btnAuthenticate.setOnClickListener {
            var userEmail = etEmail.text.toString().trim { it <= ' ' } //trim คือการตัดspaceหน้าหลังเวลาuserใส่ข้อมูลเข้ามา
            var userPassword = etPassword.text.toString().trim { it <= ' ' }
            //หลังกดปุ่มsign in จะมีหน้าโหลดดิ้งให้ดูรอ เพื่อคุมมจังหวะความเร็วของเน็ต
            var dialog = getLoading()
            dialog.show()

            if (mode == 2) {
                // Process Register
                mAuth!!.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener { task -> //ส่งข้อมูลไปสร้างหรือบันทึกในfirebase
                    dialog.dismiss()
                    if (task.isSuccessful) {
                        // Case Success
                        mAuth.currentUser?.sendEmailVerification()?.addOnCompleteListener{
                            Toast.makeText(activity, "Create account successfully!, Please check your email for verification", Toast.LENGTH_SHORT).show()
                        }
                    } else { //Toast คือ alert
                        Toast.makeText(activity, "Authentication failed ${task.exception?.message}",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                // Process Login
                // Login Mode
                mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener { task ->
                        dialog.dismiss()
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(activity, "signInWithEmail:success", Toast.LENGTH_SHORT).show()
                            navigateToMainAppScreen();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TEST", "signInWithEmail:failure", task.exception)
                            Toast.makeText(activity, "Authentication Failed: " + task.exception!!.message, Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
    fun navigateToMainAppScreen() {
        val navController = findNavController()
        navController.navigate(R.id.action_loginFragment_to_mainAppFragment)
    }

    fun switchMode(currentMode: Int) {
        if (currentMode == 1) {
            // Render Login UI
            tvTitle.text = "Login with"
            btnAuthenticate.text = "Sign In"
            tvDescription.text = "Don't have account yet?"
            tvSignInSignUp.text = "Sign Up"
        } else {
            // Render Register UI
            tvTitle.text = "New Account"
            btnAuthenticate.text = "Sign Up"
            tvDescription.text = "Already have account?"
            tvSignInSignUp.text = "Sign In"
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}