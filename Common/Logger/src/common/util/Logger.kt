package common.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Logs information on the console.
 *
 * @author Alexander Komischke
 */
object Logger {
    private const val shouldLog = true
    private const val separator = "\t : \t"

    private fun format(message: String): String {
        if (!shouldLog) {
            return ""
        }
        return SimpleDateFormat("yyy.MM.dd HH:mm:ss")
                .format(GregorianCalendar().time) + separator + message
    }

    fun log(o: Any?) = log(o.toString())

    fun log(vararg o: Any?) {
        var strings = ""
        for (i in 0 until (o.size)) {
            strings += i.toString() + separator
        }
        log(strings)
    }

    fun log(message: String) {
        if (shouldLog) {
            println(format(message))
        }
    }

}
