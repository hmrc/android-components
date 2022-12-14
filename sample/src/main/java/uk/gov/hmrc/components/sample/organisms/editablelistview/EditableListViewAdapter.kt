package uk.gov.hmrc.components.sample.organisms.editablelistview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uk.gov.hmrc.components.sample.databinding.EditableListViewItemBinding

class EditableListViewAdapter(
    private val values: List<EditableListViewFragment.CompanyBenefit>,
    private val onClickListener: OnItemClickListener,
) : RecyclerView.Adapter<EditableListViewAdapter.ViewHolder>() {

    var isEditEnable: Boolean = false
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            EditableListViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(values[position], onClickListener)
    }

    inner class ViewHolder(private val binding: EditableListViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            result: EditableListViewFragment.CompanyBenefit,
            onClickListener: OnItemClickListener

        ) = with(binding) {
            benefitName.text = result.name
            benefitAmount.text = result.amount
            editButton.text = result.button
            editButton.setOnClickListener {
                onClickListener(bindingAdapterPosition)
            }

            if (isEditEnable) {
                motionLayout.transitionToEnd()
            } else {
                motionLayout.transitionToStart()
            }
        }
    }

}
typealias OnItemClickListener = (position: Int) -> Unit