package space.mel.cocktail.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class CocktailsList(
    val drinks: List<Drink>
)
@Parcelize
data class Drink(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String
) : Parcelable
