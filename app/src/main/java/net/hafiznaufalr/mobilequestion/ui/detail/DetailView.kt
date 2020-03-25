package net.hafiznaufalr.mobilequestion.ui.detail

import net.hafiznaufalr.mobilequestion.model.Model.Movie

interface DetailView {
    fun onDataResponse(data: Movie)
    fun onDataFailure(throwable: Throwable)
}