package com.ecomorfosis.global.ui.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.preferences.core.Preferences
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ecomorfosis.global.databinding.FragmentCountryBinding
import com.ecomorfosis.global.mock.getCountries
import com.ecomorfosis.global.models.Country
import com.ecomorfosis.global.models.RegisterInformation
import com.ecomorfosis.global.repository.EcomorfosisPreferences
import com.ecomorfosis.global.retrofit.User
import com.ecomorfosis.global.retrofit.UserRequest
import com.ecomorfosis.global.ui.MainActivity
import com.ecomorfosis.global.ui.adapters.CountryAdapter
import com.ecomorfosis.global.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CountryFragment : Fragment(), CountryAdapter.Callback {

    private lateinit var binding: FragmentCountryBinding

    private val viewModel: CountryViewModel by viewModels()

    private lateinit var onNextListener: OnContinueListener

    private lateinit var data: RegisterInformation

    private val list = getCountries()

    var countrySelected: String = ""

    private lateinit var user: User

    private var preferences: EcomorfosisPreferences? = null

    companion object {
        @JvmStatic
        fun newInstance() = CountryFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activity = context as Activity
        try {
            onNextListener = activity as OnContinueListener
        } catch (c: ClassCastException) {
            throw ClassCastException("$activity must override")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            data = requireArguments().getParcelable(KeyConstants.PUSH_DATA)!!
            val header = "${data.name}, ¿De qué país eres?"
            binding.textView.text = header
        }

        preferences = EcomorfosisPreferences(requireContext())

        subscriberObservers()

        binding.button2.setOnClickListener {
            if (countrySelected.isNotEmpty()) {
                val request = UserRequest(
                    country = countrySelected,
                    name = data.name,
                    birth = data.birth,
                    email = data.mail
                )

                user = User(
                    country = countrySelected,
                    name = data.name,
                    birth = data.birth,
                    email = data.mail
                )

                viewModel.setStateEvent(MainStateEvent.CreateUser, request)
            }
        }

        val adapter = CountryAdapter(list, this, requireContext())
        binding.rvCountry.adapter = adapter
    }

    private fun subscriberObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Success<String> -> {
                    if (preferences != null) {
                        GlobalScope.launch {
                            viewModel.setUserPref(user, preferences!!)
                            context?.launchActivity<MainActivity>() {
                                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_SINGLE_TOP)
                            }
                            activity?.finish()
                        }

                    }
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(it.exception.message)
                }
                is DataState.ErrorMessage -> {
                    displayProgressBar(false)
                    displayError(it.msg)
                }
                DataState.Loading -> displayProgressBar(true)
            }
        })
    }

    override fun onCountrySelected(data: Country) {
        countrySelected = data.title
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        binding.progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun displayError(message: String?) {
        if (message != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Unknown", Toast.LENGTH_SHORT).show()
        }
    }

}