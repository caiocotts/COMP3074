package ca.gbc.comp3074.lab4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CustomerViewModelFactory(private val customerDAO: CustomerDAO): ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>):T {
        if (modelClass.isAssignableFrom(CustomerViewModel::class.java)){
            return CustomerViewModel(customerDAO) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}