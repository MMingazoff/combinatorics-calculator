package com.itis.combinatoricscalculator.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.DigitsKeyListener
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.internal.ViewUtils
import com.itis.combinatoricscalculator.MainActivity
import com.itis.combinatoricscalculator.R
import com.itis.combinatoricscalculator.databinding.FragmentPermutationsBinding
import com.itis.combinatoricscalculator.factorial

class PermutationsFragment : Fragment(R.layout.fragment_permutations) {
    private var binding: FragmentPermutationsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPermutationsBinding.bind(view)
        setUpClickListeners()
    }


    @SuppressLint("RestrictedApi")
    private fun setUpClickListeners() {
        binding?.run {
            root.setOnClickListener {
                ViewUtils.hideKeyboard(it)
            }
            switchWithRepetitions.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked)
                    ivFormula.setImageResource(R.drawable.permutations_with_repeats).also {
                        etInput.keyListener = DigitsKeyListener.getInstance("0123456789.,")
                    }
                else
                    ivFormula.setImageResource(R.drawable.permutations).also {
                        etInput.keyListener = null
                    }
            }
            btnCalculate.setOnClickListener {
                if (checkValues()) {
                    if (switchWithRepetitions.isChecked) {
                        val inputs = etInput.text
                            .toString()
                            .split(",")
                            .map {
                                it.toInt().factorial()
                            }
                        tvResult.text = getString(
                            R.string.result,
                            inputs.reduce { a, b -> a * b }.toString()
                        ).also {
                            etInput.error = null
                        }
                    } else {
                        try {
                            tvResult.text =
                                getString(
                                    R.string.result,
                                    etInput.text.toString().toInt().factorial().toString()
                                ).also {
                                    etInput.error = null
                                }
                        } catch (e: NumberFormatException) {
                            etInput.error = getString(R.string.error)
                        }
                    }
                } else {
                    etInput.error = getString(R.string.error)
                }
            }
        }
    }

    private fun checkValues(): Boolean {
        val n = binding?.etInput?.text.toString()
        return n.isNotBlank()
    }
}