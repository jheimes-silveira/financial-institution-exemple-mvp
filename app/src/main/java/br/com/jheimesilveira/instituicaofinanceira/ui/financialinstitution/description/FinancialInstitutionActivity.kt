package br.com.jheimesilveira.instituicaofinanceira.ui.financialinstitution.description

import android.os.Bundle
import br.com.jheimesilveira.instituicaofinanceira.R
import br.com.jheimesilveira.instituicaofinanceira.data.model.FinancialInstitution
import br.com.jheimesilveira.instituicaofinanceira.ui.BaseActivity
import br.com.jheimesilveira.instituicaofinanceira.util.CommonUtil
import kotlinx.android.synthetic.main.activity_financial_institution.*

class FinancialInstitutionActivity : BaseActivity(), FinancialInstitutionMvp.View {
    companion object {
        const val FINANCIAL_INSTITUTION = "FINANCIAL_INSTITUTION"
    }

    private lateinit var presenter: FinancialInstitutionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        setContentView(R.layout.activity_financial_institution)

        presenter = FinancialInstitutionPresenter(this)

        presenter.loadData(intent.extras?.getSerializable(FINANCIAL_INSTITUTION))
    }

    override fun finish() {
        super.finish()

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun showFinancialInstitution(financialInstitution: FinancialInstitution) {
        initTvTitle(financialInstitution)
        initTvCode(financialInstitution)
        initTvImage(financialInstitution)
        initToolbar(financialInstitution)
    }

    private fun initTvTitle(financialInstitution: FinancialInstitution) {
        tvTitle.text = financialInstitution.name
    }

    private fun initTvCode(financialInstitution: FinancialInstitution) {
        tvCode.text = financialInstitution.code.toString()
    }

    private fun initTvImage(financialInstitution: FinancialInstitution) {
        with(financialInstitution) {
            var urlImage = this.image

            if (urlImage.isNullOrEmpty()) {
                urlImage = this.imageName
            }

            CommonUtil.loadImage(this@FinancialInstitutionActivity, ivImage, urlImage)
        }
    }

    private fun initToolbar(financialInstitution: FinancialInstitution) {
        toolbar.title = financialInstitution.name

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}
