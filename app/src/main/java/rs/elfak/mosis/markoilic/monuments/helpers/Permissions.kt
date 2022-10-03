package rs.elfak.mosis.markoilic.monuments.helpers

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object Permissions {

    private const val LEGACY_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 456
    private val LEGACY_WRITE_PERMISSIONS = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    fun isLegacyExternalStoragePermissionRequired(context: Context): Boolean {
        val permissionGranted = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

        return Build.VERSION.SDK_INT < 29 && !permissionGranted
    }

    fun requestLegacyWriteExternalStoragePermission(context: Context) {
        ActivityCompat.requestPermissions(context as Activity, LEGACY_WRITE_PERMISSIONS, LEGACY_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE)
    }
}