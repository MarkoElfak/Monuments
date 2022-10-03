package rs.elfak.mosis.markoilic.monuments.screens

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import rs.elfak.mosis.markoilic.monuments.R
import rs.elfak.mosis.markoilic.monuments.databinding.ActivityProfileBinding
import rs.elfak.mosis.markoilic.monuments.databinding.SingleLocationLayoutBinding
import com.ivankostadinovic.genericadapter.GenericAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.aprilapps.easyphotopicker.ChooserType
import pl.aprilapps.easyphotopicker.EasyImage
import pl.aprilapps.easyphotopicker.MediaFile
import pl.aprilapps.easyphotopicker.MediaSource
import rs.elfak.mosis.markoilic.monuments.data.Repository
import rs.elfak.mosis.markoilic.monuments.data.model.UserModel
import rs.elfak.mosis.markoilic.monuments.helpers.Permissions

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val locations = listOf("Location 1", "Location 2", "Location 3", "Location 4", "Location 5", "Location 6")

    private val easyImage = EasyImage.Builder(this).setChooserTitle("Pick media").setChooserType(ChooserType.CAMERA_AND_GALLERY).setFolderName("pictures").build()
    private lateinit var userInfo: UserModel
    private lateinit var imageURI: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)

        userInfo = Repository.getUserInfo(this)

        binding.data = userInfo

        Glide.with(this).load(binding.data.imageURL).into(binding.profileActivityPicture)
        
        binding.profileActivityEditBtn.setOnClickListener {
            changeAttributes(View.VISIBLE, true)
        }

        binding.profileActivityChangePictureBtn.setOnClickListener {
            if (Permissions.isLegacyExternalStoragePermissionRequired(this)) {
                Permissions.requestLegacyWriteExternalStoragePermission(this)
            } else {
                easyImage.openChooser(this)
            }
        }

        binding.profileActivitySaveBtn.setOnClickListener {
            changeAttributes(View.INVISIBLE, false)

            updateUserInformations()
        }

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

    private fun changeAttributes(visible: Int, change: Boolean) {
        binding.profileActivityUsername.isEnabled = change
        binding.profileActivityEmail.isEnabled = change

        binding.profileActivitySaveBtn.visibility = visible
        binding.profileActivityChangePictureBtn.visibility = visible
        binding.profileActivityEditBtn.visibility = if (visible == View.VISIBLE) View.INVISIBLE else View.VISIBLE
    }

    private fun updateUserInformations() {
        val user = UserModel()

        user.username = binding.profileActivityUsername.text.toString()
        user.email = binding.profileActivityEmail.text.toString()
        user.dateOfBirth = userInfo.dateOfBirth
        user.imageURL = userInfo.imageURL
        user.friendList = userInfo.friendList

        lifecycleScope.launch(Dispatchers.Main) {
            Repository.updateUserWithPicture(user, imageURI)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        easyImage.handleActivityResult(requestCode, resultCode, data, this, object : EasyImage.Callbacks {
            override fun onCanceled(source: MediaSource) {

            }

            override fun onImagePickerError(error: Throwable, source: MediaSource) {
                error.printStackTrace()
            }

            override fun onMediaFilesPicked(imageFiles: Array<MediaFile>, source: MediaSource) {
                for (file in imageFiles) {
                    imageURI = Uri.fromFile(file.file)
                    Glide.with(this@ProfileActivity).load(file.file).into(binding.profileActivityPicture)
                    binding.profileActivitySaveBtn.visibility = View.VISIBLE
                }
            }
        })
    }
}