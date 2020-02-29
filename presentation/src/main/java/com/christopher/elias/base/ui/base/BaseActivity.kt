package com.christopher.elias.base.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by Christopher Elias on 3/02/2020.
 * christopher.mike.96@gmail.com
 *
 * Peru Apps
 * Lima, Peru.
 **/
abstract class BaseActivity<T: ViewDataBinding, out V : BaseViewModel<*>> : AppCompatActivity() {

    private lateinit var viewDataBinding: T
    // private variable just for set the viewModel variable to the view (Data binding)
    private var _viewModel: V? = null

    abstract val getLayoutId : Int

    abstract val getViewModel : V

    abstract val getBindingVariable : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId)
        _viewModel = if (_viewModel == null) getViewModel else _viewModel
        viewDataBinding.setVariable(getBindingVariable, _viewModel)
        /**
         * use Fragment.viewLifecycleOwner for fragments
         */
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.executePendingBindings()
    }

}