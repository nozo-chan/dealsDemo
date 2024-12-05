package com.socialdeal.app.helpers

import androidx.core.text.HtmlCompat

fun stripHtmlTags(input: String): String {
    return HtmlCompat.fromHtml(input, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_LIST_ITEM).toString()
}