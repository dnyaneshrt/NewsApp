package com.tech.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//step 1 :create your own class as a child of RecyclerView.Adapter.
class MyRecyclerViewAdaper(var listnews: ArrayList<News>, var listner:newsItemClicked) :
    RecyclerView.Adapter<MyRecyclerViewAdaper.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        var layoutInflater = LayoutInflater.from(parent.context)
        var view = layoutInflater.inflate(R.layout.dummy_view, parent, false)
        var holder = MyViewHolder(view)
        view.setOnClickListener {
//            Toast.makeText(parent.context,"news clicked",Toast.LENGTH_SHORT).show()
            listner.onItemClicked(""+holder.adapterPosition)
        }

        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item = listnews[position]
        holder.title.text =item.title
        holder.desc.text=item.description


    }

    override fun getItemCount(): Int {
        return listnews.size
    }


    //step 2: create a  ViewHolder class
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.findViewById<TextView>(R.id.txt_title)
        var desc = itemView.findViewById<TextView>(R.id.txt_description)
        var image_view= itemView.findViewById<ImageView>(R.id.imageView)
    }
}

interface newsItemClicked
{
  fun onItemClicked(item: String)
}
