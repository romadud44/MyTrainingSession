package com.example.mytrainingsession

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ListAdapter(context: Context, trainingList: MutableList<Exercise>) :
    ArrayAdapter<Exercise>(context, R.layout.list_item, trainingList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val training = getItem(position)
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }
        val imageViewIV = view?.findViewById<ImageView>(R.id.imageViewTV)
        val trainingTV = view?.findViewById<TextView>(R.id.trainingNameTV)


        training?.gifImage?.let { imageViewIV?.setImageResource(it) }
        trainingTV?.text = training?.name



        return view!!


    }
}