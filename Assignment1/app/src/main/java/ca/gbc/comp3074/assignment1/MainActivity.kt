package ca.gbc.comp3074.assignment1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val editTextHoursWorked by lazy { findViewById<EditText>(R.id.editTextHoursWorked) }
    private val editTextHourlyRate by lazy { findViewById<EditText>(R.id.editTextHourlyRate) }
    private val editTextTaxRate by lazy { findViewById<EditText>(R.id.editTextTaxRate) }

    private val buttonCalculatePay by lazy { findViewById<Button>(R.id.buttonCalculatePay) }

    private val textPay by lazy { findViewById<TextView>(R.id.textPay) }
    private val textOvertimePay by lazy { findViewById<TextView>(R.id.textOvertimePay) }
    private val textTotalPay by lazy { findViewById<TextView>(R.id.textTotalPay) }
    private val textTax by lazy { findViewById<TextView>(R.id.textTax) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val extraPadding = (15 * resources.displayMetrics.density).toInt()
            v.setPadding(
                systemBars.left + extraPadding,
                systemBars.top + extraPadding,
                systemBars.right + extraPadding,
                systemBars.bottom + extraPadding
            )
            insets
        }

        buttonCalculatePay.setOnClickListener {
            val hours = editTextHoursWorked.text.toString().toIntOrNull()
            val rate = editTextHourlyRate.text.toString().toDoubleOrNull()
            val taxRate = editTextTaxRate.text.toString().toDoubleOrNull()
            if (hours == null || rate == null || taxRate == null) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            } else {
                val res = calculatePay(hours, rate, taxRate)
                textPay.text = getString(R.string.money_amount, res.pay)
                textOvertimePay.text = getString(R.string.money_amount, res.overtimePay)
                textTotalPay.text = getString(R.string.money_amount, res.totalPay)
                textTax.text = getString(R.string.money_amount, res.tax)
                WindowCompat.getInsetsController(window, it).hide(WindowInsetsCompat.Type.ime())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun calculatePay(hours: Int, rate: Double, taxRate: Double): CalculationResult {
        val pay = hours * rate
        val overtimePay = when {
            hours < 40 -> 0.0
            else -> (hours - 40) * rate * 1.5
        }
        val totalPay = pay + overtimePay
        val tax = pay * taxRate

        return CalculationResult(pay, overtimePay, totalPay, tax)
    }
}