package com.example.mulchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mulchapp.databinding.ActivityOrderSummaryBinding

@Suppress("DEPRECATION")
class OrderSummaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderSummaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOrderSummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val orderDetail = intent.getParcelableExtra<OrderDetail>("key2")

        binding.mulchType.text = orderDetail?.mulchType.toString()
        binding.numberOfCubicYards.text = " - ${orderDetail?.numberOfCubicYards} Cubic Yards"
        binding.pricePerCubicYards.text = "$ ${orderDetail?.pricePerCubicYard} per Cubic Yards"
        binding.description.text = "delivering ${orderDetail?.numberOfCubicYards} Cubic Yards to"

        binding.street.text = "${orderDetail?.street}"
        binding.stateAndCity.text ="${orderDetail?.city} , ${orderDetail?.state}"
        binding.zipCode.text =  "${orderDetail?.zipCode}"
        binding.email.text ="${orderDetail?.email}"
        binding.phoneNumber.text = "${orderDetail?.phone}"


        binding.mulchCostVal.text = "$ ${orderDetail?.mulchCost}"
        binding.deliveryChargeVal.text = "$ ${orderDetail?.deliveryCharge}"
        binding.totalVal.text = "$ ${orderDetail?.totalPrice}"
        binding.salesTaxVal.text = "$ ${orderDetail?.salesTax}"

        binding.placeOrderButton.setOnClickListener {
            Toast.makeText(this@OrderSummaryActivity,"Order has been place", Toast.LENGTH_SHORT).show()
        }









    }
}