package ir.hossainco.liveevent

import kotlin.reflect.KProperty

class LiveEventProperty<T>(clazz: Class<LiveEvent<*>>? = null) {
	val liveEvent: LiveEvent<T> = when (clazz) {
		is SingleLiveEvent<*> -> SingleLiveEvent()
		is MutabaleLiveEvent<*> -> MutabaleLiveEvent()
	//is OneShotLiveEvent<*> -> OneShotLiveEvent()
		else -> SingleLiveEvent()
	}

	operator fun getValue(thisRef: Any?, property: KProperty<*>) = liveEvent
}
