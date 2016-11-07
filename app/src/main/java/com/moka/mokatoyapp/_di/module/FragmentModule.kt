package com.moka.mokatoyapp._di.module

import android.app.Activity
import com.moka.framework.widget.adapter.BaseAdapter
import com.moka.framework.widget.adapter.ItemData
import com.moka.framework.widget.adapter.RecyclerItemView
import com.moka.mokatoyapp._di.PerFragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    constructor(activity: Activity) {
        this.activity = activity
    }

    constructor(activity: Activity, adapter: BaseAdapter<ItemData, RecyclerItemView<ItemData>>) {
        this.activity = activity
        this.adapter = adapter
    }

    private lateinit var activity: Activity
    private lateinit var adapter: BaseAdapter<ItemData, RecyclerItemView<ItemData>>

    @Provides
    @PerFragment
    fun activity() = activity

    @Provides
    @PerFragment
    fun adapter() = adapter

}