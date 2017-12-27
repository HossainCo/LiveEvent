@file:Suppress("unused")

package ir.hossainco.liveevent

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import ir.hossainco.commonkotlin.android.livedata.Observe
import ir.hossainco.commonkotlin.android.livedata.toObserver

fun <T> liveEvent(clazz: Class<LiveEvent<T>>? = null)
	= LiveEventProperty(clazz)

fun <T> liveEvent(owner: LifecycleOwner? = null, clazz: Class<LiveEvent<T>>? = null, observer: Observer<T>) =
	liveEvent(clazz).apply {
		if (owner != null)
			liveEvent.observe(owner, observer)
		else
			liveEvent.observeForever(observer)
	}

inline fun <T> liveEvent(owner: LifecycleOwner? = null, clazz: Class<LiveEvent<T>>? = null, crossinline observe: Observe<Unit, T>)
	= liveEvent(owner, clazz, toObserver(observe))
