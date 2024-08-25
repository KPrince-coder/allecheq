package dev.android.allecheq.presentation.utils

fun encodedStringToList(encodedString: String?): List<Int> {
    /**
     * @return Convert List<Int> to String representation
     */
    return encodedString?.split(",")?.map { it.toInt() } ?: emptyList()
}

fun listToEncodedList(list: List<Int>): String {
    /**
     *@return Convert String representation back to List<Int>
     */
    return list.joinToString(", ")
}