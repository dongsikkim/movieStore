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
import com.sundaydev.kakaoTest.R
import com.sundaydev.kakaoTest.data.Genres
import com.sundaydev.kakaoTest.network.URL_IMAGE
import java.text.NumberFormat

@BindingAdapter("visibleGone")
fun setVisibleGone(view: View, boolean: Boolean) {
    view.visibility = when (boolean) {
        true -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("loadImage")
fun setImage(view: AppCompatImageView, url: String?) {
    url?.let { Glide.with(view.context).load(URL_IMAGE + it).error(R.drawable.ic_logo).into(view) }
}

@BindingAdapter("percentage")
fun setPercentage(view: AppCompatTextView, percentage: Int) {
    view.text = when (percentage) {
        in 1..100 -> {
            val spannableString = SpannableStringBuilder("$percentage%")
            spannableString.apply { setSpan(RelativeSizeSpan(1.2f), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) }
        }
        else -> view.context.getString(R.string.note_rate)
    }
}

@BindingAdapter("refresh")
fun setRefresh(layout : androidx.swiperefreshlayout.widget.SwipeRefreshLayout, isRefresh : Boolean) {
    layout.isRefreshing = isRefresh
}


@BindingAdapter("genres")
fun setGenres(view: AppCompatTextView, genreArray: List<Genres>?) {
    if (!genreArray.isNullOrEmpty()) {
        view.text = genreArray.joinToString { genres -> genres.name }
    }
}

@BindingAdapter("runtime")
fun setRuntime(view: AppCompatTextView, minute: Int) {
    val builder = StringBuilder()
    if (minute / 60 > 0) {
        builder.append(minute / 60).append("h ")
    }
    builder.append(minute % 60).append("m")
    view.text = builder.toString()
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
