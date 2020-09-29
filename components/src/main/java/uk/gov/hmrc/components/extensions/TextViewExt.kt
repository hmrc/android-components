/*
 * Copyright 2019 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.gov.hmrc.components.extensions

import android.os.Build
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import java.util.regex.Matcher
import java.util.regex.Pattern
import uk.gov.hmrc.components.R

class CustomLinkMovementMethod(private val clickHandler: (String) -> Unit = {}) : LinkMovementMethod() {

    override fun onTouchEvent(widget: TextView?, buffer: Spannable?, event: MotionEvent?): Boolean {

        val argsAreNotNull = widget != null && buffer != null && event != null

        if (argsAreNotNull && event!!.action == MotionEvent.ACTION_UP) {
            var x = event.x
            var y = event.y

            x -= widget!!.totalPaddingLeft
            y -= widget.totalPaddingTop

            x += widget.scrollX
            y += widget.scrollY

            val layout = widget.layout
            val line = layout.getLineForVertical(y.toInt())
            val off = layout.getOffsetForHorizontal(line, x)

            val link = buffer!!.getSpans(off, off, URLSpan::class.java)
            if (link.isNotEmpty()) {
                clickHandler.invoke(link[0].url)
                return true
            }
        }
        return super.onTouchEvent(widget, buffer, event)
    }
}

/**
 * Makes the given text clickable via HTML or a given pattern to match with.
 * Default HTML link color will be applied unless a color is passed in.
 *
 * @param text you wish to make clickable
 * @param clickHandler that you want to trigger when a url or text matching the pattern is clicked
 * @param pattern to identify urls in the text (optional)
 * @param color that you wish links to be. By default no color is set (optional)
 */
@JvmOverloads
fun TextView.setClickableUrl(
    text: String,
    clickHandler: (String) -> Unit = {},
    pattern: Pattern? = null,
    @ColorRes color: Int = R.color.hmrc_link_text
) {
    val linkColor = ContextCompat.getColor(context, color)

    this.isClickable = true
    this.movementMethod = CustomLinkMovementMethod(clickHandler)
    this.setLinkTextColor(linkColor)

    val setHandler: (String) -> Unit = { matched ->
        val startIndex = text.indexOf(matched)
        val customSpannable = SpannableString(text)
        customSpannable.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) = clickHandler.invoke(matched)

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
                ds.color = linkColor
            }
        }, startIndex, startIndex + matched.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        this.text = customSpannable

        if (context.isAccessibilityEnabled()) {
            setOnClickListener { clickHandler.invoke(matched) }
        }
    }

    pattern?.let {
        val matcher: Matcher = it.matcher(text)
        if (matcher.find()) {
            val matched = matcher.group(0)
            setHandler(matched)
            return
        }
    }

    this.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(text.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        Html.fromHtml(text.toString())
    }

    val spannable = SpannableString(getText())

    for (span in spannable.getSpans(0, spannable.length, URLSpan::class.java)) {
        val start = spannable.getSpanStart(span)
        val end = spannable.getSpanEnd(span)
        spannable.removeSpan(span)
        val spanNoUnderline = URLSpanNoUnderline(span.url, clickHandler, linkColor)
        spannable.setSpan(spanNoUnderline, start, end, 0)
    }

    this.text = spannable
}

class URLSpanNoUnderline(
    private val url: String,
    private val clickHandler: (String) -> Unit = {},
    @ColorInt private val urlColor: Int
) : URLSpan(url) {
    override fun onClick(widget: View) {
        super.onClick(widget)
        clickHandler.invoke(url)
    }

    override fun updateDrawState(ds: TextPaint) {
        ds.isUnderlineText = false
        ds.color = urlColor
    }
}

fun TextView.setStyle(@StyleRes style: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        setTextAppearance(style)
    } else {
        @Suppress("DEPRECATION")
        setTextAppearance(context, style)
    }
}
