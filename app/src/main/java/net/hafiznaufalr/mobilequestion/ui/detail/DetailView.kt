package net.hafiznaufalr.mobilequestion.ui.detail

import net.hafiznaufalr.mobilequestion.model.Model.Detail

interface DetailView {
    fun onDataResponse(data: Detail)
    fun onDataFailure(throwable: Throwable)
}