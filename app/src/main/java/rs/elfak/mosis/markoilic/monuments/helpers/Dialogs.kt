package rs.elfak.mosis.markoilic.monuments.helpers

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import rs.elfak.mosis.markoilic.monuments.R
import rs.elfak.mosis.markoilic.monuments.databinding.InfoDialogBinding

object Dialogs {
    fun showInfoDialog(context: Context) {
        val dialog = Dialog(context)

        val binding: InfoDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.info_dialog, null, false);
        dialog.setContentView(binding.root)

        dialog.show()
    }
}