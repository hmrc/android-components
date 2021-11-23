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
package uk.gov.hmrc.components.molecule.item

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.databinding.ComponentSwitchViewRowBinding
import uk.gov.hmrc.components.extensions.setStyle

class SwitchRowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val binding: ComponentSwitchViewRowBinding =
        ComponentSwitchViewRowBinding.inflate(LayoutInflater.from(context), this)

    init {
        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(it, R.styleable.SwitchRowView, 0, 0)

            val title = typedArray.getString(R.styleable.SwitchRowView_title) ?: ""
            val titleStyle = typedArray.getResourceId(R.styleable.SwitchRowView_titleStyle, R.style.Text_Bold)
            val body = typedArray.getString(R.styleable.SwitchRowView_body) ?: ""
            val showBody = typedArray.getBoolean(R.styleable.SwitchRowView_showBody, true)
            val checked = typedArray.getBoolean(R.styleable.SwitchRowView_checked, false)
            val switchContentDescription = typedArray.getString(R.styleable.SwitchRowView_switchContentDescription)

            setTitle(title)
            setTitleStyle(titleStyle)
            setBody(body)
            showBody(showBody)
            setChecked(checked)
            setSwitchContentDescription(switchContentDescription)

            typedArray.recycle()
        }
    }

    fun setSwitchContentDescription(switchContentDescription: String?) {
        binding.switchRowSwitch.contentDescription = switchContentDescription
    }

    fun setTitleStyle(@StyleRes titleStyle: Int) {
        binding.switchRowTitle.setStyle(titleStyle)
    }

    fun setTitle(title: CharSequence) {
        binding.switchRowTitle.text = title
    }

    fun setTitle(@StringRes id: Int) {
        binding.switchRowTitle.text = context.getString(id)
    }

    fun setBody(body: CharSequence) {
        binding.switchRowBody.text = body
    }

    fun showBody(showBody: Boolean = true) {
        binding.switchRowBody.visibility = if (showBody) View.VISIBLE else View.GONE
    }

    fun setChecked(checked: Boolean = true) {
        binding.switchRowSwitch.isChecked = checked
    }

    fun setCheckedChangedListener(checkedChangedListener: (enabled: Boolean) -> Unit) {
        binding.switchRowSwitch.setOnCheckedChangeListener { _, enabled ->
            checkedChangedListener.invoke(enabled)
        }
    }
}
