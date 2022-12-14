package rs.elfak.mosis.markoilic.monuments.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rs.elfak.mosis.markoilic.monuments.R
import rs.elfak.mosis.markoilic.monuments.data.Repository
import rs.elfak.mosis.markoilic.monuments.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.loginActivityLoginBtn.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                Repository.login(binding.loginEditTextEmail.text.toString(), binding.loginEditTextPassword.text.toString()).collect {

                    intent = Intent(this@LoginActivity, ProfileActivity::class.java)

                    Repository.saveUserInfo(this@LoginActivity, it)

                    startActivity(intent)

                    //Toast.makeText(this@LoginActivity, it.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}