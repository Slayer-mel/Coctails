package space.mel.cocktail.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import space.mel.cocktail.MainRecyclerViewAdapter
import space.mel.cocktail.R
import space.mel.cocktail.databinding.FragmentCocktailBinding
import space.mel.cocktail.entities.Drink
import space.mel.cocktail.viewmodel.CocktailsViewModel

class CocktailsFragment : Fragment() {
    var mainAdapter: MainRecyclerViewAdapter? = null
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
        initAdapter()
        initObservers()
        getData()
    }


    private fun initObservers() {
        val view : RecyclerView = cocktailBinding.rvCocktail
        view.setHasFixedSize(true)
        view.setRecyclerListener {  }
        view.setItemViewCacheSize(5)

        cocktailsViewModel.cocktailsListLiveData.observe(viewLifecycleOwner){
        mainAdapter?.setItem(it)
        }
    }

    fun getData() {
        val handler = CoroutineExceptionHandler { _, t ->
            Log.d("LOGSLOGS", "Network Error: ${t.message}")
        }
        CoroutineScope(Dispatchers.IO).launch(handler){
        cocktailsViewModel.fetchCocktails()
        }
    }

    fun initAdapter() {
        mainAdapter = MainRecyclerViewAdapter(
            onClick = ::startDrinkID,
        )
        cocktailBinding.rvCocktail.adapter = mainAdapter
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

