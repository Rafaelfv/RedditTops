package com.rafaelfv.reddittops

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.rafaelfv.reddittops.repository.model.Children
import com.rafaelfv.reddittops.ui.fragments.FragmentDetailTop
import com.rafaelfv.reddittops.ui.fragments.FragmentListTop
import com.rafaelfv.reddittops.utils.*
import com.rafaelfv.reddittops.viewModel.BaseViewModel
import com.rafaelfv.reddittops.viewModel.ViewModelMainActivity

class MainActivity : AppCompatActivity(), ListTopCallback {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false
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
            tag = FRAGMENT_TAG_LIST_TOP
        )

        setupDetailFragmentIfApply()
    }

    private fun setupDetailFragmentIfApply() {
        if (twoPane) {
            this.supportFragmentManager.setFragment(
                fragment = viewModel.getFragmentDetailTop(),
                id = R.id.frameDetailTop, FRAGMENT_TAG_DETAIL_TOP
            )
        } else if (viewModel.currentDetailAdded) {
            refreshChildrenView()
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

    override fun onItemSelected(children: Children) {
        viewModel.setChildren(children)
        refreshChildrenView()
    }

    private fun refreshChildrenView() {
        if (twoPane) {
            viewModel.getChildren()?.let { viewModel.getFragmentDetailTop().setupViews(it) }
        } else {
            viewModel.currentDetailAdded = true
            val fragmentDetailTop = viewModel.getFragmentDetailTop()
            val bundle = Bundle()
            bundle.putParcelable(KEY_CHILDREN, viewModel.getChildren())
            fragmentDetailTop.arguments = bundle
            supportFragmentManager.addFragment(
                fragmentDetailTop,
                R.id.frameListTop,
                FRAGMENT_TAG_DETAIL_TOP
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {

            REQUEST_CODE_PERMISSION_WRITE_EXTERNAL -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    viewModel.getFragmentDetailTop().saveImageOnDevice()
                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.permission_storage_denied),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (viewModel.currentDetailAdded) {
            viewModel.currentDetailAdded = false
        }
    }

}