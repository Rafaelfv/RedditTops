package com.rafaelfv.reddittops

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProviders
import com.rafaelfv.reddittops.ui.fragments.FragmentDetailTop
import com.rafaelfv.reddittops.ui.fragments.FragmentListTop
import com.rafaelfv.reddittops.utils.FRAGMENT_TAG_DETAIL_TOP
import com.rafaelfv.reddittops.utils.FRAGMENT_TAG_LIST_TOP
import com.rafaelfv.reddittops.utils.setFragment
import com.rafaelfv.reddittops.viewModel.BaseViewModel
import com.rafaelfv.reddittops.viewModel.ViewModelMainActivity

class MainActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false
    private var TAG = "MainActivity"
    private lateinit var viewModel: ViewModelMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        viewModel = ViewModelProviders.of(this).get(ViewModelMainActivity::class.java)
        twoPane = findViewById<FrameLayout>(R.id.frameDetailTop) != null
        this.supportFragmentManager.setFragment(
            fragment = viewModel.getFragmentListTop(),
            id = R.id.frameListTop,
            tag =FRAGMENT_TAG_LIST_TOP
        )

        setupDetailFragmentIfApply()
    }

    private fun setupDetailFragmentIfApply() {
        if (twoPane) {
            this.supportFragmentManager.setFragment(fragment = FragmentDetailTop(), id = R.id.frameDetailTop, FRAGMENT_TAG_DETAIL_TOP)
        }
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

}