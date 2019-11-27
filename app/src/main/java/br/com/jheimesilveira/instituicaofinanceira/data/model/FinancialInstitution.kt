package br.com.jheimesilveira.instituicaofinanceira.data.model

import java.io.Serializable

class FinancialInstitution(
    val name: String,
    val code: Int,
    val favorite: Boolean = false,
    val imageName: String? = null,
    val image: String? = null
): Serializable
