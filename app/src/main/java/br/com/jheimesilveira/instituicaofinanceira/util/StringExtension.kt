package br.com.jheimesilveira.instituicaofinanceira.util



fun String.containsSimilar(expression: String): Boolean {
    val value: String = this.toUpperCase()

    return StringUtil.removeSpecialCharacterAndaccentuation(value)?.contains(expression.toUpperCase()) ?: return false
}