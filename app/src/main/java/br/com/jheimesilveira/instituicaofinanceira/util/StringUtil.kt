package br.com.jheimesilveira.instituicaofinanceira.util

object StringUtil {
    fun removeSpecialCharacterAndaccentuation(string: String?): String? {
        return if (string == null || string.isEmpty()) {
            string
        } else string.replace("[ãâàáä]".toRegex(), "a")
            .replace("[êèéë&]".toRegex(), "e")
            .replace("[îìíï]".toRegex(), "i")
            .replace("[õôòóö]".toRegex(), "o")
            .replace("[ûúùü]".toRegex(), "u")
            .replace("[ÃÂÀÁÄ]".toRegex(), "A")
            .replace("[ÊÈÉË]".toRegex(), "E")
            .replace("[ÎÌÍÏ]".toRegex(), "I")
            .replace("[ÕÔÒÓÖ]".toRegex(), "O")
            .replace("[ÛÙÚÜ]".toRegex(), "U")
            .replace('ç', 'c')
            .replace('Ç', 'C')
            .replace('ñ', 'n')
            .replace('Ñ', 'N')
            .replace("[^a-zA-Z0-9]".toRegex(), " ")

    }
}