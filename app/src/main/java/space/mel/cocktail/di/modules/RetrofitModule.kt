package space.mel.cocktail.di.modules

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.mel.cocktail.retrofit.BaseURL
import space.mel.cocktail.retrofit.CocktailApi

val retrofitModule = module {
    single { provideRetrofit() }
    single { provideCocktailApi(get()) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BaseURL.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideCocktailApi(retrofit: Retrofit): CocktailApi {
    return retrofit.create(CocktailApi::class.java)
}
