package com.af.ebtikartaskaf.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.af.ebtikartaskaf.R
import com.af.ebtikartaskaf.databinding.HomePopularPersonAdapterBinding
import com.af.ebtikartaskaf.interfaces.ItemClickInterface
import com.af.ebtikartaskaf.model.dto.PersonDto
import com.af.ebtikartaskaf.utils.HelperMethods
import java.util.*
/*
 Created By Dev Asem Alfaqeh 28/12/2020
 */

class HomePopularPersonAdapter(private val personDtos: ArrayList<PersonDto>?,
                               private val personDtoItemClickInterface: ItemClickInterface<PersonDto>)
    : RecyclerView.Adapter<HomePopularPersonAdapter.ViewHolder>() {

    private var context: Context? = null
    private lateinit var homePopularPersonAdapterBinding: HomePopularPersonAdapterBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        homePopularPersonAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.home_popular_person_adapter,
                parent, false)
        return ViewHolder(homePopularPersonAdapterBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val personDto = personDtos?.get(position)
        personDto?.let { holder.bindData(it, context) }
        homePopularPersonAdapterBinding.cvInfo.setOnClickListener {
            if (personDto != null) {
                personDtoItemClickInterface.onItemClickListener(personDto, position)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return personDtos?.size!!
    }

    class ViewHolder(private val homePopularPersonAdapterBinding: HomePopularPersonAdapterBinding) : RecyclerView.ViewHolder(homePopularPersonAdapterBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bindData(personDto: PersonDto, context: Context?) {
            HelperMethods.loadPhoto(context,
                    personDto.personImagePath,
                    homePopularPersonAdapterBinding.imvPP)
            homePopularPersonAdapterBinding.tvName.text = personDto.personName + ""
        }

    }

}