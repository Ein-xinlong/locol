package com.example.home.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common.MyApplication
import com.example.common.base.BaseActivity
import com.example.common.dialog.LoadingUtil
import com.example.common.dialog.LoadingUtil.createLoadingDialog

import com.example.common.router.RouterConfig
import com.example.home.R
import com.example.home.databinding.ActivityHomeBinding
import com.sumansoul.base.status.TRANSPARENT

@Route(path = RouterConfig.App.ACTIVITY_HOME)
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override fun statusBarMode(): Int {
        return TRANSPARENT
    }

    override fun initAdapter() {

    }

    override fun initData() {
        mDataBinding?.textView?.setOnClickListener{
            createLoadingDialog(this,"加载中。。。")
        }
    }

    override fun initView() {

    }

    override fun getLayout(): Int {
        return R.layout.activity_home
    }

}