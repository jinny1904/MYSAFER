package com.example.mysafer.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.mysafer.R
import kotlinx.android.synthetic.main.fragment_home_sos.*


class HomeSosFragment : Fragment() {

    lateinit var home_sos_img :ImageView

   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        home_sos_img = view.findViewById(R.id.home_sos)
        Glide.with(this).load(R.raw.home_sos).override(800,800).into(home_sos)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_sos, container, false)

        return view

    }
}