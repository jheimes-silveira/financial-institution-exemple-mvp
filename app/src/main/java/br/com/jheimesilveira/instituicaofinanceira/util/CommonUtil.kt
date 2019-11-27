package br.com.jheimesilveira.instituicaofinanceira.util

import android.app.ProgressDialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable
import br.com.jheimesilveira.instituicaofinanceira.R
import com.bumptech.glide.Glide

object CommonUtil {
    fun showLoadingDialog(context: Context?): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.let {
            it.show()
            it.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            it.setContentView(R.layout.progress_dialog)
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)

            return it
        }
    }

    fun loadImage(context: Context, imageView: ImageView, posterPath: String?) {
        Glide
            .with(context)
            .load(posterPath)
            .error(R.drawable.ic_bank_svgrepo_com)
            .into(imageView)
    }

    fun convertDpToIntPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}