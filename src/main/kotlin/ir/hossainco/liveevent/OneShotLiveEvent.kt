//package ir.hossainco.liveevent
//
//import android.arch.lifecycle.LifecycleOwner
//import android.arch.lifecycle.Observer
//import android.support.annotation.MainThread
//import java.util.concurrent.ConcurrentHashMap
//import java.util.concurrent.atomic.AtomicBoolean
//
//class OneShotLiveEvent<T> : LiveEvent<T>() {
//	private val pendingObservers = ConcurrentHashMap<Observer<T>, Pair<Observer<T>, AtomicBoolean>>()
//
//	@MainThread
//	override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
//		val interceptor = object : Observer<T> {
//			override fun onChanged(t: T?) {
//				var observerToTrigger: Observer<T>? = null
//				synchronized(pendingObservers) {
//					if (pendingObservers.containsKey(this) && pendingObservers[this]!!.second.compareAndSet(true, false)) {
//						observerToTrigger = pendingObservers[this]!!.first
//					}
//				}
//				observerToTrigger?.onChanged(t)
//			}
//		}
//
//		synchronized(pendingObservers) {
//			pendingObservers.put(interceptor, Pair(observer, AtomicBoolean(false)))
//		}
//
//		// Observe the internal MutableLiveData
//		super.observe(owner, interceptor)
//	}
//
//	override fun removeObserver(observer: Observer<T>) {
//		super.removeObserver(observer)
//		synchronized(pendingObservers) {
//			for (entry in pendingObservers.entries) {
//				if (observer === entry.key || observer === entry.value.first) {
//					pendingObservers.remove(entry.key)
//					break
//				}
//			}
//		}
//	}
//
//	@MainThread
//	override fun setValue(t: T?) {
//		synchronized(pendingObservers) {
//			for (pending in pendingObservers.values) {
//				pending.second.set(true)
//			}
//		}
//		super.setValue(t)
//	}
//}
