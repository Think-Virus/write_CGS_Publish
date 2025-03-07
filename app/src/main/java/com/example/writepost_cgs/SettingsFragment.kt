package com.example.writepost_cgs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView

class SettingsFragment: Fragment() {
    var firstTrigger = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        firstTrigger = true
    }

    override fun onResume() {
        super.onResume()

        if(firstTrigger) {
            val thisActivity = activity as MainActivity

            val fragmentManager = thisActivity.supportFragmentManager
            val editUserInfoFragment = EditUserInfoFragment()

            val transaction = fragmentManager.beginTransaction()
            val bundle = Bundle()
            bundle.putString("name", "settings")
            editUserInfoFragment.arguments = bundle
            transaction.replace(R.id.fragment_container, editUserInfoFragment)
            transaction.commit()

            firstTrigger = false
        }
    }
}