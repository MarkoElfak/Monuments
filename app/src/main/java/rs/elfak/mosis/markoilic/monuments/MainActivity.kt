package rs.elfak.mosis.markoilic.monuments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import rs.elfak.mosis.markoilic.monuments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.mainActivityLoginBtn.setOnClickListener{
            val intent = Intent(this, LoginActivity :: class.java);
            startActivity(intent);
        }

        binding.mainActivityRegisterBtn.setOnClickListener{
            val intent = Intent(this, RegisterActivity :: class.java);
            startActivity(intent);
        }
    }
}