package br.com.jheimesilveira.instituicaofinanceira

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.jheimesilveira.instituicaofinanceira.ui.financialinstitution.list.ListFinancialInstitutionActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        android.os.Handler().postDelayed({
            runOnUiThread {
                Intent(this, ListFinancialInstitutionActivity::class.java).apply {
                    startActivity(this)
                }

                finish()
            }
        }, 2000)
    }
}
