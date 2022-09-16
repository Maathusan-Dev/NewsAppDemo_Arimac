package com.example.newsdemoarimac.callBack

import com.example.newsdemoarimac.models.Article

interface AdapterCallBack {
    fun onItemClick(item: Article)
}