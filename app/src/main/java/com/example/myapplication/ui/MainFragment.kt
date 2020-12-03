package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ProcessAdapter
import com.example.myapplication.R
import com.example.myapplication.Result
import com.example.myapplication.algorithm.*

class MainFragment : Fragment() {
    private lateinit var numberOfCore: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val algorithm: Algorithm = ReverseLongProcessTimeAlgorithmImpl()
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val rv = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = ProcessAdapter.build(rv)

        view.findViewById<View>(R.id.button6).setOnClickListener {
            calculate(BinPackingAlgorithmImpl(), adapter.getData())
        }

        view.findViewById<View>(R.id.button7).setOnClickListener {
            calculate(ShortProcessTimeAlgorithmImpl(), adapter.getData())
        }

        view.findViewById<View>(R.id.button5).setOnClickListener {
            calculate(ReverseLongProcessTimeAlgorithmImpl(), adapter.getData())
        }


        view.findViewById<View>(R.id.button).setOnClickListener {
            calculate(LongProcessTimeAlgorithmImpl(), adapter.getData())
        }

        view.findViewById<View>(R.id.button4).setOnClickListener {
            adapter.clear()
        }

        val process = view.findViewById<EditText>(R.id.editTextNumberDecimal)
        numberOfCore = view.findViewById(R.id.editTextNumberDecimal2)
        view.findViewById<View>(R.id.button2).setOnClickListener {
            try {
                adapter.addItem(process.text.toString().toFloat())
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "error to add", Toast.LENGTH_SHORT).show()
            } finally {
                process.setText("")
            }
        }
        return view
    }

    private fun calculate(algorithm: Algorithm, list: List<Float>) {
        try {

            val result = algorithm.calculate(list, numberOfCore.text.toString().toInt())

            findNavController().navigate(MainFragmentDirections.actionMainFragmentToResultFragment(result.toTypedArray()))
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "error to core number", Toast.LENGTH_SHORT).show()
        } finally {
            numberOfCore.setText("")
        }
    }

}