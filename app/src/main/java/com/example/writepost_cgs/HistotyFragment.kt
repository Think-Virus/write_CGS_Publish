package com.example.writepost_cgs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager


class HistotyFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    var firesTriger:Boolean = true
    var nowPostAmount = PostAmount.postAmount

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frament_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
    }

    override fun onResume() {
        super.onResume()
        if(nowPostAmount != PostAmount.postAmount || firesTriger){
            val db = Room.databaseBuilder(
                activity as MainActivity,
                AppDatabases::class.java,
                "post_history_db"
            ).allowMainThreadQueries().build()

            val glide = Glide.with(this)
            val adapter = RecyclerViewAdapter(
                db.PostHistoryDao().getAll(),
                LayoutInflater.from(activity as MainActivity),
                glide
            )

            recyclerView.adapter = adapter
            firesTriger = false
            nowPostAmount = PostAmount.postAmount
        }
    }
}

class RecyclerViewAdapter(
    val itemList:List<PostHistory>?,
    val inflater: LayoutInflater,
    val glide: RequestManager
):RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val title:TextView
        val content:TextView
        val image:ImageView

        init {
            title = itemView.findViewById(R.id.title)
            content = itemView.findViewById(R.id.content)
            image = itemView.findViewById(R.id.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_post_history,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = itemList?.get(position)?.title
        holder.content.text = itemList?.get(position)?.content
        holder.content.text = itemList?.get(position)?.content
        glide.load(itemList?.get(position)?.image).centerCrop().into(holder.image)
    }

    override fun getItemCount(): Int {
        return itemList?.size ?: 0
    }
}