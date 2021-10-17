package com.tech.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity(), newsItemClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        my_recyler_view.layoutManager = LinearLayoutManager(this)
        var my_adapter = MyRecyclerViewAdaper(getNews(), this)// data sent
        my_recyler_view.adapter = my_adapter
    }

/*
   fun getData():ArrayList<String>{
        var list= ArrayList<String>()
        for(i in 0 until 100)
        {
            list.add("news $i")
        }
        return list
    }


*/

    fun getNews(): ArrayList<News> {

// Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url =
            "https://newsapi.org/v2/top-headlines?country=us&apiKey=cb8d5f5d04e641e09cb3b86f13340057"

// Request a string response from the provided URL.

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->

                Log.d("response from news api", response.toString())

                var jsonNewsArray=response.getJSONArray("articles")

                for(i in 0 until jsonNewsArray.length())
                {
                   var jsonObject= response.getJSONObject(i.toString())

//                  var news:ArrayList<News>= News(
//                        jsonObject.getString("title"),
//                        jsonObject.getString("description"),
//                        jsonObject.getString("url"),
//                        jsonObject.getString("urlToImage")
//                    )

                }

            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "failed to get response", Toast.LENGTH_SHORT)
            }
        )
// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)

        return
    }


    override fun onItemClicked(item: String) {
        Toast.makeText(this, "news $item", Toast.LENGTH_SHORT).show()
    }
}