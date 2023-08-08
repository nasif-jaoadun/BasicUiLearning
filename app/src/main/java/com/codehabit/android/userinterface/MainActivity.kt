package com.codehabit.android.userinterface

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.codehabit.android.userinterface.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        addTextView(getString(R.string.hello))
        addTextView(getString(R.string.from))
        addTextView(getString(R.string.android))
        displayImageAssets("monster01.webp")

//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "This is the entered name:", Snackbar.LENGTH_LONG)
                .setAction("Action", { showToast() }).show()
        }

    }

    private fun displayImageAssets(fileName : String){
        assets.open(fileName).use {
            val drawable = Drawable.createFromStream(it, null)
            drawable?.let { it1 -> addImageView(it1) }
        }
    }

    private fun showToast() {
        Toast.makeText(this, "New Click", Toast.LENGTH_LONG).show()
    }

    private fun addTextView(label: String){
        val view = TextView(this)
        view.text = label
        view.textSize = 20f
        view.setTextColor(Color.parseColor("#ff0000"))
        binding.contentView.linearLayout.addView(view)
    }

    private fun addImageView(drawable : Drawable){
        val view = ImageView(this)
        view.setImageDrawable(drawable)
        binding.contentView.linearLayout.addView(view)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
}