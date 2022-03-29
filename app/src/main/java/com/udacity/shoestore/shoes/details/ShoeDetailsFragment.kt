package com.udacity.shoestore.shoes.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
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
        binding.activityViewModel = viewModel
        binding.setLifecycleOwner(this)
        binding.save.setOnClickListener {
            try {
                val shoe_name : String = binding.shoeName.text.toString()
                val shoe_company : String = binding.shoeCompany.text.toString()
                val shoe_size : Double = binding.shoeSize.text.toString().toDouble()
                val shoe_description = binding.shoeDescription.text.toString()
                if (shoe_name.isNotBlank() && shoe_company.isNotBlank() && !shoe_size.isNaN() && shoe_description.isNotBlank()){
                    viewModel.insertNew(Shoe(shoe_name, shoe_size, shoe_company, shoe_description))
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