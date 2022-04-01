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
import kotlinx.android.synthetic.main.fragment_school_sos.*

class SchoolSosFragment : Fragment() {

    lateinit var school_sos_img : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        school_sos_img = view.findViewById(R.id.school_sos)
        Glide.with(this).load(R.raw.school_sos).override(800,800).into(school_sos)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_school_sos, container, false)
    }
}