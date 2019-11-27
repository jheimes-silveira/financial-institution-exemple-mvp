package br.com.jheimesilveira.instituicaofinanceira.data.service

import br.com.jheimesilveira.instituicaofinanceira.data.model.FinancialInstitution
import br.com.jheimesilveira.instituicaofinanceira.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Response

object FinancialInstitutionService {
    fun get(
        onSuccess: (financialInstitution: List<FinancialInstitution>) -> Unit,
        onError: (t: Throwable) -> Unit
    ) {
        val call = RetrofitClient.getFinancialInstitution().getList()

        call.enqueue(object : retrofit2.Callback<List<FinancialInstitution>> {
            override fun onResponse(
                call: Call<List<FinancialInstitution>>,
                response: Response<List<FinancialInstitution>>
            ) {

                if (response.isSuccessful) {
                    onSuccess(response.body() as List<FinancialInstitution>)

                    return
                }

                onError(Throwable("Não foi possível conectar-se ao servidor, verifique a conexão da sua rede e tente novamente"))
            }

            override fun onFailure(call: Call<List<FinancialInstitution>>, t: Throwable) {
                onError(t)
            }
        })
    }
}



