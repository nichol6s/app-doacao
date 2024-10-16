package com.example.appdoacao

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DonationAdapter(
    private val donations: List<Donation>,
    private val onItemClick: (Donation) -> Unit
) : RecyclerView.Adapter<DonationAdapter.DonationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_donation, parent, false)
        return DonationViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: DonationViewHolder, position: Int) {
        holder.bind(donations[position])
    }

    override fun getItemCount() = donations.size

    class DonationViewHolder(
        itemView: View,
        private val onItemClick: (Donation) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.donationNameTextView)
        private val amountTextView: TextView = itemView.findViewById(R.id.donationAmountTextView)

        fun bind(donation: Donation) {
            nameTextView.text = donation.name
            amountTextView.text = "R$ ${donation.amount}"
            itemView.setOnClickListener { onItemClick(donation) }
        }
    }
}