package uk.gov.hmrc.sample_compose_fragments.navigator

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import uk.gov.hmrc.sample_compose_fragments.presentation.fragment.molecules.MoleculesFragmentDirections
import javax.inject.Inject

class AppNavigator @Inject constructor() : Navigator{

    override fun Fragment.gotoMoleculeH4TitleBodyView(){
        navigate(MoleculesFragmentDirections.actionMoleculesFragmentToH4TitleBodyViewFragment())
    }

    override fun Fragment.gotoMoleculeH5TitleBodyView(){
        navigate(MoleculesFragmentDirections.actionMoleculesFragmentToH5TitleBodyViewFragment())
    }

    override fun Fragment.gotoMoleculeInsetView() {
        navigate(MoleculesFragmentDirections.actionMoleculesFragmentToInsetViewFragment())
    }

    override fun Fragment.gotoMoleculeInsetTextView() {
        navigate(MoleculesFragmentDirections.actionMoleculesFragmentToInsetTextViewFragment())
    }

    override fun Fragment.gotoMoleculeBoldTitleBodyView(){
        navigate(MoleculesFragmentDirections.actionMoleculesFragmentToBoldTitleBodyViewFragment())
    }

    override fun Fragment.gotoTextInputView() {
        navigate(MoleculesFragmentDirections.actionMoleculesFragmentToTextInputViewFragment())
    }

    override fun Fragment.gotoCurrencyInputView() {
        navigate(MoleculesFragmentDirections.actionMoleculesFragmentToCurrencyInputViewFragment())
    }

    override fun Fragment.gotoMultiRowTextFragment() {
        navigate(MoleculesFragmentDirections.actionMoleculesFragmentToNavMultiRowTextFragment())
    }

    override fun Fragment.gotoMoleculeSwitchRowView() {
        navigate(MoleculesFragmentDirections.actionMoleculesFragmentToSwitchRowViewFragment())
    }

    override fun Fragment.goBack() {
        findNavController().popBackStack()
    }

    private fun Fragment.navigate(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }
}