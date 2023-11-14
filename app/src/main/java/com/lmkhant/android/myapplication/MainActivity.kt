package com.lmkhant.android.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lmkhant.android.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    lateinit var binding: ActivityMainBinding

    private lateinit var inputViewModel: InputViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: InputAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        recyclerView= findViewById(R.id.rv_input)

        binding.etInput.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnAdd.isEnabled = s.toString().trim().isNotEmpty()

            }

        })
        binding.btnAdd.setOnClickListener {
            inputViewModel.addInput(Input(binding.etInput.text.toString(), false))
            binding.etInput.setText("")
        }
        binding.btnAdd.isEnabled = false

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)

        binding.btnDelete.setOnClickListener {
            inputViewModel.removeCheckedInputs()
        }
        inputViewModel = ViewModelProvider(this)[InputViewModel::class.java]


        adapter = InputAdapter(mutableListOf())

        recyclerView.adapter = adapter
        inputViewModel.dataset.observe(this) { dataset ->
            dataset?.let {
                adapter.updateList(it.toMutableList())
                adapter.notifyDataSetChanged()
            }
        }
    }
}