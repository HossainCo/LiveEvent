# Android LiveEvent Library
[![License: MIT](https://img.shields.io/badge/License-MIT-brightgreen.svg)](https://opensource.org/licenses/MIT)
[![](https://jitpack.io/v/HossainCo/LiveEvent.svg)](https://jitpack.io/#HossainCo/LiveEvent)

[![pipeline status](https://gitlab.com/HossainCo/LiveEvent/badges/master/pipeline.svg)](https://gitlab.com/HossainCo/LiveEvent/commits/master)
[![coverage report](https://gitlab.com/HossainCo/LiveEvent/badges/master/coverage.svg)](https://gitlab.com/HossainCo/LiveEvent/commits/master)
[![Build Status](https://travis-ci.org/HossainCo/LiveEvent.svg?branch=master)](https://travis-ci.org/HossainCo/LiveEvent)
[![Coverage Status](https://coveralls.io/repos/github/HossainCo/LiveEvent/badge.svg?branch=master)](https://coveralls.io/github/HossainCo/LiveEvent?branch=master)

[![Github All Releases](https://img.shields.io/github/downloads/hossainco/livedata/total.svg?style=flat-square)]()

This is a simple library to handle UI events based on Android LiveData architecture library.

If `observer` is passed then: 
If `lifecycleOwner` is  not passed or `null` it use `observeForever(observer)` insteadof `observe(lifecycleOwner, observer)`.

## How to use
### Usage
```kotlin
// Simple create a SingleLiveEvent<String>
val event by liveEvent<String>

// Also register an default|first Observer
val event by liveEvent<String>(lifecycleOwner) { value ->
	Logger.e('test', value)
}

// lifecycleOwner parameter automaticly use in AppCompat activities !
// else if lifecycleOwner does not send then it observes for ever !!
val event by liveEvent { value: Boolean ->
	Logger.e('test', "check: $value")
}

// Use another LiveEvent types
val event by liveEvent<String>(clazz= OneShotLiveEvent::class)

// specify event class (default is SingleLiveEvent)
val event by liveEvent<String>(lifecycleOwner, OneShotLiveEvent::class) {
	// actions
}

// from google samples Handles only one observer
val event : LiveEvent<String> = SingleLiveEvent()
 
// from QAs Handles only one observer (not recommended)
val event : LiveEvent<String> = MutableLiveEvent()
 
// from QAs Handles many observers
val event : LiveEvent<String> = OneShotLiveEvent()
```

### Observe
```kotlin
event.observe(lifecycleOwner, Observer {
	// actions on event
})
```

## Gradle
```Groovy
// in root project
allprojects {
	repositories {
		// ...
		maven { url 'https://jitpack.io' }
	}
}
 
// in project
dependencies {
	compile 'com.github.HossainCo:LiveEvent:1.0.0-alpha4'
	// ...
}
```

## License
```text
MIT License
 
Copyright (c) 2017 HossainCo
 
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
 
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
 
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
