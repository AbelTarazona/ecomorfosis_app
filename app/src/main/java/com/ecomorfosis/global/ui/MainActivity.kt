package com.ecomorfosis.global.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.ecomorfosis.global.R
import com.ecomorfosis.global.databinding.ActivityMainBinding
import com.ecomorfosis.global.mock.getModulesHome
import com.ecomorfosis.global.repository.EcomorfosisPreferences
import com.ecomorfosis.global.ui.activities.EntryActivity
import com.ecomorfosis.global.ui.activities.GameHomeActivity
import com.ecomorfosis.global.ui.adapters.ModulesMainAdapter
import com.ecomorfosis.global.utils.KeyConstants
import com.ecomorfosis.global.utils.launchActivity
import com.ecomorfosis.global.utils.statusBarColorNew
import com.ecomorfosis.global.utils.viewBinding

class MainActivity : AppCompatActivity(), ModulesMainAdapter.Callback {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val list = getModulesHome()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        statusBarColorNew(R.color.colorPrimary)

        val pref = EcomorfosisPreferences(this)

        pref.sessionSaved.asLiveData().observe(this) { logged ->
            if (logged != null) {
                if (!logged) {
                    launchActivity<EntryActivity>() {
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    }
                    finish()
                } else {
                    showData(pref)
                }
            }
        }
    }

    private fun showData(pref: EcomorfosisPreferences) {
        pref.userSaved.asLiveData().observe(this) { user ->
            if (user != null) {
                val msg = "¡Hola ${user.name}!, escoge a qué módulo ingresar"
                binding.textView12.text = msg
            }
        }

        initComponents(binding)
    }

    private fun initComponents(binding: ActivityMainBinding) {

        list.sortedBy {
            it.order
        }
        val adapter = ModulesMainAdapter(list, this, this)
        binding.rvMain.adapter = adapter

    }

    override fun onModuleClick(id: Int) {
        when (id) {
            KeyConstants.HomeModules.GUIA -> {

            }
            KeyConstants.HomeModules.GAME -> {
                startActivity(Intent(this, GameHomeActivity::class.java))
            }
            KeyConstants.HomeModules.MATERIALES -> {

            }
        }
    }
}