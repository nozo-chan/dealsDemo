package com.socialdeal.app.helpers

fun String.removeHtmlTagsWithRegex(): String {
    return this.replace(Regex("<.*?>"), "")
}