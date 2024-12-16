package com.example.countrylistapp.utils

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("app:onRefresh")
fun setOnRefreshListener(swipeRefreshLayout: SwipeRefreshLayout, refreshListener: () -> Unit) {
    swipeRefreshLayout.setOnRefreshListener {
        refreshListener()
        swipeRefreshLayout.isRefreshing = false
    }
}