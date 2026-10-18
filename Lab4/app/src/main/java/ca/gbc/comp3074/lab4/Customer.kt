package ca.gbc.comp3074.lab4

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val age: Int,
    val active: Boolean
) {
    constructor(name: String, age: Int, active: Boolean) : this(0, name, age, active)
}