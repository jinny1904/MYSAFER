package com.example.mysafer

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysafer.databinding.ActivityListBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User

class ListActivity : AppCompatActivity() {
    lateinit var binding : ActivityListBinding
    var array : MutableList<UserDTO> = arrayListOf() //친구정보가 담긴 배열
    var uids : MutableList<String> = arrayListOf()  //친구의 uid가 담긴
    val myUid = FirebaseAuth.getInstance().uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)

        FirebaseFirestore.getInstance().collection("users").get().addOnCompleteListener {
                task ->
            array.clear()
            uids.clear()    //데이터 초기화
            for (item in task.result!!.documents){
                if(myUid != item.id){
                    array.add(item.toObject(UserDTO::class.java)!!)
                    uids.add(item.id)}
            }
            binding.peopleListRecyclerview.adapter?.notifyDataSetChanged()
        }
        binding.peopleListRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.peopleListRecyclerview.adapter = RecyclerviewAdapter()
        watchingMyUidVideoRequest()
    }

    fun watchingMyUidVideoRequest(){

        //snapshot을 이용해서 실시간으로 database감시
        FirebaseFirestore.getInstance().collection("users").document(myUid!!).addSnapshotListener{ value, error->
            var userDTO = value?.toObject(UserDTO::class.java)
            if(userDTO?.channel != null){
                showJoinDialog(userDTO.channel!!)
            }
        }
    }

    fun showJoinDialog(channel: String){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("${channel}방에 참여하시겠습니까?")
        builder.setPositiveButton("Yes"){
                dialogInterface, i ->
            openVideoActivity(channel)
            removeChannelStr()
        }
        builder.setNegativeButton("No"){dialogInterface, i ->
            dialogInterface.dismiss()
        }
        builder.create().show()
    }

    fun removeChannelStr(){
        var map = mutableMapOf<String,Any>()
        map["channel"]  = FieldValue.delete()
        FirebaseFirestore.getInstance().collection("users").document(myUid!!).update(map)
    }

    inner class RecyclerviewAdapter : RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerviewAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person,parent,false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerviewAdapter.ViewHolder, position: Int) {
            holder.itemEmail.text = array[position].email
            holder.itemView.setOnClickListener {
                val channelNumber = (1000..1000000).random().toString()
                openVideoActivity(channelNumber)
                createVideoChatRoom(position, channelNumber)
            }
        }

        override fun getItemCount(): Int {
            return array.size
        }

        inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
            val itemEmail = view.findViewById<TextView>(R.id.item_email)
        }
    }
    //VideoActivity에서의 channelId
    fun openVideoActivity(channelId : String){
        val i = Intent(this, VideoActivity::class.java)
        i.putExtra("channelId", channelId)
        startActivity(i)
    }

    //user클릭시 channelId를 생성하는 함수
    fun createVideoChatRoom(position: Int, channel:String){
        var map = mutableMapOf<String,Any>() //소통하고 싶은 user의 db에 업데이트 할 것이므로 map사용
        map["channel"] = channel  //channelId는 파라미터에서 받아옴
        FirebaseFirestore.getInstance().collection("users").document(uids[position]).update(map)
    }
}
