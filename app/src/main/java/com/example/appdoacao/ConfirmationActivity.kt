package com.example.appdoacao

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConfirmationActivity : AppCompatActivity() {
    private lateinit var donationTextView: TextView
    private lateinit var confirmButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        donationTextView = findViewById(R.id.donationTextView)
        confirmButton = findViewById(R.id.confirmButton)

        val donation = intent.getSerializableExtra("DONATION") as? Donation
        if (donation == null) {
            showToast("Erro: Doação não encontrada")
            finish()
            return
        }

        setupViews(donation)
    }

    private fun setupViews(donation: Donation) {
        donationTextView.text = "Doação: ${donation.name}\nValor: R$ ${donation.amount}"

        confirmButton.setOnClickListener {
            confirmDonation(donation)
        }
    }

    private fun confirmDonation(donation: Donation) {
        lifecycleScope.launch {
            confirmButton.isEnabled = false
            try {
                val success = withContext(Dispatchers.IO) {
                    // Simula uma chamada de API para processar a doação
                    simulateApiCall(donation)
                }
                if (success) {
                    showToast("Doação de ${donation.name} confirmada!")
                    finish()
                } else {
                    showToast("Erro ao processar a doação. Tente novamente.")
                }
            } catch (e: Exception) {
                showToast("Erro ao confirmar doação")
            } finally {
                confirmButton.isEnabled = true
            }
        }
    }

    private fun simulateApiCall(donation: Donation): Boolean {
        // Simula um delay de rede
        Thread.sleep(1500)
        // Simula uma confirmação bem-sucedida
        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}