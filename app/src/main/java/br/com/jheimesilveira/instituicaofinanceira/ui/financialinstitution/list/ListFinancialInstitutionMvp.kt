package br.com.jheimesilveira.instituicaofinanceira.ui.financialinstitution.list

import br.com.jheimesilveira.instituicaofinanceira.data.model.FinancialInstitution


interface ListFinancialInstitutionMvp {
    interface View {
        fun showFinancialInstitution(financialInstitutions: List<FinancialInstitution>)

        fun onError(e: Throwable)

        fun onEmptyState(visible: Int)

        fun onLoading(status: Boolean)

        fun onQueryTextListenerSubmit(query: String?)
    }

    interface Presenter {
        fun getListService()

        fun setOnQueryTextListenerSubmit(query: String?)

        fun setOnQueryTextListenerChange(query: String?)

        fun destroyView()
    }
}
