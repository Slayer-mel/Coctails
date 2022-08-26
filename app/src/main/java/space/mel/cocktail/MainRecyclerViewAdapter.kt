package space.mel.cocktail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import space.mel.cocktail.databinding.RvItemBinding
import space.mel.cocktail.entities.Drink

class MainRecyclerViewAdapter(val onClick: (Drink) -> Unit) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.MyViewHolder>() {
    var currentlist: List<Drink> = listOf()
    fun setItem(list: List<Drink>) {
        currentlist = list
        notifyDataSetChanged()
    }

    inner class MyViewHolder(
        val rvBinding: RvItemBinding
    ) : RecyclerView.ViewHolder(rvBinding.root) {
        fun setData(cocktail: Drink) {
            rvBinding.tvCocktail.text = "${cocktail.strDrink}"
            Glide
                .with(rvBinding.root.context)
                .load(cocktail.strDrinkThumb)
                .into(rvBinding.imgCocktail)
            rvBinding.root.setOnClickListener {
                onClick.invoke(cocktail)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setData(currentlist[position])
    }

    override fun getItemCount(): Int {
        return currentlist.size
    }
}