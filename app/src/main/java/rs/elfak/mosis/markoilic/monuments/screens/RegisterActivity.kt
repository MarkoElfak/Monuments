package rs.elfak.mosis.markoilic.monuments.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import rs.elfak.mosis.markoilic.monuments.R
import rs.elfak.mosis.markoilic.monuments.data.Repository
import rs.elfak.mosis.markoilic.monuments.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        binding.registerActivityRegisterBtn.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                Repository.register(binding.registerEditTextUsername.text.toString(), binding.registerEditTextPassword.text.toString(),
                    binding.registerEditTextEmail.text.toString(), binding.registerEditTextDateOfBirth.text.toString()).collect {

                    Toast.makeText(this@RegisterActivity, it.toString(), Toast.LENGTH_LONG).show()
                }
                }
            }
        }
    }