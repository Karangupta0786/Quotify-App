package com.example.quotifyapplication

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context:Context):ViewModel() {
    private var quoteList:Array<Quote> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuotesFromAssets()
    }

    private fun loadQuotesFromAssets(): Array<Quote> {
        val inputStream = context.assets.open("quotes.json")
        val size:Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer,Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json,Array<Quote>::class.java)
    }

    fun getQuotes() = quoteList[index]

    fun nextQuote(): Quote {
        if (index<quoteList.size-1)
        return quoteList[++index]
        else {
            Toast.makeText(context, "it's last Quote!!", Toast.LENGTH_SHORT).show()
            return quoteList[index]
        }
    }

    fun prevQuote(): Quote {
        if (index>=1){
           return quoteList[--index]
        }
        else{
            Toast.makeText(context, "it's first Quote!!", Toast.LENGTH_SHORT).show()
            return quoteList[index]
        }
    }

}