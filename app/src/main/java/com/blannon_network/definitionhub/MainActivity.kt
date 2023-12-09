package com.blannon_network.definitionhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.blannon_network.MeaningAdapter
import com.blannon_network.Respond.WordResult
import com.blannon_network.RetrofitInstance
import com.blannon_network.definitionhub.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: MeaningAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.searchBtn.setOnClickListener{
            val word = binding.SearchInput.text.toString()
            getMeaning(word)
        }
        adapter = MeaningAdapter(emptyList())
        binding.meaningRv.layoutManager = LinearLayoutManager(this)
        binding.meaningRv.adapter = adapter
    }

    private fun  getMeaning(word : String){
        setInProgress(true)
        GlobalScope.launch {

            try {
                val response =    RetrofitInstance.definitionHubApi.getMeaning(word)
                if (response.body()==null){
                    throw (Exception())
                }
                runOnUiThread{
                    setInProgress(false)
                    response.body()?.first()?.let {
                        setUI(it)
                    }
                }
            } catch (e: Exception){
                runOnUiThread{
                    setInProgress(false)
                    Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }
    private fun setUI(response: WordResult){
       binding.wordTv.text = response.word
        binding.phoneticTv.text = response.phonetic
        adapter.updateNewData(response.meanings)
    }

    private fun setInProgress(inProgress: Boolean){
        if (inProgress){
            binding.searchBtn.visibility = View.INVISIBLE
            binding.progressbar.visibility = View.VISIBLE
        } else
            binding.searchBtn.visibility = View.VISIBLE
        binding.progressbar.visibility = View.INVISIBLE
    }
}