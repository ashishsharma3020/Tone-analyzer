package com.example.machine.Models

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.machine.R
import com.squareup.picasso.Picasso

class Adapter(val songs:Array<Song>,val context:Context): RecyclerView.Adapter<Adapter.viewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
       val view= LayoutInflater.from(context).inflate(R.layout.samplesong,parent,false)
        return viewholder(view)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        Picasso.get().load(songs[position].imageurl).placeholder(R.drawable.ic_launcher_foreground).into(holder.image)
         holder.name.text=songs[position].name
        holder.artist.text=songs[position].artist
        val intent=Intent(Intent.ACTION_VIEW)
        holder.play.setOnClickListener{
            intent.data= Uri.parse(songs[position].songurl)
            context.startActivity(intent)
        }
        holder.artist.setOnClickListener{
            intent.data= Uri.parse(songs[position].artisturl)
            context.startActivity(intent)
        }
        }

    override fun getItemCount(): Int {
        return songs.size
    }
    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView){

        lateinit var image:ImageView
        lateinit var name:TextView
        lateinit var artist: TextView
        lateinit var play:ImageView
        init {
            image=itemView.findViewById(R.id.imageView2)
            name=itemView.findViewById(R.id.textView)
            artist=itemView.findViewById(R.id.textView2)
            play=itemView.findViewById(R.id.imageView3)
        }
    }

}