package com.example.fragmenttutorial

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_registration.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegistrationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistrationFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegistrationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegistrationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnRegister.setOnClickListener(View.OnClickListener {
            if (!txtRegistPass.text.toString().contentEquals(txtRegistPass2.text.toString())){
                Toast.makeText(activity, "Password tidak Sama", Toast.LENGTH_SHORT).show()
            } else if (txtRegistUsername.text.toString().equals("") && txtRegistPass.text.toString().equals("") && txtRegistPass2.text.toString().equals("")  ){
                Toast.makeText(activity, "Data Harus diisi", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "Berhasil buat Akun", Toast.LENGTH_SHORT).show()
                (activity as MainActivity).loadFragment(LoginFragment.newInstance(txtRegistUsername.text.toString(), txtRegistPass2.text.toString()))

                //untuk shared preferences(menyimpan username/password)
                val sp = activity?.getSharedPreferences("USER_CREDENTIAL", Context.MODE_PRIVATE)
                val editor = sp?.edit()
                editor?.putString("username", txtRegistUsername.text.toString())
                editor?.commit()

            }
        })
    }
}