package br.com.jheimesilveira.instituicaofinanceira.ui.financialinstitution.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.jheimesilveira.instituicaofinanceira.R
import br.com.jheimesilveira.instituicaofinanceira.data.model.FinancialInstitution
import br.com.jheimesilveira.instituicaofinanceira.util.CommonUtil
import com.github.siwarats.itemdecoration.stickyheader.StickyViewHolder
import kotlinx.android.synthetic.main.adapter_header_list_financial_institution.view.*
import kotlinx.android.synthetic.main.adapter_list_financial_institution.view.*

class ListFinancialInstitutionAdapter(
    private val context: Context,
    private var dataSet: List<Any> = ArrayList()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val HEADER_TYPE = 0
        const val ITEM_TYPE = 1
    }

    private var itemClickListener: ((financialInstitution: FinancialInstitution) -> Unit)? = null

    override fun getItemViewType(position: Int): Int {
        if (dataSet[position] is String) {
            return HEADER_TYPE
        }

        return ITEM_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER_TYPE -> HeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapter_header_list_financial_institution, parent, false)
            )

            else -> ItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapter_list_financial_institution, parent, false)
            )
        }
    }

    override fun getItemCount() = dataSet.count()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> initHeaderViewHolder(holder, position)
            is ItemViewHolder -> initItemViewHolder(holder, position)
        }
    }

    private fun initHeaderViewHolder(
        holder: HeaderViewHolder,
        position: Int
    ) {
        val header = dataSet[position] as String

        holder.tvTitle.text = header

        initMarginTop(holder, position)
    }

    private fun initMarginTop(holder: RecyclerView.ViewHolder, position: Int) {
        val params = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        when (position) {
            0 -> {
                params.topMargin = CommonUtil.convertDpToIntPx(0)
            }
            else -> {
                params.topMargin = CommonUtil.convertDpToIntPx(16)
            }
        }

        holder.itemView.layoutParams = params
    }

    private fun initItemViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        val financialInstitution = dataSet[position] as FinancialInstitution

        with(financialInstitution) {
            holder.tvTitle.text = "$code - $name"

            var urlImage = this.image

            if (urlImage.isNullOrEmpty()) {
                urlImage = this.imageName
            }

            CommonUtil.loadImage(context, holder.ivImage, urlImage)
        }

        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(financialInstitution)
        }
    }

    fun onItemClickListener(itemClickListener: (financialInstitution: FinancialInstitution) -> Unit) {
        this.itemClickListener = itemClickListener
    }

    fun update(dataSet: List<Any>) {
        this.dataSet = dataSet

        notifyDataSetChanged()
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.tvTitle
        val ivImage: ImageView = itemView.ivImage
    }

    class HeaderViewHolder(itemView: View) : StickyViewHolder(itemView) {
        val tvTitle: TextView = itemView.tvTitleHeader
    }
}
