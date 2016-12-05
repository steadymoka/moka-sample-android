package com.moka.mokatoyapp._di.module

import com.moka.framework.widget.adapter.*
import com.moka.mokatoyapp._di.PerFragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    constructor() {
    }

    constructor(adapter: BaseAdapter<*, *>) {
        this.adapter = adapter as BaseAdapter<ItemData, RecyclerItemView<ItemData>>
    }

    private lateinit var adapter: BaseAdapter<ItemData, RecyclerItemView<ItemData>>

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