package space.mel.cocktail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import space.mel.cocktail.MainRecyclerViewAdapter
import space.mel.cocktail.R
import space.mel.cocktail.databinding.FragmentCocktailBinding
import space.mel.cocktail.entities.Drink
import space.mel.cocktail.viewmodel.CocktailsViewModel

class CocktailsFragment : Fragment() {
    var cocktailAdapter: MainRecyclerViewAdapter? = null
    lateinit var cocktailBinding: FragmentCocktailBinding
    private val cocktailsViewModel : CocktailsViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cocktailBinding = FragmentCocktailBinding.inflate(inflater)
        return cocktailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ingredient = arguments?.getString("Ingredient")
        if (ingredient != null) {
            cocktailsViewModel.fetchFiltreByIngredients(ingredient)
        }else{
            cocktailsViewModel.fetchAllCocktails()
        }
        initAdapter()
        initObservers()
    }


    private fun initObservers() {
        cocktailsViewModel.cocktailsListLiveData.observe(viewLifecycleOwner){
        cocktailAdapter?.setItem(it)
        }
    }

    fun initAdapter() {
        cocktailAdapter = MainRecyclerViewAdapter(
            onClick = ::startDrinkID,
        )
        cocktailBinding.rvCocktail.adapter = cocktailAdapter
    }

     fun startDrinkID(drinks: Drink) {
        findNavController().navigate(
            R.id.action_cocktail_fragment_to_drink_fragment,
            Bundle().apply {
                putString(
                    "DrinkID",
                    drinks.idDrink
                )
            }
        )
    }
}

