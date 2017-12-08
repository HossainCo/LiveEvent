package ir.hossainco.liveevent

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread

class MutabaleLiveEvent<T> : LiveEvent<T>() {
	@MainThread
	override fun observe(owner: LifecycleOwner, observer: Observer<T?>) {
		// Having more than one observer is obviously a per case basis, so just modify accordingly.
		if (hasObservers())
			throw Throwable("Only one observer at a time may subscribe to a SingleActionLiveData")

		super.observe(owner, Observer { data ->
			if (data == null) return@Observer
			observer.onChanged(data)
			value = null
		})
	}

	// Just a nicely named method that wraps setting the value
	@MainThread
	fun sendAction(data: T) {
		value = data
	}
}
