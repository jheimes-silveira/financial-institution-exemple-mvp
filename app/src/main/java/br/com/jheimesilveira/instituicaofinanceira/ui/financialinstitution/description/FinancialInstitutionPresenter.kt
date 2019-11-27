package br.com.jheimesilveira.instituicaofinanceira.ui.financialinstitution.description

import br.com.jheimesilveira.instituicaofinanceira.data.model.FinancialInstitution
import java.io.Serializable

class FinancialInstitutionPresenter(private var view: FinancialInstitutionMvp.View?) :
    FinancialInstitutionMvp.Presenter {
    private lateinit var financialInstitution: FinancialInstitution

    override fun loadData(financialInstitution: Serializable?) {
        if (financialInstitution == null) return

        this.financialInstitution = financialInstitution as FinancialInstitution

        this.view?.showFinancialInstitution(financialInstitution)
    }


    override fun destroyView() {
        view = null
    }
}
