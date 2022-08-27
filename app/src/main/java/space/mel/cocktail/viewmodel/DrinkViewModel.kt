package space.mel.cocktail.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import space.mel.cocktail.entities.DetailedDrink
import space.mel.cocktail.retrofit.CocktailApi

class DrinkViewModel(
    private val cocktailApi: CocktailApi
) : ViewModel() {

    val drinkIDLiveData: MutableLiveData<DetailedDrink> = MutableLiveData()

    fun fetchDrinkInfo(drinkID: String) {
        val handler = CoroutineExceptionHandler { _, t ->
            Log.d("LOGSLOGS", "DrinkInfo Network Error: ${t.message}")
        }
        CoroutineScope(Dispatchers.IO).launch(handler) {
            val drinkInfo = cocktailApi.getDrinks(i = drinkID.toInt())
            Log.d("LOGSLOGS", "DrinkInfo: $drinkInfo")
            withContext(Dispatchers.Main) {
                drinkInfo.drinks?.first()?.let {
                    drinkIDLiveData.value = it
                }
            }
        }
    }
}