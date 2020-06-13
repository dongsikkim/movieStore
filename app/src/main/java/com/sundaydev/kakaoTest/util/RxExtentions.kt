package com.sundaydev.kakaoTest.util

import android.util.Log.d
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.annotations.SchedulerSupport
import io.reactivex.schedulers.Schedulers
import io.reactivex.rxkotlin.subscribeBy as rxKotlinSubscribeBy

private val onErrorStub: (Throwable) -> Unit = { d("sss", "${it.message}") }
private val onNextStub: (Any) -> Unit = {}
private val onCompleteStub: () -> Unit = {}

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Maybe<T>.workOnSchedulerIo(): Maybe<T> =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Flowable<T>.workOnSchedulerIo(): Flowable<T> =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun Completable.workOnSchedulerIo(): Completable =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Single<T>.workOnSchedulerIo(): Single<T> =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Observable<T>.workOnSchedulerIo(): Observable<T> =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Single<T>.subscribeByCommon(onSuccess: (T) -> Unit = onNextStub, onError: (Throwable) -> Unit = onErrorStub) =
    rxKotlinSubscribeBy(onError, onSuccess)

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun Completable.subscribeByCommon(onComplete: () -> Unit = onCompleteStub, onError: (Throwable) -> Unit = onErrorStub) =
    rxKotlinSubscribeBy(onError, onComplete)

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Observable<T>.subscribeByCommon(
    onNext: (T) -> Unit = onNextStub,
    onComplete: () -> Unit = onCompleteStub,
    onError: (Throwable) -> Unit = onErrorStub
) = rxKotlinSubscribeBy(onError, onComplete, onNext)