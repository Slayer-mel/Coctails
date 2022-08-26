package space.mel.cocktail.di.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import space.mel.cocktail.viewmodel.CocktailsViewModel
import space.mel.cocktail.viewmodel.DrinkViewModel

val viewModelModule = module {
    viewModel { CocktailsViewModel(get()) }
    viewModel { DrinkViewModel(get()) }
}