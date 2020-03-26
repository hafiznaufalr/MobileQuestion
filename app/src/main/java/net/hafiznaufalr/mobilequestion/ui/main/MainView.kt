package net.hafiznaufalr.mobilequestion.ui.main

import net.hafiznaufalr.mobilequestion.model.Model.MovieResponse

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun onDataResponse(data: MovieResponse)
    fun onDataFailure(throwable: Throwable)
}