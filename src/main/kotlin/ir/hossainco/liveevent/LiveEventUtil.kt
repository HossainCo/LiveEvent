package ir.hossainco.liveevent

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer

private fun <T> toObserver(observer: (T) -> Any): Observer<T> =
	Observer { it?.let(observer) }

fun <T> liveEvent(clazz: Class<LiveEvent<T>>? = null) = LiveEventProperty(clazz)

fun <T> liveEvent(owner: LifecycleOwner? = null, clazz: Class<LiveEvent<T>>? = null, observer: Observer<T>) =
	liveEvent(clazz).apply {
		if (owner != null)
			liveEvent.observe(owner, observer)
		else
			liveEvent.observeForever(observer)
	}

fun <T> liveEvent(owner: LifecycleOwner? = null, clazz: Class<LiveEvent<T>>? = null, observer: (T) -> Any) =
	liveEvent(owner, clazz, toObserver(observer))
