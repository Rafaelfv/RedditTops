package com.rafaelfv.reddittops.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.rafaelfv.reddittops.R
import com.rafaelfv.reddittops.repository.model.Children
import com.rafaelfv.reddittops.utils.DetailTopCallback
import com.rafaelfv.reddittops.utils.KEY_CHILDREN
import com.rafaelfv.reddittops.utils.setImageFromUrl
import com.rafaelfv.reddittops.viewModel.ViewModelDetailTop
import kotlinx.android.synthetic.main.fragment_detail_top.*

class FragmentDetailTop : Fragment() {

    private var viewModel: ViewModelDetailTop? = null
    private lateinit var children: Children
    private lateinit var callback: DetailTopCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ViewModelDetailTop::class.java)

        if (arguments?.getParcelable<Children>(KEY_CHILDREN) != null) {
            children = arguments?.getParcelable<Children>(KEY_CHILDREN) as Children
            viewModel?.setChildren(children)
        } else {
            //children = viewModel?.getChildren() as Children
        }
        val a = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(children)
    }

    fun setupViews(children: Children) {
        image_top_detail.setImageFromUrl(children.data.thumbnail)
        title_top_detail.text = children.data.name
        content_top_detail.text = children.data.title
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as DetailTopCallback
    }


    override fun onDetach() {
        super.onDetach()
        callback.onDetachFragmentDetail()
    }
}