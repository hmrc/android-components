package uk.gov.hmrc.components.sample.organisms.editablelistview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import uk.gov.hmrc.components.extensions.setAccessibilityMessage
import uk.gov.hmrc.components.extensions.setAsAccessibilityHeading
import uk.gov.hmrc.components.sample.R
import uk.gov.hmrc.components.sample.autoCleared
import uk.gov.hmrc.components.sample.base.BaseComponentsFragment
import uk.gov.hmrc.components.sample.base.ToolbarState
import uk.gov.hmrc.components.sample.databinding.FragmentEditableListViewBinding

class EditableListViewFragment : BaseComponentsFragment() {

    private var binding: FragmentEditableListViewBinding by autoCleared()

    private lateinit var editableListViewAdapter: EditableListViewAdapter

    private var cbList = ArrayList<CompanyBenefit>()

    var clicked = false
    var itemPosition: Int? = null

    override fun provideToolbar() = ToolbarState(true, R.string.molecules_editable_list_view, true)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditableListViewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        cbList.add(CompanyBenefit(name = "Medical", amount = "£1000", button = "Edit"))
        cbList.add(CompanyBenefit(name = "Car benefit", amount = "£600", button = "Edit"))


        editableListViewAdapter =
            EditableListViewAdapter(cbList) { position ->
                itemPosition = position
            }

        editableListViewAdapter

        binding.list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = editableListViewAdapter
        }

        binding.editBenefitsButton.apply {

            setOnClickListener {
                editableListViewAdapter.isEditEnable = !editableListViewAdapter.isEditEnable
                if (clicked) {
                    clicked = false
                    announceForAccessibility("Edit buttons now hidden")

                    text = getString(R.string.edit_button_update_or_remove)
                    setAccessibilityMessage("edit your company benefits")

                } else {
                    clicked = true
                    announceForAccessibility("Edit buttons now visible")

                    text = getString(R.string.edit_button_finish)
                    setAccessibilityMessage("finish editing your company benefits")

                    binding.cbHeader.apply {
                        setAsAccessibilityHeading(true)
                        contentDescription = "Started editing your company benefits"
                    }

                }
            }
        }
    }

    data class CompanyBenefit(var name: String, var amount: String, var button: String)
}