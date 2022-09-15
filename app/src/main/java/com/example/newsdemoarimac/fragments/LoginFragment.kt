package com.example.newsdemoarimac.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.newsdemoarimac.R
import com.example.newsdemoarimac.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.tvRegister.setOnClickListener(this)
        binding.btnSignIn.setOnClickListener(this)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            LoginFragment()

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_register -> {
                // Navigate to sign up fragment from register fragment
                navController.navigate(R.id.action_loginFragment_to_registerFragment)
            }
            R.id.btn_sign_in -> {
                // Navigate to sign up fragment from Dashboard fragment
                navController.navigate(R.id.action_loginFragment_to_detailsFragment)
            }
        }
    }
}