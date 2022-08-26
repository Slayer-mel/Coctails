package space.mel.cocktail

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import space.mel.cocktail.di.modules.retrofitModule
import space.mel.cocktail.di.modules.viewModelModule

class AppCocktail : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppCocktail)
            modules(
                listOf(
                    retrofitModule, viewModelModule
                )
            )
        }
    }
}