package com.sundaydev.kakaoTest.util

import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.RelativeSizeSpan
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sundaydev.kakaoTest.data.Movie
import com.sundaydev.kakaoTest.network.URL_IMAGE
import java.text.NumberFormat

@BindingAdapter("visibleGone")
fun setVisibleGone(view: View, boolean: Boolean) {
    view.visibility = when (boolean) {
        true -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("loadPoster")
fun setLoadPoster(view: AppCompatImageView, data: Movie) {
    Glide.with(view.context).load(URL_IMAGE + data.poster_path).into(view)
}

@BindingAdapter("loadProfile")
fun setLoadProfile(view: AppCompatImageView, url: String) {
    Glide.with(view.context).load(URL_IMAGE + url).into(view)
}

@BindingAdapter("percentage")
fun setPercentage(view: AppCompatTextView, percentage: Int) {
    val spannableString = SpannableStringBuilder("$percentage%")
    spannableString.setSpan(RelativeSizeSpan(1.2f), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    view.text = spannableString
}

@BindingAdapter("number")
fun setNumber(textView: AppCompatTextView, number: Int) = with(textView) { text = NumberFormat.getInstance().format(number) }

@BindingAdapter("numberWithCount")
fun setNumberWithCount(textView: AppCompatTextView, number: Int) = with(textView) { text = "${NumberFormat.getInstance().format(number)}개" }

@BindingAdapter("reputationScore")
fun setReputationScore(textView: AppCompatTextView, number: Int) {
    val honour = when {
        number < 19 -> "풋내기"
        number in 20..299 -> "새니기"
        number in 300..1999 -> "구성원"
        number in 2000..3999 -> "엘리트"
        number in 4000..9999 -> "전문가"
        number in 10000..19999 -> "권위자"
        else -> "지배자"
    }
    textView.text = "$honour ${NumberFormat.getInstance().format(number)}"
}


@BindingAdapter("defaultItemDecorator")
fun setDefaultItemDecorator(recyclerView: RecyclerView, drawable: Drawable) {
    recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL).apply {
        setDrawable(drawable)
    })
}
