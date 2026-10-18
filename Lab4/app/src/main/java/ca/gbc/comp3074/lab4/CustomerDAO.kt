package ca.gbc.comp3074.lab4

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(customer: Customer)

    @Delete
    suspend fun delete(customer: Customer)

    @Query("select * from customer")
    fun getAll(): Flow<List<Customer>>

    @Query("select * from customer where name like '%'||:name||'%' order by name")
    fun getByName(name: String): Flow<List<Customer>>
}
