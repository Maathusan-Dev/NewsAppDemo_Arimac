package com.example.newsdemoarimac.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.newsdemoarimac.R
import com.example.newsdemoarimac.databinding.FragmentLoginBinding
import com.example.newsdemoarimac.models.User
import com.example.newsdemoarimac.util.Constants
import com.example.newsdemoarimac.viewModels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentLoginBinding
    private val userViewModel: UserViewModel by viewModels()

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

    private fun login() {
        val userName = binding.etUserName.text.toString()
        val password = binding.etPassword.text.toString()

        if (userName == "") {
            Toast.makeText(context, "Enter Username", Toast.LENGTH_LONG).show()
        } else if (password == "") {
            Toast.makeText(context, "Enter Password", Toast.LENGTH_LONG).show()
        } else {
            userViewModel.getUser(userName).observe(viewLifecycleOwner) {
                if (it == null) {
                    Toast.makeText(context, "User not found", Toast.LENGTH_LONG).show()
                } else if (it.password != password) {
                    Toast.makeText(context, "Password not match", Toast.LENGTH_LONG).show()
                } else {

                    val bundle = Bundle()
                    bundle.putString(Constants.USERNAME, it.username)
                    // Navigate to sign up fragment from Dashboard fragment

                    navController.navigate(R.id.action_loginFragment_to_dashboardFragment, bundle)
                }
            }
        }


    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_register -> {
                // Navigate to sign up fragment from register fragment
                navController.navigate(R.id.action_loginFragment_to_registerFragment)
            }
            R.id.btn_sign_in -> {
                login()
            }
        }
    }
}