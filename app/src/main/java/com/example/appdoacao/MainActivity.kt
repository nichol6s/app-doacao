package com.example.appdoacao

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var donationRecyclerView: RecyclerView
    private lateinit var donationAdapter: DonationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        donationRecyclerView = findViewById(R.id.donationRecyclerView)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        donationAdapter = DonationAdapter(getDonations()) { donation ->
            navigateToConfirmation(donation)
        }
        donationRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = donationAdapter
        }
    }

    private fun getDonations(): List<Donation> {
        // Simula obtenção de doações de uma fonte de dados
        return listOf(
            Donation("1", "Doação para Hospital", 100.0),
            Donation("2", "Ajuda para Orfanato", 50.0),
            Donation("3", "Apoio à Educação", 75.0)
        )
    }

    private fun navigateToConfirmation(donation: Donation) {
        val intent = Intent(this, ConfirmationActivity::class.java).apply {
            putExtra("DONATION", donation)
        }
        startActivity(intent)
    }
}