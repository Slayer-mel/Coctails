package space.mel.cocktail.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import space.mel.cocktail.entities.Drink
import space.mel.cocktail.retrofit.CocktailApi

class CocktailsViewModel(
    private val cocktailApi: CocktailApi
) : ViewModel() {

    val cocktailsListLiveData : MutableLiveData<List<Drink>> = MutableLiveData()

     fun fetchAllCocktails() {
        val handler = CoroutineExceptionHandler { _, t ->
            Log.d("LOGSLOGS", "Cocktails Network Error: ${t.message}")
        }
        viewModelScope.launch(handler + Dispatchers.IO) {
            val allDrinlksResult = cocktailApi.getCocktails(c = "Cocktail")
            cocktailsListLiveData.postValue(allDrinlksResult.drinks)
        }
    }

    fun fetchFiltreByIngredients (ingredient : String) {
        val handler = CoroutineExceptionHandler { _, t ->
            Log.d("CheckError", "Filtered cocktails Network Error: ${t.message}")
        }

        viewModelScope.launch(handler + Dispatchers.IO) {
            val allFilteredCocktailsResult = cocktailApi.getFiltredCocktails(i = ingredient)
            cocktailsListLiveData.postValue(allFilteredCocktailsResult.drinks)
        }
    }
}