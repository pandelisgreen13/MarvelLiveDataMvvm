package gr.padpad.marvellivedata.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import gr.padpad.marvellivedata.R
import gr.padpad.marvellivedata.model.data.MarvelHeroesModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_hero.*
import kotlinx.android.synthetic.main.row_hero.view.*

class DashboardRecyclerViewAdapter(private val heroList: MutableList<MarvelHeroesModel>, private val onFavouriteClicked: (Int) -> Unit) : RecyclerView.Adapter<DashboardRecyclerViewAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_hero, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val hero = heroList[position]
        holder.heroName.text = hero.name
        holder.heroDescription.text = handleDescription(hero.description, holder.itemView.context)
        holder.favouriteButton.isSelected = hero.isFavorite
        holder.favouriteButton.setOnClickListener {
            onFavouriteClicked(hero.id)
            hero.isFavorite = !hero.isFavorite
            it.isSelected = hero.isFavorite
            notifyItemChanged(position)
        }
        Picasso.get().load(hero.thumbnail).into(holder.heroImageView.heroImageView)
    }

    private fun handleDescription(description: String, context: Context): String {
        return when {
            description.isEmpty() -> context.resources.getString(R.string.dummy_description)
            else -> description
        }
    }

    override fun getItemCount(): Int {
        return heroList.size
    }

    class ItemViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
}




