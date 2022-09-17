package com.sundaydev.kakaoTest.util

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.core.view.*

fun Activity.hideSystemBars() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        // statusBar 까지 그려지게 설정
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView)
        val statusBarType: Int = WindowInsetsCompat.Type.statusBars()

        windowInsetsController?.let {
            it.show(statusBarType)
        }
    } else {
        // statusBar 까지 그려지게 설정
        val drawViewOnStatusBarFlag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION


        window.decorView.systemUiVisibility = drawViewOnStatusBarFlag

        // & 연산으로 부분집합 추출 (y가 x의 부분집합이라면 x & y = y 다)
        val hasNotFullScreenFlag =
            (window.attributes.flags and WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN
        // window에 [FLAG_FORCE_NOT_FULLSCREEN]가 설정된 경우만 해제한다.
        if (hasNotFullScreenFlag) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        }
    }

    val rootView: View = window.decorView.findViewById(android.R.id.content)
    ViewCompat.setOnApplyWindowInsetsListener(rootView) { view, windowInsets ->
        val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
        view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            bottomMargin = insets.bottom // 세로모드 일때 navi height 만큼 margin
            rightMargin = insets.right // 가로모드 일때 navi height 만큼 margin
        }

        WindowInsetsCompat.CONSUMED
    }

    setStatusBarTextColor(isBlack = false)
    setStatusBarBgColor(Color.TRANSPARENT)
    setNavigationBarBgColor(Color.BLACK)
}


private fun Activity.setStatusBarTextColor(isBlack: Boolean) {
    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = isBlack
}

private fun Activity.setStatusBarBgColor(@ColorInt color: Int) {
    window.statusBarColor = color
}

private fun Activity.setNavigationBarBgColor(@ColorInt color: Int) {
    window.navigationBarColor = color
}