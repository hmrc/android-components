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
package uk.gov.hmrc.components.molecule.tabbar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver
import com.google.android.material.tabs.TabLayout
import uk.gov.hmrc.components.R

class TabBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TabLayout(context, attrs, defStyleAttr) {

    private var initialHeight: Int? = null

    private val parentLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            (parent as View).viewTreeObserver.removeOnGlobalLayoutListener(this)
            tabMode = MODE_AUTO // set temporarily to auto to prevent long text auto-resizing to fit smaller views
            val screenWidth = (parent as View).width
            measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
            val tabsWidth = measuredWidth

            if (screenWidth > tabsWidth) {
                tabMode = MODE_FIXED // need MODE_FIXED because MODE_AUTO centers and we want to fill the space
                tabGravity = GRAVITY_FILL
            } else {
                tabMode = MODE_SCROLLABLE
            }

            resources.configuration.fontScale.takeIf { it != 1.0f }?.let {
                if (initialHeight == null) initialHeight = height
                // need to use initial height so it doesn't keep growing when toggled
                layoutParams.height = (initialHeight!! * it).toInt()
            }

            // re-select selected tab incase longer text has pushed it sideways off screen and need to scroll
            getTabAt(selectedTabPosition)?.select()
        }
    }

    override fun addTab(tab: Tab) {
        super.addTab(tab)
        onAddTab()
    }

    override fun addTab(tab: Tab, position: Int) {
        super.addTab(tab, position)
        onAddTab()
    }

    override fun addTab(tab: Tab, setSelected: Boolean) {
        super.addTab(tab, setSelected)
        onAddTab()
    }

    override fun addTab(tab: Tab, position: Int, setSelected: Boolean) {
        super.addTab(tab, position, setSelected)
        onAddTab()
    }

    private fun onAddTab() {
        updateContentDescriptions()
        (parent as View).viewTreeObserver.addOnGlobalLayoutListener(parentLayoutListener)
    }

    private fun updateContentDescriptions() {
        for (i in 0..tabCount) {
            val position = context.getString(R.string.accessibility_tab_position, i + 1, tabCount)
            getTabAt(i)?.apply {
                contentDescription = "$text, $position"
            }
        }
    }
}
