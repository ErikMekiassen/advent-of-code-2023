package library

class library {
    fun stringToArray (string: String, regex: String): List<String> {
        return string.trim().split(regex)
    }
    fun stringToInt (it: String): Int {
        return it.trim().toInt()
    }
    fun rename(string: String, chrOld: String, chrNew: String): String {
        return string.replace(chrOld, chrNew)
    }
}