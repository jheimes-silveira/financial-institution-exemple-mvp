package br.com.jheimesilveira.instituicaofinanceira.ui.financialinstitution.list

import android.view.View
import br.com.jheimesilveira.instituicaofinanceira.data.model.FinancialInstitution
import br.com.jheimesilveira.instituicaofinanceira.data.service.FinancialInstitutionService
import br.com.jheimesilveira.instituicaofinanceira.util.containsSimilar

class ListFinancialInstitutionPresenter(private var view: ListFinancialInstitutionMvp.View?) :
    ListFinancialInstitutionMvp.Presenter {
    private var financialInstitution: List<FinancialInstitution> = ArrayList()

    override fun getListService() {
        this.view?.onLoading(true)
        this.view?.onEmptyState(View.GONE)

        FinancialInstitutionService.get(
            onSuccess = {
                financialInstitution = it

                this.view?.onLoading(false)

                emptyState(it)

                this.view?.showFinancialInstitution(financialInstitution)
            },
            onError = {
                this.view?.onLoading(false)

                this.view?.onError(it)
            })
    }

    override fun setOnQueryTextListenerSubmit(query: String?) {
        if (query == null) return

        val filter = financialInstitution.filter {
            it.name.containsSimilar(query)
        }

        this.view?.showFinancialInstitution(filter)
        emptyState(filter)
        this.view?.onQueryTextListenerSubmit(query)
    }

    override fun setOnQueryTextListenerChange(query: String?) {
        if (query == null) return

        val filter = financialInstitution.filter {
            it.name.containsSimilar(query) || it.code.toString().containsSimilar(query)
        }

        emptyState(filter)

        this.view?.showFinancialInstitution(filter)
    }

    private fun emptyState(list: List<FinancialInstitution>?) {
        if (list.isNullOrEmpty()) {
            this.view?.onEmptyState(View.VISIBLE)
            return
        }

        this.view?.onEmptyState(View.GONE)
    }

    override fun destroyView() {
        view = null
    }
}
