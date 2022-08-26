package space.mel.cocktail.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import space.mel.cocktail.databinding.FragmentDrinkBinding
import space.mel.cocktail.viewmodel.DrinkViewModel

class DrinkFragment : Fragment() {
    lateinit var drinkFragmentBinding: FragmentDrinkBinding
    private val drinksViewModel: DrinkViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        drinkFragmentBinding = FragmentDrinkBinding.inflate(inflater)
        return drinkFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val drinkID = arguments?.getString("DrinkID")
        if (drinkID != null) {
            drinksViewModel.fetchDrinkInfo(drinkID)
        }
        initObservers()
    }

    private fun initObservers() {
        drinksViewModel.drinkIDLiveData.observe(viewLifecycleOwner) { detailedDrink ->
            Log.d("LOGSLOGS", "$detailedDrink")
            with(drinkFragmentBinding) {
                tvDrinkName.text = detailedDrink.strDrink
                tvDrinkIngredient1.text = detailedDrink.strIngredient1
                tvDrinkIngredient2.text = detailedDrink.strIngredient2
                tvDrinkIngredient3.text = detailedDrink.strIngredient3
                tvDrinkIngredient4.text = detailedDrink.strIngredient4
                tvDrinkIngredient5.text = detailedDrink.strIngredient5
                Glide
                    .with(drinkFragmentBinding.root)
                    .load(detailedDrink.strDrinkThumb)
                    .into(drinkFragmentBinding.ivDrink)
            }
        }
    }
}