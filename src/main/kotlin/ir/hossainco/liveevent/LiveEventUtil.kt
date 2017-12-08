package ir.hossainco.liveevent

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer

internal fun <T> toObserver(observer: ((T?) -> Any)?) =
	if (observer == null) null else Observer<T> { observer(it) }

fun <T> liveEvent(owner: LifecycleOwner? = null, clazz: Class<LiveEvent<*>>? = null, observer: Observer<T>? = null) = LiveEventProperty<T>(clazz).apply {
	if (observer != null)
		if (owner != null)
			liveEvent.observe(owner, observer)
		else
			liveEvent.observeForever(observer)
}

fun <T> liveEvent(owner: LifecycleOwner? = null, clazz: Class<LiveEvent<*>>? = null, observer: ((T?) -> Any)? = null)
	= liveEvent(owner, clazz, toObserver(observer))
