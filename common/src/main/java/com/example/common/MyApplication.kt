package com.example.common

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

class MyApplication : Application() {
    var myApplication:MyApplication?=null
    override fun onCreate() {
        super.onCreate()
        myApplication=this
        //路由初始化
        if (BuildConfig.DEBUG) {
            //打印日志
            ARouter.openLog()
            //开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }
}