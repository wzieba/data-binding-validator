package br.com.ilhasoft.support.validation.entity

import org.threeten.bp.format.DateTimeFormatter

class DateIntervalRuleParam(
        val dateFormatter: DateTimeFormatter,
        val acceptableDifferenceInMillis: Long,
        val baseTimeInMillis: Long?,
        val dateIntervalType: DateIntervalType
)