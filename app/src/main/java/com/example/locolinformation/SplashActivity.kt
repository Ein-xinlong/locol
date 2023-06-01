package com.example.locolinformation

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.animation.AlphaAnimation
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common.base.BaseActivity
import com.example.common.router.ARouterUtils
import com.example.common.router.RouterConfig
import com.example.locolinformation.databinding.ActivitySplashBinding
import com.sumansoul.base.status.TRANSPARENT


@Route(path = RouterConfig.App.ACTIVITY_SPLASH)
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun initAdapter() {

    }
    override fun statusBarMode(): Int {
        return TRANSPARENT
    }
    override fun initData() {

        // 创建 View 动画，将启动页淡出
        val animationOut = AlphaAnimation(1.0f, 0.0f)
        animationOut.duration = 1000 // 设置动画持续时间
        // 创建 ObjectAnimator 动画，将应用程序名称向上移动
        val animationOn = ObjectAnimator.ofFloat(mDataBinding?.layoutSplash, "rotation", -270f, 0f)
        animationOn.duration = 3000 // 设置动画持续时间

        // 创建 ObjectAnimator 动画，将应用程序名称向上移动
        val animationUp = ObjectAnimator.ofFloat(mDataBinding?.textSplash, "translationY", 0f, -200f)
        animationUp.duration = 3000 // 设置动画持续时间

        // 创建一个 AnimatorSet 对象，并将淡出动画和向上移动动画添加到其中
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(animationOn, animationUp)

        // 监听动画完成事件，在动画完成后跳转到主界面
        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                ARouterUtils.navigation(RouterConfig.App.ACTIVITY_HOME)
                finish()
            }

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationRepeat(animation: Animator) {}

        })

        // 开始动画
        animatorSet.start()

    }

    override fun initView() {

    }

    override fun getLayout(): Int {
        return R.layout.activity_splash
    }


}

