package com.example.insulinapp
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.insulinapp.databinding.FragmentFourthBinding
import kotlin.math.roundToInt


class FourthFragment  : Fragment(){
    //set up variables
    private var _binding: FragmentFourthBinding? = null
    private lateinit var carb1: EditText
    private lateinit var carb2: EditText
    private lateinit var glucose1: EditText
    private lateinit var glucose2: EditText
    private lateinit var glucose3: EditText

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFourthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carb1 = binding.editCarbContent
        carb2 = binding.editCarbRatio
        glucose1 = binding.editCurrentGlucose
        glucose2 = binding.editTargetGlucose
        glucose3 = binding.editSensitivity

        binding.buttonCalculate.setOnClickListener {
            makeCalculations()
        }
    }

    private fun makeCalculations() {
        val carbContent = carb1.text.toString().toDoubleOrNull()
        val carbRatio = carb2.text.toString().toDoubleOrNull()
        val current = glucose1.text.toString().toDoubleOrNull()
        val target = glucose2.text.toString().toDoubleOrNull()
        val sensitivity = glucose3.text.toString().toDoubleOrNull()

        if (carbContent == null || carbRatio == null || current == null
            || target == null || sensitivity == null) {
            // Handle invalid input values
            Toast.makeText(activity, "Please enter valid values for all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val carbInsulin = (carbContent / carbRatio * 2).roundToInt() / 2.0f
        val glucoseInsulin = ((current - target) / sensitivity *2).roundToInt() / 2.0f
        val result = carbInsulin + glucoseInsulin

        val ciResult = binding.editCarbInsulin
        ciResult.text = "$carbInsulin"

        val giResult = binding.editGlucoseInsulin
        giResult.text= "$glucoseInsulin"

        val resultTotal = binding.editTotalInsulin
        resultTotal.text= "$result"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




