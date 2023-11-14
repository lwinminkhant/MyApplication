package com.lmkhant.android.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InputAdapter(private val data: MutableList<Input>) :
    RecyclerView.Adapter<InputAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val checked: CheckBox

        init {
            textView = view.findViewById(R.id.tv_text)
            checked = view.findViewById(R.id.ck_text)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.input_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = (data[position]).input
        viewHolder.checked.isChecked = (data[position]).checked

        viewHolder.checked.setOnClickListener { v ->
            data[position].checked = (v as CheckBox).isChecked
            //itemListener.onItemChecked(data[position])
        }
    }

    fun updateList(newData: MutableList<Input>){
        this.data.clear()
        this.data.addAll(newData)
    }
    override fun getItemCount() = data.size
}
