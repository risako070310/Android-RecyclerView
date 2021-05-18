package com.risako070310.recyclerview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_course_data_cell.*

class MainActivity : AppCompatActivity() {

    private val courseData = listOf(
        CourseData(R.drawable.android, "Androidアプリプログラミングコース", "いろんなアプリを作って、プログラミングを楽しもう！"),
        CourseData(R.drawable.iphone, "iPhoneアプリプログラミングコース", "世界にひとつだけのiPhoneアプリをつくって、周りを驚かせよう！"),
        CourseData(R.drawable.unity, "Unityゲームプログラミングコース", "世界を驚かすワクワクが止まらない！3Dゲームを制作しよう！")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = RecyclerViewAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.addAll(courseData)
    }
}

data class CourseData (val characterImageResource: Int, val courseName: String, val description: String)

class RecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView!!), LayoutContainer

    private val items: MutableList<CourseData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_course_data_cell, parent, false)
        return ViewHolder(view)
    }

    fun addAll(items: List<CourseData>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.characterImageView.setImageResource(item.characterImageResource)
        holder.courseNameTextView.text = item.courseName
        holder.descriptionTextView.text = item.description
    }

    override fun getItemCount(): Int {
        return items.size
    }

}