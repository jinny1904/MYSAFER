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
import kotlinx.android.synthetic.main.fragment_sex_sos.*

class SexSosFragment : Fragment() {

    lateinit var sex_sos_img : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sex_sos_img = view.findViewById(R.id.sex_sos)
        Glide.with(this).load(R.raw.sex_sos).override(700,700).into(sex_sos)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sex_sos, container, false)
    }


}