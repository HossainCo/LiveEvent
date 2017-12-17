@file:Suppress("unused")

package ir.hossainco.liveevent

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer

inline fun <T> toObserver(crossinline observer: (T) -> Any): Observer<T> =
	Observer { it?.let { observer(it) } }


fun <T> liveEvent(clazz: Class<LiveEvent<T>>? = null) = LiveEventProperty(clazz)

fun <T> liveEvent(owner: LifecycleOwner? = null, clazz: Class<LiveEvent<T>>? = null, observer: Observer<T>) =
	liveEvent(clazz).apply {
		if (owner != null)
			liveEvent.observe(owner, observer)
		else
			liveEvent.observeForever(observer)
	}


inline fun <T> liveEvent(owner: LifecycleOwner? = null, clazz: Class<LiveEvent<T>>? = null, crossinline observer: (T) -> Unit) =
	liveEvent(owner, clazz, toObserver(observer))
