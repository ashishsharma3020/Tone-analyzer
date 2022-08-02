package com.example.machine

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.machine.Models.Adapter
import com.example.machine.Models.Song
import com.google.gson.Gson

class SongActivity : AppCompatActivity() {
      @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)
       val gson= Gson()
        val songjson= intent.getStringExtra("name")
        val songs=gson.fromJson(songjson,Array<Song>::class.java)
        findViewById<RecyclerView>(R.id.recycler).adapter=Adapter(songs,this)
        findViewById<RecyclerView>(R.id.recycler).layoutManager=LinearLayoutManager(this)
    }
}