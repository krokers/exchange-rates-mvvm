# (GitHub-Flavored) Markdown Editor


# ExchangeRates
Exemplary MVVM Android application based on databinding. It presents the daily exchange rates basing on USD.
<br>This application was inspired by following projects, articles and presentations:

* [Clean Architecture](https://github.com/android10/Android-CleanArchitecture) by Fernando Cejas,
* [Exploring RxJava 2 for Android](https://www.youtube.com/watch?v=htIXKI5gOQU) by Jake Wharton,
* [MCE^3 - Dagger 2](https://goo.gl/vlhY6x) by Gregory Kick,
* [A Journey Through MV Wonderland](https://goo.gl/25eGuQ) by Florina Muntenescu,
* [Android Architecture Blueprints](https://github.com/googlesamples/android-architecture)


## How to run

This application uses external service exposing the currency rates. If you want to run it yourself:
1. Open this page: https://openexchangerates.org/,
2. Register a free account
3. Request application ID
4. Add the application ID as a resource of this project, eg:
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="oer_app_id">abddefghijkl</string>
</resources>
```

## Architecture
As mentioned earlier, this application uses Clean Architecture approach. Layers are divided into separate gradle modules:
* **presentation** - Android module. Contains presentation logic based on MVVM. View models exposes data via databinding bindables.
* **domain** - pure java module with business use cases. 
* **data** - Android module implementing the repository pattern. Retrieves data from external service and from local database (in progress).

![https://drive.google.com/open?id=0B5xM_DaGK-J0LXdqQ0paWnl5akE](https://drive.google.com/open?id=0B5xM_DaGK-J0LXdqQ0paWnl5akE)


## Discussions

Refer to the issues section.


# License

	Copyright 2017 Rafal Swierkot
	
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
