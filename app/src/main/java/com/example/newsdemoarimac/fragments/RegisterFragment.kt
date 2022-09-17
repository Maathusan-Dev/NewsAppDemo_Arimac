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
import com.example.newsdemoarimac.databinding.FragmentRegisterBinding
import com.example.newsdemoarimac.models.User
import com.example.newsdemoarimac.viewModels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment(),View.OnClickListener {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentRegisterBinding
    private val userViewModel:UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.tvSignIn.setOnClickListener(this)
        binding.btnRegister.setOnClickListener(this)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RegisterFragment()

    }

    fun getInputs(){
        val userName = binding.etUserName.text.toString()
        val password = binding.etPassword.text.toString()
        val confirm = binding.etConfirmPassword.text.toString()

        if (userName == ""){
            Toast.makeText(context,"Enter Username",Toast.LENGTH_LONG).show()
        }else if (password == ""){
            Toast.makeText(context,"Enter Password",Toast.LENGTH_LONG).show()
        }else if (confirm == ""){
            Toast.makeText(context,"Enter confirm password",Toast.LENGTH_LONG).show()
        }else if (confirm != password) {
            Toast.makeText(context,"Password not match",Toast.LENGTH_LONG).show()
        }else{
            val newUser = User(0,userName,password,false)
            userViewModel.insertUser(newUser)
            Toast.makeText(context,"User Created",Toast.LENGTH_LONG).show().also {
                navController.popBackStack()
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_register -> {
                getInputs()
            }
            R.id.tv_sign_in -> {
                // Navigate to sign up fragment from register fragment
                navController.popBackStack()
            }
        }
    }
}