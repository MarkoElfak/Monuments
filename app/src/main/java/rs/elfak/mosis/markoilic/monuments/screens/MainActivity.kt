package rs.elfak.mosis.markoilic.monuments.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import rs.elfak.mosis.markoilic.monuments.R
import rs.elfak.mosis.markoilic.monuments.databinding.ActivityMainBinding
import rs.elfak.mosis.markoilic.monuments.helpers.Dialogs

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.mainActivityLoginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java);
            startActivity(intent);
        }

        binding.mainActivityRegisterBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java);
            startActivity(intent);
        }

        binding.mainActivityInfoIV.setOnClickListener {
            Dialogs.showInfoDialog(this)
        }

        binding.mainActivityForgetPasswordLabel.setOnClickListener{
            val intent = Intent(this, ForgotPasswordActivity::class.java);
            startActivity(intent);
        }
    }
}