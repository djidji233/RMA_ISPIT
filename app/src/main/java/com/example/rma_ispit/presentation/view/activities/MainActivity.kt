package com.example.rma_ispit.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.rma_ispit.R
import com.example.rma_ispit.presentation.contract.MainContract
import com.example.rma_ispit.presentation.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rma_ispit.presentation.view.recycler.adapter.WeatherAdapter
import com.example.rma_ispit.presentation.view.recycler.diff.WeatherDiffItemCallback
import com.example.rma_ispit.presentation.view.states.WeatherState
import timber.log.Timber

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val mainViewModel : MainContract.ViewModel by viewModel<MainViewModel>()
    private lateinit var weatherAdapter : WeatherAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){
        initRecycler()
        initObservers()
        initListeners()
    }

    private fun initRecycler() {
        recyclerList.layoutManager = LinearLayoutManager(this)
        weatherAdapter = WeatherAdapter(
            WeatherDiffItemCallback(),
            {
                //Toast.makeText(this, "Clicked on ${it.name}", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DetailsActivity::class.java)
                startActivity(intent)
            })
        recyclerList.adapter = weatherAdapter
    }

    private fun initListeners(){
        searchBtn.setOnClickListener{
//            val intent = Intent(this, DetailsActivity::class.java)
//            startActivity(intent)
            mainViewModel.getAll()
            mainViewModel.fetchAll(cityEt.text.toString(), daysEt.text.toString().toInt())
            //mainViewModel.getAll()

            val inputManager: InputMethodManager =getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)
        }
    }

    private fun initObservers(){
        mainViewModel.weatherState.observe(this, Observer {
            Timber.e(it.toString())
            renderState(it)
        })
        //mainViewModel.getAll()
        //mainViewModel.fetchAll(cityEt.text.toString(), daysEt.text.toString().toInt())
    }

    private fun renderState(state : WeatherState){
        when (state) {
            is WeatherState.Success -> {
                showLoadingState(false)
                weatherAdapter.submitList(state.weather)
            }
            is WeatherState.Error -> {
                showLoadingState(false)
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is WeatherState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(this, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is WeatherState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        cityEt.isVisible = !loading
        daysEt.isVisible = !loading
        searchBtn.isVisible = !loading
        recyclerList.isVisible = !loading
    }


}