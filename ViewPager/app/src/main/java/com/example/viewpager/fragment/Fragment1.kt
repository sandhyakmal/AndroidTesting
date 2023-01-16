package com.example.viewpager.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.viewpager.LoginActivity
import com.example.viewpager.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_1.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var firebaseAuth: FirebaseAuth

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
        val view = inflater.inflate(R.layout.fragment_1, container, false)

        FirebaseApp.initializeApp(requireContext())
        firebaseAuth = FirebaseAuth.getInstance()


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("201616624596-7vsfrk5bsq9ao9vi23jtunhe2399uum9.apps.googleusercontent.com")
            .requestEmail()
            .build()

        val mGooleSignInClient = GoogleSignIn.getClient(requireContext(),gso)

        view.btnLogout.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"Logout",Toast.LENGTH_LONG).show()

            mGooleSignInClient.signOut().addOnCompleteListener {
                firebaseAuth.currentUser?.delete()
                firebaseAuth.signOut()

                startActivity(Intent(context, LoginActivity::class.java))
                activity?.finish()
            }
        })

        //ambil data username email dari google
        val acct = GoogleSignIn.getLastSignedInAccount(requireActivity())
        view.textView3.setText(acct?.displayName)
        view.textView4.setText(acct?.email)

        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }
}