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
    private const val empty = ""
    private const val dateFormat = "yyy.MM.dd HH:mm:ss"

    private infix fun format(message: String): String =
            if (!shouldLog) empty
            else SimpleDateFormat(dateFormat)
                    .format(GregorianCalendar().time) + separator + message

    infix fun log(o: Any?) = log(o.toString())

    fun log(vararg o: Any?) {
        var strings = empty
        for (i in 0 until (o.size)) {
            strings += i.toString() + separator
        }
        log(strings)
    }

    infix fun log(message: String) {
        if (shouldLog) {
            println(this format message)
        }
    }

}
