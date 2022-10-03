package rs.elfak.mosis.markoilic.monuments.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import rs.elfak.mosis.markoilic.monuments.R
import rs.elfak.mosis.markoilic.monuments.databinding.ActivityProfileBinding
import rs.elfak.mosis.markoilic.monuments.databinding.SingleLocationLayoutBinding
import com.ivankostadinovic.genericadapter.GenericAdapter
import rs.elfak.mosis.markoilic.monuments.data.Repository

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val locations = listOf("Location 1", "Location 2", "Location 3", "Location 4", "Location 5", "Location 6")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)

        binding.data = Repository.getUserInfo(this)

        val myLocationAdapter = object: GenericAdapter<String, SingleLocationLayoutBinding>(locations, R.layout.single_location_layout) {
            override fun onBindData(model: String?, position: Int, dataBinding: SingleLocationLayoutBinding) {
            }

            override fun onItemClick(model: String?, position: Int) {
                Toast.makeText(this@ProfileActivity, model, Toast.LENGTH_LONG).show()
            }

        }

        binding.profileActivityRV.layoutManager = LinearLayoutManager(this)
        binding.profileActivityRV.adapter = myLocationAdapter
    }
}