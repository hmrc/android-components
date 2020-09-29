/*
 * Copyright 2020 HM Revenue & Customs
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
package uk.gov.hmrc.components.sample.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import uk.gov.hmrc.components.sample.MainActivity
import uk.gov.hmrc.components.sample.R

abstract class BaseComponentsFragment : Fragment() {

    abstract fun provideToolbar(): ToolbarState

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setToolbar(provideToolbar())
    }

    protected fun selectFragment(fragment: BaseComponentsFragment) {
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, fragment)?.addToBackStack("")?.commit()
    }
}

data class ToolbarState(val enabled: Boolean = false, @StringRes val title: Int? = null, val homeAsUpEnabled: Boolean = false) {
    fun getTitle(context: Context): String {
        title ?: return ""
        return context.getString(title)
    }
}
