package gr.padpad.marvellivedata.ui.adapters.series

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import gr.padpad.marvellivedata.R
import gr.padpad.marvellivedata.model.response.marvel.series.MarvelSeries
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_hero.*

class SeriesRecyclerViewAdapter(private val seriesList: MutableList<MarvelSeries>) : RecyclerView.Adapter<SeriesRecyclerViewAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_series, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val series = seriesList[position]

        holder.heroName.text = series.name
        holder.heroDescription.text = handleDescription(series.description
                ?: "", holder.itemView.context)
        Picasso.get().load(series.getEventImage()).into(holder.heroImageView)
    }

    private fun handleDescription(description: String, context: Context): String {
        return when {
            description.isEmpty() -> context.resources.getString(R.string.dummy_description)
            else -> description
        }
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }

    class ItemViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
}




