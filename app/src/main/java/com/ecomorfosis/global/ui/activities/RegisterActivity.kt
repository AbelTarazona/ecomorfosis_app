package com.ecomorfosis.global.ui.activities

import android.os.Bundle
import android.transition.Explode
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ecomorfosis.global.utils.KeyConstants
import com.ecomorfosis.global.*
import com.ecomorfosis.global.databinding.ActivityRegisterBinding
import com.ecomorfosis.global.models.RegisterInformation
import com.ecomorfosis.global.ui.MainActivity
import com.ecomorfosis.global.ui.fragments.CountryFragment
import com.ecomorfosis.global.ui.fragments.UserFragment
import com.ecomorfosis.global.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity(), OnContinueListener {

    private val binding by viewBinding(ActivityRegisterBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(window) {
            enterTransition = Explode()
            exitTransition = Explode()
        }
        setContentView(binding.root)
        statusBarColorNew(R.color.orange_main)

        val intent = this.intent

        if (intent != null) {
            val data = intent.getParcelableExtra<RegisterInformation>(KeyConstants.PUSH_DATA)

            if (data == null) {
                openScreen(StateRegister.USER, null)
            }
        }
    }

    private fun openScreen(screen: StateRegister, data: RegisterInformation?) {

        val fragment: Fragment? = when (screen) {
            StateRegister.USER -> {
                UserFragment.newInstance()
            }
            StateRegister.COUNTRY -> {
                CountryFragment.newInstance()
            }
        }

        if (fragment != null) {
            val bundle = Bundle()
            bundle.putParcelable(KeyConstants.PUSH_DATA, data)
            fragment.arguments = bundle
            replaceFragment(fragment, R.id.container)
        }

    }

    override fun onNext(state: StateRegister, data: RegisterInformation) {
        if (state == StateRegister.USER) {
            openScreen(StateRegister.COUNTRY, data)
        }
        if (state == StateRegister.COUNTRY) {
            launchActivity<MainActivity> {

            }
        }
    }
}