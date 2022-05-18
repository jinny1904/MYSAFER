package com.example.mysafer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.example.mysafer.databinding.ActivityVideoBinding
import com.remotemonster.sdk.RemonCall

class VideoActivity : AppCompatActivity() {
    lateinit var binding : ActivityVideoBinding
    var remonCall : RemonCall? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_video)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)  //영상통화동안 화면 안꺼지게

        remonCall = RemonCall.builder()  //remonCall 클래스 생성
            .context(this)
            .serviceId("21ed6fb5-2c8a-4b01-874f-681e1a33ebd4")
            .key("a0151bae3ff0b1cb94d24a470dea724946472df492da31efd81017e8c2dbb39b")
            .videoCodec("VP8")
            .videoWidth(640)
            .videoHeight(480)
            .localView(binding.localView)
            .remoteView(binding.remoteView)
            .build()

        val channelId = intent.getStringExtra("channelId")  //채널Id 받아오는 변수 생성
        remonCall?.connect(channelId)
        //상대방이 영상통화 종료시 내 activity 종료
        remonCall?.onClose{
            finish()
        }
    }

    //activity종료시 영상통화 종료
    override fun onDestroy() {
        remonCall?.close()
        super.onDestroy()

    }
}