package kotlindemo.meetup.com.meetup

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_view_holder.view.*

class MainActivity : AppCompatActivity() {

    var adapter: Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonIncrement.setOnClickListener {
            increment()
            increment()
        }

        buttonDecrement.setOnClickListener {
            decrement()
        }
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = Adapter()
        recyclerView.adapter = adapter

    }

    private fun decrement() {
        adapter?.size = (adapter?.size ?: 0) - 1
        adapter?.notifyDataSetChanged()
    }

    private fun increment() {
        adapter?.let {
            it.size += 1
            it.notifyDataSetChanged()
        }

    }
}

class Adapter(var size: Int = 0) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater
                .from(p0.context)
                .inflate(R.layout.layout_view_holder,
                        p0,
                        false))
    }

    override fun getItemCount(): Int = size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.render(p1)
    }

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun render(p1: Int) {
        itemView.textView.text = "$p1"
    }

}
