package com.example.android.homeowrk

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.replace
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = getPreferences(MODE_PRIVATE) ?: return
        val access = sharedPref.getBoolean(getString(R.string.access), false)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                if (access) add<FlowerListFragment>(R.id.fragment_container)
                else add<SignUpFragment>(R.id.fragment_container)
            }
        }
    }

    fun showDialog(message: String) {
        val args = Bundle()
        args.putString("message", message)

        val dialog = CommonDialogFragment()
        dialog.arguments = args
        dialog.show(supportFragmentManager, "ValidationDialog")
    }

    fun navigateToFlowerList() {
        val sharedPref = getPreferences(MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putBoolean(getString(R.string.access), true)
            apply()
        }

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<FlowerListFragment>(R.id.fragment_container)
        }
    }
}