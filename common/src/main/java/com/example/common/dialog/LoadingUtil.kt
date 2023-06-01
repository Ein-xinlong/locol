package com.example.common.dialog

import android.app.Dialog
import android.content.Context
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.lottie.LottieAnimationView
import com.example.common.R

/**
 * @author anxinlong
 * @since 2023/6/1 6:04 PM
 * loading 加载框
 */
 object LoadingUtil {
    fun createLoadingDialog(context: Context, msg: String?): Dialog? {
        val inflater = LayoutInflater.from(context)
        val v: View = inflater.inflate(R.layout.dialog_loading, null) // 得到加载view
        val layout = v.findViewById(R.id.cons) as ConstraintLayout // 加载布局
        val tipTextView = v.findViewById(R.id.loading_text) as TextView // 提示文字
        val animation=v.findViewById(R.id.animation) as LottieAnimationView
        animation.playAnimation()
        tipTextView.text = msg // 设置加载信息
        val loadingDialog = Dialog(context) // 创建自定义样式dialog
        loadingDialog.setCancelable(false) // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false) // 点击加载框以外的区域
        loadingDialog.setContentView(
            layout, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        ) // 设置布局
        /**
         * 将显示Dialog的方法封装在这里面
         */
        val window: Window? = loadingDialog.getWindow()
        val lp: WindowManager.LayoutParams = window?.getAttributes()!!
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.setGravity(Gravity.CENTER)
        window?.setAttributes(lp)

        loadingDialog.show()
        return loadingDialog
    }

    /**
     * 关闭dialog
     * @param mDialogUtils
     */
    fun closeDialog(mDialogUtils: Dialog?) {
        if (mDialogUtils != null && mDialogUtils.isShowing()) {
            mDialogUtils.dismiss()
        }
    }
}