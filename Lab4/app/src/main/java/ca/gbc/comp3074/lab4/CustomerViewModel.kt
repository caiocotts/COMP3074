package ca.gbc.comp3074.lab4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CustomerViewModel(private val customerDAO: CustomerDAO) : ViewModel() {
    val customers: Flow<List<Customer>> = customerDAO.getAll()

    fun create(name: String, age: Int, active: Boolean) {
        viewModelScope.launch { customerDAO.insert(Customer(name, age, active)) }
    }

    fun delete(customer: Customer) {
        viewModelScope.launch { customerDAO.delete(customer) }
    }
}
