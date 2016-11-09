package com.moka.mokatoyapp.vp.addedittask


import android.os.Bundle
import com.moka.framework.base.BaseActivity
import com.moka.mokatoyapp.R


class AddEditTaskActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_toolbar)
        onSlideAnimationOnFinish()

        val addEditTaskFragment: AddEditTaskFragment = AddEditTaskFragment()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayout_container, addEditTaskFragment)
                .commit()
    }

}
