package com.example.mulchapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.example.mulchapp.databinding.ActivityMainBinding
import com.example.mulchapp.databinding.ActivityOrderMulchBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.math.round

const val EXTRA_TYPE_KEY2 = "com.example.mulchapp.MainActivity.typekey"



class OrderMulchActivity : AppCompatActivity() {
    private val mulchCollection = mutableMapOf<String,Int>(
        "Premium Bark" to 56,
        "Special Blend" to 35,
        "Triple Ground" to 40,
        "Chocolate Dyed" to 38,
        "Red Dyed" to 38,
        "Black Dyed" to 38,
        "Play Mat" to 38,
        "Cedar" to 38
    )

    private val validZipCodes = mutableMapOf<Int, Int>(

        60540 to 25,
        60563 to 39,
        60564 to 35,
        60565 to 35,
        60187 to 40,
        60188 to 40,
        60189 to 35,
        60190 to 40

    )

    private lateinit var binding: ActivityOrderMulchBinding
    private lateinit var mulchType: String
    private var numberOfCubicYards:Int = 0
    private var pricePerCubicYards:Double = 0.0
    private var deliveryCharge: Double = 0.0
    private var salesTax: Double = 0.0
    private var totalPrice = 0.0
    private var mulchCost:Double = 0.0



    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOrderMulchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set mulchType
        val dataFromPreviousActivity = intent.getStringExtra(EXTRA_TYPE_KEY2)
        Log.d("SELECTED MULCH", "${dataFromPreviousActivity}")
        setMulchType(dataFromPreviousActivity.toString())
        setPrice(dataFromPreviousActivity.toString())
        binding.salesTaxVal.text = "$ ${salesTax}"
        binding.selectedMulch.text = mulchType
        binding.priceOfMulch.text = "$${mulchCollection[mulchType].toString()} per cubic yard"


        binding.nextButton2.setOnClickListener {
            if(binding.city.text.isEmpty() || binding.state.text.isEmpty() || binding.street.text.isEmpty()
                || binding.zipcode.text.isEmpty() || binding.email.text.isEmpty() || binding.phone.text.isEmpty()){
                Toast.makeText(this@OrderMulchActivity,"cannot pass data please try again", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this@OrderMulchActivity,"passing data", Toast.LENGTH_SHORT).show()
                val orderObject1 = OrderDetail(
                    mulchType,
                    totalPrice,
                    numberOfCubicYards,
                    pricePerCubicYards,
                    deliveryCharge,
                    salesTax,
                    binding.state.text.toString(),
                    binding.zipcode.text.toString(),
                    binding.street.text.toString(),
                    binding.city.text.toString(),
                    binding.email.text.toString(),
                    binding.phone.text.toString(),
                    mulchCost
                )
                val intent = Intent(this@OrderMulchActivity,OrderSummaryActivity::class.java)
                intent.putExtra("key2",orderObject1)
                startActivity(intent)
            }
        }





        //plus button increase
        binding.plusButton.setOnClickListener {
            numberOfCubicYards++
            binding.numberOfBags.setText(numberOfCubicYards.toString())
            calcTotal()
        }

        binding.zipcode.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable) {
                if(editable.toString().length == 5){
                    calcDeliveryCharge(editable.toString())
                    calcTotal()
                }
                else{
                    deliveryCharge = 0.0
                    binding.deliveryChargeVal.text = "$ ${deliveryCharge}"
                    calcTotal()
                }
            }
        })


        binding.numberOfBags.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                numberOfCubicYards = binding.numberOfBags.text.toString().toInt()
                calcTotal()
            }else{
                binding.plusButton.setOnClickListener {
                    numberOfCubicYards =binding.numberOfBags.text.toString().toInt()
                    numberOfCubicYards++
                    binding.numberOfBags.setText(numberOfCubicYards.toString())
                    calcTotal()
                }
                binding.subButton.setOnClickListener {
                    if(numberOfCubicYards != 0){
                        numberOfCubicYards =binding.numberOfBags.text.toString().toInt()
                        numberOfCubicYards--
                        binding.numberOfBags.setText(numberOfCubicYards.toString())
                        calcTotal()
                    }

                }
            }

        }
        //sub button decrease
        binding.subButton.setOnClickListener {
            if(numberOfCubicYards != 0){
                numberOfCubicYards--
                binding.numberOfBags.setText(numberOfCubicYards.toString())
                calcTotal()
            }

        }


    }
        fun calcDeliveryCharge(zipcode:String){
            val keyMatches = validZipCodes.keys.any{it == zipcode.toInt()}

            if(keyMatches){
                Snackbar.make(binding.root,"Delivery Available", Snackbar.LENGTH_SHORT ).show()
                deliveryCharge = validZipCodes[zipcode.toInt()]?.toDouble() ?: return
                binding.deliveryChargeVal.text = "$  ${deliveryCharge}"
            }
            else{
                Snackbar.make(binding.root,"Delivery Not Available", Snackbar.LENGTH_SHORT ).show()
                deliveryCharge = 0.0
                binding.deliveryChargeVal.text = "$  ${deliveryCharge}"
            }

        }

        fun setMulchType(typeMulch: String){
            mulchType = typeMulch
        }

        fun calcTotal(){
            var total: Double = 0.0
            val totalCubicYards:Double = numberOfCubicYards.toDouble() * pricePerCubicYards
            binding.mulchCostVal.text = "$ ${totalCubicYards}"
            mulchCost = totalCubicYards
            total =  totalCubicYards + salesTax + deliveryCharge
            salesTax = round(mulchCost * 0.07)
            binding.salesTaxVal.text = "$ ${salesTax}"
            binding.totalVal.text = "$ ${total.toString()}"
            totalPrice = total
        }

        fun setPrice(typeofMulch:String){
            pricePerCubicYards = mulchCollection[typeofMulch]?.toDouble() ?: return
        }
}