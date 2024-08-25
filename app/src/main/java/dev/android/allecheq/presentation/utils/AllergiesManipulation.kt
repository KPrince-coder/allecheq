package dev.android.allecheq.presentation.utils

fun encodedStringToList(encodedString: String?): List<Int>? {
    /**
     * @return Convert String representation back to List<Int>
     */
    return encodedString?.split(",")?.mapNotNull { it.toIntOrNull() }
}

fun listToEncodedString(list: List<Int>): String {
    /**
     *@return Convert List<Int> to String representation
     */
    return list.joinToString(", ")
}