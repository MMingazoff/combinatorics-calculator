package com.itis.combinatoricscalculator.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.internal.ViewUtils
import com.itis.combinatoricscalculator.MainActivity
import com.itis.combinatoricscalculator.R
import com.itis.combinatoricscalculator.databinding.FragmentArrangementBinding
import com.itis.combinatoricscalculator.factorial
import com.itis.combinatoricscalculator.power

class ArrangementFragment : Fragment(R.layout.fragment_arrangement) {
    private var binding: FragmentArrangementBinding? = null
    private var result = 0L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArrangementBinding.bind(view)
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
                    ivFormula.setImageResource(R.drawable.arrangement_with_repeats)
                else
                    ivFormula.setImageResource(R.drawable.arrangement)
            }
            btnCalculate.setOnClickListener {
                if (switchWithRepetitions.isChecked) {
                    val n = etInputN.text.toString().toInt()
                    val k = etInputK.text.toString().toInt()
                    tvResult.text = getString(
                        R.string.result,
                        n.power(k).toString()
                    ).also {
                        etInputN.error = null
                        etInputK.error = null
                    }
                } else {
                    if (checkValues()) {
                        val n = etInputN.text.toString().toInt()
                        val k = etInputK.text.toString().toInt()
                        tvResult.text = getString(
                            R.string.result,
                            (n.factorial() / (n - k).factorial()).toString()
                        ).also {
                            etInputN.error = null
                            etInputK.error = null
                        }
                    } else {
                        etInputN.error = getString(R.string.error)
                        etInputK.error = getString(R.string.error)
                    }
                }

            }
        }
    }

    private fun checkValues(): Boolean {
        val n = binding?.etInputN?.text.toString()
        val k = binding?.etInputK?.text.toString()
        if (n.isBlank() or k.isBlank())
            return false
        if (n.toInt() < k.toInt())
            return false
        return true
    }
}