package com.krsolutions.yetanotherlauncher

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krsolutions.yetanotherlauncher.databinding.ItemAppBinding

class Adapter(
    val context:Context
) : RecyclerView.Adapter<Adapter.AppItemViewHolder>() {

    lateinit var appBinding: ItemAppBinding
    var appList: List<AppBlock>?= null

    inner class AppItemViewHolder(
        val appBinding: ItemAppBinding
    ): RecyclerView.ViewHolder(appBinding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        appBinding = ItemAppBinding.inflate(inflater, parent, false)
        return AppItemViewHolder(appBinding)
    }

    override fun getItemCount(): Int {
        return appList?.size?:0
    }

    override fun onBindViewHolder(holder: AppItemViewHolder, position: Int) {
        holder.appBinding.appIcon.setImageDrawable(appList?.get(position)?.icon)
        holder.appBinding.appName.text = appList?.get(position)?.appName
        holder.appBinding.root.setOnClickListener {
            context.startActivity(
                context.packageManager.getLaunchIntentForPackage(appList?.get(position)?.packageName?:"com.krsolutions.yetanotherlauncher")
            )
        }
    }

    fun passAppList(
        appsList: List<AppBlock>
    ){
        appList = appsList
        notifyDataSetChanged()
    }

}

