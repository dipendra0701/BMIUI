package com.example.bmicalc_muinew

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuCompat
import com.example.bmicalc_muinew.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var isClear: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCal.setOnClickListener(this)
        if (isClear) {
            isClear = false
            binding.btnCal.setText("CALCULATE")

        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu,menu)
        MenuCompat.setGroupDividerEnabled(menu!!, true);//add horizontal divider

        return super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item1 ->{

                val intent = Intent(this,about_aap::class.java)

                startActivity(intent)
//              Toast.makeText(this,"About BMI", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.item2 ->{

                val intent = Intent(this, bmi_chat::class.java)

                //Toast.makeText(this," BMI Chart", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                return true
            }
            R.id.item3 ->{
                finish()
                System.exit(0)
                Toast.makeText(this,"Exit", Toast.LENGTH_SHORT).show()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
    override fun onClick(view: View) {
        when (view?.id) {
            R.id.btnCal -> {

                if (binding.edtHeight.text!!.isEmpty() && binding.edtWeight.text!!.isEmpty()) {
                    binding.edtHeight.requestFocus()
                    Toast.makeText(this, "Enter the height & Weight", Toast.LENGTH_SHORT).show()
                } else if (binding.edtWeight.text!!.isEmpty()) {
                    binding.edtWeight.requestFocus()
                    Toast.makeText(this, "Enter the weight", Toast.LENGTH_SHORT).show()
                } else if (binding.edtHeight.text!!.isEmpty()) {
                    binding.edtHeight.requestFocus()
                    Toast.makeText(this@MainActivity, "please enter height  ", Toast.LENGTH_LONG).show()
                }
                if (isClear) {
                    isClear = false
                    binding.btnCal.text = "Calculate"
                    binding.txtResult.setText("Result")
                    binding.txtMsg.setText("Message")
                    binding.edtWeight.text!!.clear()
                    binding.edtHeight.text!!.clear()
                    Toast.makeText(this, "Cleared", Toast.LENGTH_SHORT).show()
                } else {
                    //Toast.makeText(this@MainActivity, "hello", Toast.LENGTH_LONG).show()
                    // Check if the height EditText and Weight EditText are not empty
                    if (binding.edtHeight.text.toString()
                            .isNotEmpty() && binding.edtWeight.text.toString().isNotEmpty()
                    ) {
                        if (!isClear) {
                            // initialize the variable
                            isClear = true
                            binding.btnCal.setText("Clear")
                            val height = (binding.edtHeight.text.toString()).toDouble()
                            val weight = (binding.edtWeight.text.toString()).toDouble()
                            if (height == 0.0 || weight == 0.0) {
                                Toast.makeText(this, "Invalid height or weight ", Toast.LENGTH_SHORT).show()
                            } else {
                                val wt = (binding.edtWeight.text.toString()).toDouble()
                                val ht = (binding.edtHeight.text.toString()).toDouble()
                                val htnew = (ht / 100)
                                val bmi = wt / (htnew * htnew)
//                                binding.txtMsg.text = R.string.strBMI.toString()
                                binding.txtResult.text = " Your BMI is ${bmi.toInt()}"
                                // binding.BMIResult.text = "Your BMI is :-  ${BMI} "
                                // update the status text as per the bmi conditions
                                if (bmi > 25) {
                                    Toast.makeText(
                                        this@MainActivity,
                                        R.string.strOverweight,
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                    binding.txtMsg.text = resources.getString(R.string.strOverweight)
                                } else if (bmi < 18) {
                                    Toast.makeText(
                                        this@MainActivity,
                                        R.string.strUnderweight,
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                    binding.txtMsg.text = resources.getString(R.string.strUnderweight)
                                } else if (bmi > 18 && bmi < 25) {
                                    Toast.makeText(this@MainActivity, R.string.strHealthy, Toast.LENGTH_SHORT)
                                        .show()
                                    binding.txtMsg.text = resources.getString(R.string.strHealthy)
                                }
                                else {
                                    Toast.makeText(this@MainActivity, R.string.strinvalid, Toast.LENGTH_LONG).show()
                                    binding.txtMsg.text = resources.getString(R.string.strinvalid)

                                }
                            }
                        }

                    }


                }


            }

        }

    }

    override fun onResume() {
        super.onResume()
    }


}






