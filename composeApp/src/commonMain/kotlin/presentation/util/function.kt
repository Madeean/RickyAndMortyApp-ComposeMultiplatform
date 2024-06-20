package presentation.util

fun getIdFromUrl(url: List<String>): String {
  var allId = ""
  url.map {
    allId += "${it.substringAfterLast("/").toInt()},"
  }
  return allId
}