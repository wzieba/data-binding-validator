package br.com.ilhasoft.support.validation.rule

import android.app.Activity
import android.widget.TextView
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class EmailTypeRuleTest {

    private lateinit var activity: Activity
    private lateinit var textView: TextView

    @Before
    fun setUp() {
        activity = Robolectric.setupActivity(Activity::class.java)
        textView = TextView(activity)
    }

    @Test
    fun `should return false if email is invalid`() {
        textView.text = "notValidMail"
        val emailTypeRule = EmailTypeRule(textView, "")
        assertFalse(emailTypeRule.isValid(textView))
    }

    @Test
    fun `should return true if email is valid`() {
        textView.text = "valid@mail.com"
        val emailTypeRule = EmailTypeRule(textView, "")
        assertTrue(emailTypeRule.isValid(textView))
    }
}