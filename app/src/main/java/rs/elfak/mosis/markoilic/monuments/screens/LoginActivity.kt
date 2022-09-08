package rs.elfak.mosis.markoilic.monuments.screens

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
                Repository.login(binding.loginEditTextUsername.text.toString(), binding.loginEditTextPassword.text.toString()).collect {
                    Toast.makeText(this@LoginActivity, it.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}