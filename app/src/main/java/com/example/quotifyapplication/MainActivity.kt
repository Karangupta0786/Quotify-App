package com.example.quotifyapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private val quoteText:TextView
        get() = findViewById(R.id.txt_quote)
    private val quoteAuther:TextView
        get() = findViewById(R.id.quote_auther)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this,MainViewModelFactory(application))[MainViewModel::class.java]

        setQuote(viewModel.getQuotes())




    }
    fun setQuote(quote: Quote){
        quoteText.text = quote.text
        quoteAuther.text = quote.author
    }

    fun onPrevious(view: View) {
        setQuote(viewModel.prevQuote())
    }
    fun onNext(view: View) {
        setQuote(viewModel.nextQuote())
    }
    fun onShare(view: View) {
        //define intent
        val intent = Intent()
        // set the action of intent
        intent.action = Intent.ACTION_SEND
        // set the type of intent
        intent.setType("text/plain")
        // use putExtra with 2 arguments
        intent.putExtra(Intent.EXTRA_TEXT,viewModel.getQuotes().text)
        // then start the activity
        startActivity(intent)
    }


}