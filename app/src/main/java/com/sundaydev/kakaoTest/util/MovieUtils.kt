package com.sundaydev.kakaoTest.util

import android.animation.Animator
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatDelegate
import com.sundaydev.kakaoTest.R

open class LottieAnimListener : Animator.AnimatorListener {
    override fun onAnimationRepeat(animation: Animator?) {}

    override fun onAnimationEnd(animation: Animator?) {}

    override fun onAnimationCancel(animation: Animator?) {}

    override fun onAnimationStart(animation: Animator?) {}
}

fun dpToPx(dipValue: Float): Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, Resources.getSystem().displayMetrics)

fun View.hideKeyboard() =
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(windowToken, 0)

enum class DayNight(@IdRes val ids: Int) { LIGHT(R.id.light_mode), DARK(R.id.dark_mode), DEFAULT(R.id.system_mode) }

object ThemeSelector {
    fun applyTheme(dayNight: DayNight) {
        AppCompatDelegate.setDefaultNightMode(
            when (dayNight) {
                DayNight.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                DayNight.DARK -> AppCompatDelegate.MODE_NIGHT_YES
                DayNight.DEFAULT -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM else AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
            }
        )
    }
}
