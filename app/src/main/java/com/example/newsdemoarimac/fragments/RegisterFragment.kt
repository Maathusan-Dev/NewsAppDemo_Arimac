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
import com.example.newsdemoarimac.databinding.FragmentRegisterBinding

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment(),View.OnClickListener {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.tvSignIn.setOnClickListener(this)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RegisterFragment()

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_register -> {

            }
            R.id.tv_sign_in -> {
                // Navigate to sign up fragment from register fragment
                navController.popBackStack()
            }
        }
    }
}