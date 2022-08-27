package space.mel.cocktail.retrofit

import retrofit2.http.GET
import retrofit2.http.Query
import space.mel.cocktail.entities.CocktailsList
import space.mel.cocktail.entities.DetailedCocktailsList

interface CocktailApi {

    @GET("filter.php")
    suspend fun getCocktails(
        @Query("c") c:String
    ) : CocktailsList


    @GET("lookup.php")
    suspend fun getDrinks(
        @Query ("i") i: Int
    ) : DetailedCocktailsList

    @GET ("filter.php")
    suspend fun getFiltredCocktails(
        @Query("i") i : String
    ) : CocktailsList
}