package com.example.whowon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.whowon.databinding.RaffleItemLayoutBinding
import com.example.whowon.model.Raffle

class RaffleAdapter(private val raffles: List<Raffle>, private val clickListener: OnItemClickListener) :
    RecyclerView.Adapter<RaffleAdapter.RaffleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaffleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RaffleItemLayoutBinding.inflate(inflater, parent, false)
        return RaffleViewHolder(binding,clickListener)
    }

    override fun onBindViewHolder(holder: RaffleViewHolder, position: Int) {
        val raffle = raffles[position]
        holder.bind(raffle)

    }

    override fun getItemCount(): Int {
        return raffles.size
    }

   inner class RaffleViewHolder(private val binding: RaffleItemLayoutBinding,private val clickListener: OnItemClickListener) :
       RecyclerView.ViewHolder(binding.root) {

       fun bind(raffle: Raffle) {
           Glide.with(itemView)
               .load(raffle.img)
               .into(binding.raffleImageView)
           binding.raffleNameTextView.text = raffle.title
           binding.raffleAwardTextView.text = raffle.gift
           binding.raffleDurationTextView.text = raffle.date
           binding.rafflePriceTextView.text = raffle.price
           binding.cardView.setOnClickListener {
               clickListener.onItemClick(raffle)

           }
       }
   }
    interface OnItemClickListener {
        fun onItemClick(raffle: Raffle)
    }
}
