package com.example.listunderthehood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams.*
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayout: LinearLayout
    private lateinit var laptops : ArrayList<Laptop>

    private lateinit var loadbutton:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        laptops = ArrayList()
        for (i in 0..6){
            laptops.add(
                Laptop(R.drawable.im_laptop1,
                "HP Pavilion Gaming 15",
                "HP Pavilion Gaming 15 2018 - Specs\n" +
                        "CPU. Intel Core i7-8750H 56.\n" +
                        "GPU. NVIDIA GeForce GTX 1060 Max-Q (3GB GDDR5) 73.\n" +
                        "Display. 15.6”, Full HD (1920 x 1080), IPS.\n" +
                        "HDD/SSD. 128GB PCIe SSD + 1TB HDD, 7200 rpm.\n" +
                        "RAM. 12GB DDR4, 2666 MHz.\n" +
                        "OS.\n" +
                        "Battery. 70Wh, 4-cell.\n" +
                        "Body Material.")
            )
        }

        initViews()

        if(savedInstanceState != null){
            val savedLaptops = savedInstanceState.getParcelableArrayList<Laptop>("laptops")
            savedLaptops!!.forEach {
                linearLayout.addView(addItem(it))
            }
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("laptops", laptops)
    }


    private fun initViews() {

        linearLayout = findViewById(R.id.container_root)
        loadbutton = findViewById(R.id.button_load)

        loadbutton.setOnClickListener {
            for (laptop in laptops) {
                linearLayout.addView(addItem(laptop))
            }
        }

    }



    private fun addItem(laptop: Laptop) :LinearLayout{
        val tempLinearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            val params = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            params.setMargins(50, 50, 50, 50)
            background = getDrawable(R.drawable.rounder_background)
            layoutParams = params

        }

        val imageView = ImageView(this).apply {
            val params = LinearLayout.LayoutParams(MATCH_PARENT, 350)
            params.setMargins(50, 50, 50, 50)
            setImageResource(laptop.image)
            layoutParams = params
        }

        val titleTextView = TextView(this).apply {
            text = laptop.title
            val params = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            params.setMargins(50, 50, 100, 50)
            textSize = 30f



        }

        val descriptionTextView = TextView(this).apply {
            text = laptop.title
            val params = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            params.setMargins(50, 50, 50, 50)
            text = laptop.description
            layoutParams = params
        }

        tempLinearLayout.addView(imageView)
        tempLinearLayout.addView(titleTextView)
        tempLinearLayout.addView(descriptionTextView)


        return tempLinearLayout
    }



}