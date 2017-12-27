package ir.hossainco.liveevent

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class LiveEventProperty<T>(clazz: Class<LiveEvent<T>>? = null) : ReadOnlyProperty<Any, LiveEvent<T>> {

	val liveEvent: LiveEvent<T> = when (clazz) {
		is SingleLiveEvent<*> -> SingleLiveEvent()
		is MutabaleLiveEvent<*> -> MutabaleLiveEvent()
	//is OneShotLiveEvent<*> -> OneShotLiveEvent()
		else -> SingleLiveEvent()
	}

	override operator fun getValue(thisRef: Any, property: KProperty<*>)
		= liveEvent
}
