package br.com.ilhasoft.support.validation.rule

import android.widget.TextView
import br.com.ilhasoft.support.validation.entity.DateIntervalRuleParam
import br.com.ilhasoft.support.validation.entity.DateIntervalType
import br.com.ilhasoft.support.validation.util.EditTextHandler
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeParseException

class DateIntervalRule(
        view: TextView,
        dateIntervalRuleParam: DateIntervalRuleParam,
        errorMessage: String
) : Rule<TextView, DateIntervalRuleParam>(view, dateIntervalRuleParam, errorMessage) {

    public override fun isValid(view: TextView): Boolean {
        val rawTextDate = view.text
        if (rawTextDate.toString().isEmpty()) {
            return true
        }

        val baseTimeInMillisOrCurrentTime = value.baseTimeInMillis ?: System.currentTimeMillis()

        with(value) {

            return try {
                val dateToCheckInMillis = parseDateToMillis(rawTextDate)
                when (dateIntervalType) {
                    DateIntervalType.LATER_THAN -> {
                        dateToCheckInMillis - baseTimeInMillisOrCurrentTime > acceptableDifferenceInMillis
                    }
                    DateIntervalType.EARLIER_THAN -> {
                        baseTimeInMillisOrCurrentTime - dateToCheckInMillis > acceptableDifferenceInMillis
                    }
                }
            } catch (exception: DateTimeParseException) {
                false
            }
        }
    }

    public override fun onValidationSucceeded(view: TextView) {
        EditTextHandler.removeError(view)
    }

    public override fun onValidationFailed(view: TextView) {
        EditTextHandler.setError(view, errorMessage)
    }

    private fun DateIntervalRuleParam.parseDateToMillis(rawTextDate: CharSequence) =
            LocalDate.parse(rawTextDate, dateFormatter)
                    .atStartOfDay()
                    .toInstant(ZoneOffset.ofTotalSeconds(0))
                    .toEpochMilli()
}
