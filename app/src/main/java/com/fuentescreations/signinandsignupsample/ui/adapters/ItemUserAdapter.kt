package com.fuentescreations.signinandsignupsample.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.fuentescreations.signinandsignupsample.databinding.ItemUserBinding
import com.fuentescreations.signinandsignupsample.ui.data.models.UserModel

class ItemUserAdapter (private val userModelList: List<UserModel>) : RecyclerView.Adapter<ItemUserAdapter.ItemUserViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemUserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ItemUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemUserViewHolder, position: Int) { holder.bind(userModelList[position]) }

    override fun getItemCount(): Int = userModelList.size

    class ItemUserViewHolder(private val binding : ItemUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(userModel: UserModel){

            binding.tvEmail.text = userModel.email

            binding.btnPassword.setOnClickListener {
                Toast.makeText(itemView.context, userModel.password, Toast.LENGTH_SHORT).show() }
        }
    }
}