package br.com.ilhasoft.support.validation.rule

import android.app.Activity
import android.widget.TextView
import br.com.ilhasoft.support.validation.entity.DateIntervalRuleParam
import br.com.ilhasoft.support.validation.entity.DateIntervalType
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.threeten.bp.format.DateTimeFormatter

//10 Oct 2018, 8:06:05
private const val BASE_DATE_IN_MILLIS: Long = 1539158754993
private const val EIGHTEEN_YEARS_IN_MILLIS = 567648000000
private val testDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

@RunWith(RobolectricTestRunner::class)
class DateIntervalRuleTest {

    private lateinit var activity: Activity
    private lateinit var textView: TextView

    @Before
    fun setUp() {
        activity = Robolectric.setupActivity(Activity::class.java)
        textView = TextView(activity)
    }

    @Test
    fun `should return true if date is earlier than given difference`() {
        textView.text = "1990-02-03"
        val dateIntervalRule = generateDateIntervalRule(DateIntervalType.EARLIER_THAN)

        assertTrue(dateIntervalRule.isValid(textView))
    }

    @Test
    fun `should return false if date is not earlier than given difference`() {
        textView.text = "2015-02-03"
        val dateIntervalRule = generateDateIntervalRule(DateIntervalType.EARLIER_THAN)

        assertFalse(dateIntervalRule.isValid(textView))
    }

    @Test
    fun `should return true if date is later than given difference`() {
        textView.text = "2050-02-03"
        val dateIntervalRule = generateDateIntervalRule(DateIntervalType.LATER_THAN)

        assertTrue(dateIntervalRule.isValid(textView))
    }

    @Test
    fun `should return false if date is not later than given difference`() {
        textView.text = "2020-02-03"
        val dateIntervalRule = generateDateIntervalRule(DateIntervalType.LATER_THAN)

        assertFalse(dateIntervalRule.isValid(textView))
    }

    @Test
    fun `should return false if date is not proper`() {
        textView.text = "4230-80-50"
        val dateIntervalRule = generateDateIntervalRule(DateIntervalType.LATER_THAN)

        assertFalse(dateIntervalRule.isValid(textView))
    }

    private fun generateDateIntervalRule(dateIntervalType: DateIntervalType): DateIntervalRule {
        return DateIntervalRule(textView, DateIntervalRuleParam(
                testDateFormatter,
                EIGHTEEN_YEARS_IN_MILLIS,
                BASE_DATE_IN_MILLIS,
                dateIntervalType
        ), "")
    }
}