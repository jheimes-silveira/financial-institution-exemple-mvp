package br.com.jheimesilveira.instituicaofinanceira.ui.financialinstitution.description

import br.com.jheimesilveira.instituicaofinanceira.data.model.FinancialInstitution
import java.io.Serializable


interface FinancialInstitutionMvp {
    interface View {
        fun showFinancialInstitution(financialInstitution: FinancialInstitution)
    }

    interface Presenter {
        fun loadData(financialInstitution: Serializable?)

        fun destroyView()
    }
}
