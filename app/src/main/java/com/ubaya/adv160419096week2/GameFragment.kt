package com.ubaya.adv160419096week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText

class GameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val num1 = (1..99).random()
        val num2 = (1..99).random()

        val txtNumber1 = view.findViewById<TextView>(R.id.txtNumber1)
        val txtNumber2 = view.findViewById<TextView>(R.id.txtNumber2)

        txtNumber1.text = num1.toString()
        txtNumber2.text = num2.toString()

        val txtTurn = view.findViewById<TextView>(R.id.txtTurn)
        if(arguments != null){
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            txtTurn.text = "$playerName's Turn"
        }

        var point = 0
        val txtAnswer = view.findViewById<TextInputEditText>(R.id.txtAnswer)
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener{
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            if(arguments != null){
                txtTurn.text = "$playerName's Turn"
            }

            var result = num1 + num2
            if(result.toString() == txtAnswer.text.toString()){
                point += 1
                val action = GameFragmentDirections.actionSelf(playerName)
                Navigation.findNavController(it).navigate(action)
                if(result.toString() != txtAnswer.text.toString()){
                    val action = GameFragmentDirections.actionResultFragment(point)
                    Navigation.findNavController(it).navigate(action)
                }
            } else {
                val action = GameFragmentDirections.actionResultFragment(point)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}