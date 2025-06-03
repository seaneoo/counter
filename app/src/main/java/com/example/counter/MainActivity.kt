package com.example.counter

import android.os.Bundle
import android.text.Editable
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.counter.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this)[CounterViewModel::class]

        viewModel.counter.observe(this) {
            binding.counterText.text = String.format(Locale.ENGLISH, "%03d", it)

            binding.decrementButton.isEnabled = it > 0
            binding.resetButton.isEnabled = it > 0
        }

        binding.valueTextField.editText?.let {
            it.addTextChangedListener { editable -> viewModel.setAmount(editable.toString()) }

            it.text = Editable.Factory.getInstance().newEditable(viewModel.amount.value.toString())
        }

        binding.incrementButton.setOnClickListener { viewModel.increment() }
        binding.decrementButton.setOnClickListener { viewModel.decrement() }
        binding.resetButton.setOnClickListener { viewModel.reset() }
    }
}
