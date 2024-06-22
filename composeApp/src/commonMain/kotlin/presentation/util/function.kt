package presentation.util

fun getIdFromUrl(url: List<String>): String {
  var allId = ""
  url.map {
    allId += "${it.substringAfterLast("/").toInt()},"
  }
  return allId
}

fun getIdFromUrl(url: String): Int {
  return url.substringAfterLast("/").toInt()
}