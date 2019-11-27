package br.com.jheimesilveira.instituicaofinanceira.data.network

import br.com.jheimesilveira.instituicaofinanceira.data.model.FinancialInstitution
import retrofit2.Call
import retrofit2.http.GET

interface FinancialInstitutionApi {
    @GET(ApiEndPoint.FINANCIAL_INSTITUTION)
    fun getList(): Call<List<FinancialInstitution>>
}