package com.example.bootcmarket

import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bootcmarket.databinding.ActivityDetailBinding
import com.example.model.Product

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupData()
        setupView()
        setupBackListener()
    }

    private fun setupData() {
        val intent = intent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("product", Product::class.java)?.let {
                product = it
            }
        } else {
            intent.getParcelableExtra<Product>("product")?.let {
                product = it
            }
        }
    }


    private fun setupView() {
        val formatter = DecimalFormat("#,###")

        binding.ivImage.setImageResource(product.img)
        binding.tvSeller.text = product.seller
        binding.tvLocation.text = product.location
        binding.tvName.text = product.name
        binding.tvContent.text = product.content
        binding.tvBottomPrice.text = formatter.format(product.price) + "원"
    }
    private fun setupBackListener(){
        binding.ivBack.setOnClickListener {
            this.finish()
        }

    }

}