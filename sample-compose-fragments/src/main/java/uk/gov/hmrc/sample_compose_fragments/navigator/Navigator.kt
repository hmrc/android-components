package uk.gov.hmrc.sample_compose_fragments.navigator

import androidx.fragment.app.Fragment

interface Navigator {
    fun Fragment.gotoTextInputView()

    fun Fragment.gotoCurrencyInputView()

    fun Fragment.gotoMultiRowTextFragment()

    fun Fragment.gotoMoleculeH4TitleBodyView()

    fun Fragment.gotoMoleculeH5TitleBodyView()

    fun Fragment.gotoMoleculeInsetView()

    fun Fragment.gotoMoleculeInsetTextView()

    fun Fragment.gotoMoleculeBoldTitleBodyView()

    fun Fragment.goBack()
}