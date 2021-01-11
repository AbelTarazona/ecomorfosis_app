package com.ecomorfosis.global.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ecomorfosis.global.*
import com.ecomorfosis.global.databinding.ActivityEntryBinding
import com.ecomorfosis.global.utils.launchActivity
import com.ecomorfosis.global.utils.statusBarColorNew
import com.ecomorfosis.global.utils.viewBinding


class EntryActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityEntryBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        statusBarColorNew(R.color.colorPrimary)
        binding.button.setOnClickListener {
            launchActivity<RegisterActivity> { }
        }
    }
}