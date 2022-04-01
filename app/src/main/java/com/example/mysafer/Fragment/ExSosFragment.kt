package com.example.mysafer.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.mysafer.R
import kotlinx.android.synthetic.main.fragment_ex_sos.*
import kotlinx.android.synthetic.main.fragment_sex_sos.*

class ExSosFragment : Fragment() {

    lateinit var ex_sos_img : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ex_sos_img = view.findViewById(R.id.ex_sos)
        Glide.with(this).load(R.raw.ex_sos).override(800,800).into(ex_sos)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ex_sos, container, false)
    }

}