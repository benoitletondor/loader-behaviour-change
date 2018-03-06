package com.benoitletondor.loadertest

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.util.Log

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<MainActivity.Data> {

    private var data: Data? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportLoaderManager.initLoader(101, null, this)
    }

    override fun onStart() {
        super.onStart()

        if( data != null ) {
            // This was the case before 27.1.0
            Log.d("Loader", "Data is load when reaching onStart")
        } else {
            // This is the case after 27.1.0
            Log.w("Loader", "Data is NOT load when reaching onStart")
        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Data> {
        return DataLoader(this)
    }

    override fun onLoadFinished(loader: Loader<Data>, data: Data) {
        this.data = data
    }

    override fun onLoaderReset(loader: Loader<Data>) {

    }

    class Data

    class DataLoader(context: Context): Loader<Data>(context) {
        override fun onStartLoading() {
            super.onStartLoading()

            deliverResult(Data())
        }
    }
}
