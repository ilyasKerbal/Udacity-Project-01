package com.udacity.shoestore.shoes.listing

import android.os.Bundle
import android.util.AttributeSet
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.ActivityViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListLayoutBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber


class ShoeListFragment : Fragment() {

    private lateinit var viewModel: ActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : ShoeListLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.shoe_list_layout, container, false)
        setHasOptionsMenu(true)
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment())
        }
        viewModel = ViewModelProvider(requireActivity()).get(ActivityViewModel::class.java)
        val shoeListArgs by navArgs<ShoeListFragmentArgs>()
        viewModel.listOfShoes.observe(viewLifecycleOwner, Observer { newList ->
            if (shoeListArgs.addedToList) {
                Timber.i("New Shoe object received: ${newList.last().toString()}")
            }
            newList.forEach {
                val showView = createViewFromShoe(newList.last())
                binding.shoeListContainer.addView(showView)
            }

        })
        return binding.root
    }

    private fun createViewFromShoe(shoe : Shoe) : TextView {
        val shoeView = TextView(requireContext(), null, 0, R.style.shoe_item)
        shoeView.text = "${shoe.name}\n${shoe.company}\n${shoe.size}\n${shoe.description}"
        return shoeView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        requireView().findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
        return super.onOptionsItemSelected(item)
    }
}