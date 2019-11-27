package br.com.jheimesilveira.instituicaofinanceira.ui

import android.app.ProgressDialog
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.jheimesilveira.instituicaofinanceira.util.CommonUtil

abstract class BaseActivity : AppCompatActivity() {
    private var progressDialog: ProgressDialog? = null

    fun progressBar(status: Boolean) {
        if (!status) {
            progressDialog?.let { if (it.isShowing) it.cancel() }
            return
        }

        progressDialog?.let { if (it.isShowing) it.cancel() }
        progressDialog = CommonUtil.showLoadingDialog(this)
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}