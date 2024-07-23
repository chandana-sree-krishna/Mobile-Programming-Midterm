package com.example.midterm_q3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_first)

            val bundle: Bundle? = intent.extras
            bundle?.let {
                val msg = bundle.getString("extra_data")
                Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
            }

            val button: Button = findViewById(R.id.btResult)
            val edtxt1: EditText = findViewById(R.id.ednum1)
            val edtxt2: EditText = findViewById(R.id.ednum2)
            val resultTV: TextView = findViewById(R.id.textResult)

            var flag : String = "addition"
//create a spinner items (options)
            val spinnerVal : Spinner = findViewById(R.id.spSelect)
            var options = arrayOf("addition","subtraction","multiplication","division")
            spinnerVal.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options )
            button.setOnClickListener{ view ->
                var x: Int = edtxt1.text.toString().toInt();
                var y: Int = edtxt2.text.toString().toInt();

                resultTV.text = when (flag) {
                    "addition" -> add(x, y)
                    "subtraction" -> subtract(x, y)
                    "multiplication" -> multiply(x, y)
                    "division" -> divide(x, y)
                    else -> "Invalid operation"
                }
            }

            spinnerVal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    flag = options.get(p2) //p2 is the index of selected item
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }

    private fun add(a: Int, b: Int): String {
        return (a+b).toString();
    }

    private fun subtract(a:Int, b:Int): String{
        return (a-b).toString();
    }

    private fun multiply(a: Int, b: Int): String {
        return (a*b).toString();
    }

    private fun divide(a:Int, b:Int): String{
        if(b==0){
            return "Division by 0 not allowed";
        }
        else{
            return (a.toFloat()/b.toFloat()).toString();
        }
    }

}


