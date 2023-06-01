package com.example.common.base

import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.common.R
import com.sumansoul.base.status.*
import java.io.File
import java.util.logging.Handler

abstract class BaseActivity<V> : FragmentActivity() {
    private var dialog: ProgressDialog? = null
    var mHandler: Handler? = null
    var mDataBinding: V? = null
    private var sUncaughtExceptionHandler: Thread.UncaughtExceptionHandler? = null
    private var file: File? = null
    var TAG: String = "BaseActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBar()
        performDataBinding()
        //沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            //设置App主题颜色
            window.setStatusBarColor(resources.getColor(R.color.transparent))
        }

//        //初始化布局，并适配
//        var view : View?=  null
//        view = View.inflate(this,getLayout(),null)
////        AutoUtils.setSize(this, false, 750, 1334)
////        AutoUtils.auto(view)
//        setContentView(view)

        //禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        //禁止键盘挤压布局
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)


        //初始化控件
        initView()
        //执行逻辑
        initData()
        //Adapter的内容
        initAdapter()


        sUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        //所有线程异常拦截，由于主线程的异常都被我们catch住了，所以下面的代码拦截到的都是子线程的异常
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            Log.e(TAG, t.toString() + e.toString())
        }
    }

    //显示加载圈
    fun showLoading() {
        if (dialog?.isShowing == true) {
            return
        }
        dialog = ProgressDialog(this)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        dialog?.setMessage("正在加载")
        dialog?.show()
    }

    //加载圈消失
    fun dismissLoading() {
        if (dialog!!.isShowing()) {
            dialog!!.dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (dialog != null) {
            dialog?.dismiss()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragmentManager = supportFragmentManager
        for (indext in 0 until fragmentManager.fragments.size) {
            val fragment = fragmentManager.fragments[indext] //找到第一层Fragment
            if (fragment == null)
                Log.w(
                    TAG,
                    "Activity result no fragment exists for index: 0x" + Integer.toHexString(
                        requestCode
                    )
                )
            else
                handleResult(fragment, requestCode, resultCode, data)
        }
    }

    //递归调用，对所有的子Fragment生效
    private fun handleResult(
        fragment: Fragment?,
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        fragment!!.onActivityResult(requestCode, resultCode, data)//调用每个Fragment的onActivityResult
        Log.e(TAG, "BaseFragmentActivity")
        val childFragment = fragment.getChildFragmentManager().fragments //找到第二层Fragment
        if (childFragment != null)
            for (f in childFragment)
                if (f != null) {
                    handleResult(f, requestCode, resultCode, data)
                }
        if (childFragment == null)
            Log.e(TAG, "BaseFragmentActivity1111")
    }

    abstract fun statusBarMode(): Int
    abstract fun initAdapter()
    abstract fun initData()
    abstract fun initView()
    abstract fun getLayout(): Int

    private fun performDataBinding() {
        mDataBinding = DataBindingUtil.setContentView(this, getLayout())
    }

    private fun setBar() {
        when (statusBarMode()) {
            TRANSPARENT -> {//透明
                if (Build.VERSION.SDK_INT >= 21) {
                    window.decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                    window.statusBarColor = Color.TRANSPARENT
                }
            }
            TYPE_WHITE_TXT -> {
                if (Build.VERSION.SDK_INT >= 21) {
                    window.decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                    window.statusBarColor = Color.WHITE
                }
            }
            TYPE_WHITE -> {

            }

            TYPE_WHITE_NO_FULL -> {

            }


            TYPE_BLACK -> {

            }

            TYPE_FULL_WHITE -> {

            }
            TYPE_WHITE_HIDEBAR -> {

            }

        }
    }

}
