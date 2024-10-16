package com.example.appdoacao

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                showToast("Por favor, preencha todos os campos")
                return@setOnClickListener
            }

            performLogin(username, password)
        }
    }

    private fun performLogin(username: String, password: String) {
        lifecycleScope.launch {
            loginButton.isEnabled = false
            try {
                val success = withContext(Dispatchers.IO) {
                    // Simula uma chamada de API
                    simulateApiCall(username, password)
                }
                if (success) {
                    showToast("Login realizado com sucesso")
                    startMainActivity()
                } else {
                    showToast("Credenciais inválidas")
                }
            } catch (e: Exception) {
                showToast("Erro ao realizar login")
            } finally {
                loginButton.isEnabled = true
            }
        }
    }

    private fun simulateApiCall(username: String, password: String): Boolean {
        // Simula um delay de rede
        Thread.sleep(1000)
        // Simula uma validação simples
        return username == "user" && password == "password"
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Isso impede que o usuário volte para a tela de login usando o botão "Voltar"
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}