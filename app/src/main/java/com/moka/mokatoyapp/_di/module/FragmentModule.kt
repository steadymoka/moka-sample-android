package com.moka.mokatoyapp._di.module

import android.app.Activity
import com.moka.framework.widget.adapter.*
import com.moka.mokatoyapp._di.PerFragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    constructor(activity: Activity) {
        this.activity = activity
    }

    constructor(activity: Activity, adapter: BaseAdapter<*, *>) {
        this.activity = activity
        this.adapter = adapter as BaseAdapter<ItemData, RecyclerItemView<ItemData>>
    }

    private lateinit var activity: Activity
    private lateinit var adapter: BaseAdapter<ItemData, RecyclerItemView<ItemData>>

    @Provides
    @PerFragment
    fun activity() = activity

    @Provides
    @PerFragment
    fun adapterModel(): IAdapterModel {
        return adapter
    }

    @Provides
    @PerFragment
    fun adapterView(): IAdapterView {
        return adapter
    }

}