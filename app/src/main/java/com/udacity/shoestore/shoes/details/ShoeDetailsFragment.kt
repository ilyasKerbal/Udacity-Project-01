package com.udacity.shoestore.shoes.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.Converters
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.ActivityViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailsLayoutBinding
import com.udacity.shoestore.models.Shoe
import java.lang.Exception

class ShoeDetailsFragment : Fragment() {

    private lateinit var viewModel: ActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : ShoeDetailsLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.shoe_details_layout, container, false)
        binding.cancel.setOnClickListener {
            findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment(false))
        }
        
        viewModel = ViewModelProvider(requireActivity()).get(ActivityViewModel::class.java)
        binding.shoe = viewModel.randomShoe.value
        binding.setLifecycleOwner(this)
        binding.save.setOnClickListener {
            try {
                val shoe_name : String = binding?.shoe?.name ?: ""
                val shoe_company : String = binding?.shoe?.company ?: ""
                val shoe_size : Double = binding?.shoe?.size ?: Double.NaN
                val shoe_description = binding?.shoe?.description ?: ""
                if (shoe_name.isNotBlank() && shoe_company.isNotBlank() && !shoe_size.isNaN() && shoe_description.isNotBlank()){
                    binding?.shoe?.copy()?.let { it1 -> viewModel.insertNew(it1) }
                    findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment(true))
                } else {
                    Toast.makeText(requireContext(), "Invalid Input", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception){
                Toast.makeText(requireContext(), "Invalid Input", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

}