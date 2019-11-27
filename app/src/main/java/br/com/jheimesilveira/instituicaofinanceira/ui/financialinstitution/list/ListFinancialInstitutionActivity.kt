package br.com.jheimesilveira.instituicaofinanceira.ui.financialinstitution.list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.jheimesilveira.instituicaofinanceira.R
import br.com.jheimesilveira.instituicaofinanceira.data.model.FinancialInstitution
import br.com.jheimesilveira.instituicaofinanceira.ui.BaseActivity
import br.com.jheimesilveira.instituicaofinanceira.ui.financialinstitution.description.FinancialInstitutionActivity
import br.com.mauker.materialsearchview.MaterialSearchView
import com.github.siwarats.itemdecoration.stickyheader.StickyHeaderItemDecoration
import kotlinx.android.synthetic.main.activity_list_financial_institution.*

class ListFinancialInstitutionActivity : BaseActivity(), ListFinancialInstitutionMvp.View {
    private lateinit var presenter: ListFinancialInstitutionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list_financial_institution)

        presenter = ListFinancialInstitutionPresenter(this)

        setSupportActionBar(toolbar)

        initRecyclerView()

        initSearchView()

        presenter.getListService()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list_financial_institution, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_search -> {
                searchView.openSearch()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.destroyView()
    }

    override fun onBackPressed() {
        if (searchView.isOpen) {
            searchView.closeSearch()
            return
        }

        super.onBackPressed()
    }

    override fun showFinancialInstitution(financialInstitutions: List<FinancialInstitution>) {
        val dataSet = formatDataWithSection(financialInstitutions)

        (recyclerView.adapter as ListFinancialInstitutionAdapter).update(dataSet)
    }

    override fun onError(e: Throwable) {
        showMessage(e.message!!)
    }

    override fun onEmptyState(visible: Int) {
        animationCrying.visibility = visible
    }

    override fun onLoading(status: Boolean) {
        progressBar(status)
    }

    override fun onQueryTextListenerSubmit(query: String?) {
        searchView.closeSearch()
    }

    private fun initSearchView() {
        searchView.setVoiceIcon(0)

        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.setOnQueryTextListenerSubmit(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.setOnQueryTextListenerChange(newText)
                return true
            }

        })
    }

    private fun formatDataWithSection(financialInstitutions: List<FinancialInstitution>): List<Any> {
        val objects = ArrayList<Any>()

        val financialInstitutions = financialInstitutions.sortedWith(compareBy { !it.favorite })

        val favorite = getString(R.string.more_used)
        val others = getString(R.string.others)

        var currentSection: String? = null

        financialInstitutions.map { currentItem ->

            if (currentItem.favorite && currentSection != favorite) {
                currentSection = favorite
                objects.add(favorite)
            }

            if (!currentItem.favorite && currentSection != others) {
                currentSection = others
                objects.add(others)
            }

            objects.add(currentItem)
        }

        return objects
    }

    private fun initRecyclerView() {
        with(recyclerView) {
            layoutManager = LinearLayoutManager(this@ListFinancialInstitutionActivity)
            adapter = initAdapter()
            addItemDecoration(StickyHeaderItemDecoration())
        }
    }

    private fun initAdapter(): ListFinancialInstitutionAdapter {
        val adapter = ListFinancialInstitutionAdapter(this)

        adapter.onItemClickListener { item ->
            Intent(this, FinancialInstitutionActivity::class.java).apply {
                putExtra(FinancialInstitutionActivity.FINANCIAL_INSTITUTION, item)

                startActivity(this)
            }
        }

        return adapter
    }
}
