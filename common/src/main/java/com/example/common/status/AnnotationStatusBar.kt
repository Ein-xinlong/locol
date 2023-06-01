package com.sumansoul.base.status

import androidx.annotation.IntDef

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2021/1/21
 * Time: 15:27
 */
const val TRANSPARENT = 1
const val TYPE_WHITE = 2
const val TYPE_BLACK = 3
const val TYPE_FULL_WHITE = 4
const val TYPE_WHITE_HIDEBAR = 5
const val TYPE_WHITE_TXT = 6
const val TYPE_WHITE_NO_FULL = 7

@IntDef(TRANSPARENT, TYPE_WHITE, TYPE_BLACK,TYPE_FULL_WHITE,TYPE_WHITE_HIDEBAR,TYPE_WHITE_NO_FULL)
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
annotation class AnnotationStatusBar


