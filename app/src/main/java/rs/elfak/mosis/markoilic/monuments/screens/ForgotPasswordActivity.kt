package rs.elfak.mosis.markoilic.monuments.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import rs.elfak.mosis.markoilic.monuments.R
import rs.elfak.mosis.markoilic.monuments.databinding.ActivityForgotPasswordBinding
import rs.elfak.mosis.markoilic.monuments.databinding.ActivityLoginBinding
import rs.elfak.mosis.markoilic.monuments.helpers.Consts.auth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password)

        binding.forgotEmailRegisterBtn.setOnClickListener{
            resetPassword(binding.forgotEditTextEmail.text.toString())
        }
    }

    private fun resetPassword(email: String) {

        if(email.isEmpty()){
            binding.forgotEditTextEmail.error = "Email is required!"
            binding.forgotEditTextEmail.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.forgotEditTextEmail.error = "Please provide Email!"
            binding.forgotEditTextEmail.requestFocus()
            return
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this, "Check Your Email to reset Your password!", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, LoginActivity::class.java))
            }
            else {
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_LONG).show()
            }
        }
    }


}