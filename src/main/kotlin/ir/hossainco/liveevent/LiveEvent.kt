package ir.hossainco.liveevent

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread
import java.util.concurrent.Callable

abstract class LiveEvent<T> : MutableLiveData<T>(), Callable<Any>, Runnable, Observer<T> {
	@MainThread
	override fun call() {
		value = null
	}

	@MainThread
	fun call(t: T?) {
		value = t
	}

	@MainThread
	override fun run() {
		value = null
	}

	@MainThread
	override fun onChanged(t: T?) {
		value = t
	}
}
