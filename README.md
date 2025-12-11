🏆 Android Take-Home Assignment: Sports Games

Jetpack offline-architecture application using Flow, Coroutines, Koin, and custom file-based caching.

* With the help of a fully customised disc cache (no Room, no SharedPreferences), this project creates a contemporary Android application that loads sports, competitions, and matches from a remote API and supports offline-first usage with zero-wait-time startup.

The user interface (UI) is fully implemented using Jetpack Compose, supports partial rendering (sections appear as data arrives), and appropriately manages missing or invalid API responses.

Features: 

* Offline-first design 

   * The application uses a custom JSON file-based cache to load data from disc instantly.
   * The user interface continues to display accessible content even if the API is unavailable (for example, /sports returning 500, or date/image failed).

* Custom caching (no shared preferences, no room)
   
   * Used the standard File API and Kotlin Serialization.
   * To avoid corruption, atomic writes using.tmp files.

* Offline-first design

   * The app uses a custom JSON file-based cache to load cached data from disc instantly.
   * The user interface still displays content that is available even if the API is unavailable (for example, /sports returning 500).
   * Sports, competitions, and matches can all use the cache.

* Modern Android architecture

 * MVVM + a clean architecture (layers for domain, data, and presentation).
 * For both asynchronous and reactive data loading, used Flow and Kotlin Coroutines.
 * Abstract data sources using a repository pattern (network + file cache).

* Jetpack Compose User Interface

  * Selectable sports are available in the sports tab bar.
  * Section for live matches.
  * The section for upcoming matches has tabs for today, tomorrow, the weekend, and the next seven days.
  * Coil + coil-svg supports SVG icons.
  * ViewModel's dynamic mapping allows items to adjust to content.

* Koin Dependency Injection

  * DI modules for ViewModel, network, repositories.

* Error-resistant user interface

  * UI elements render on their own (matches are shown even if the sports API fails, for example).
  * Enum-based match status, fallback values, and safe mappers.
  * Date extensions for parsing different types of date/time

* Instrumented Integration Test

  * Matches that are cached are always released first.
  * When the API is successful, remote matches are released second.
  * Following a successful network call, the local cache is updated.
  * The repository displays an error but continues to use cached data if the network call is unsuccessful.
 
🔮 Future Improvements

  * Improve Test including unit tests (UI mappers, Enum parsing (MatchStatus.fromString), error handling logic in repositories, Parsing robustness for malformed API responses)
  * UI Tests
  * Improve Loading & Error States
  * Improve String Handling
  * Improve Domain Modeling
  * Better Offline Strategy (Add cache expiration timestamps)
  * Add error Logging & Monitoring


*** Task	Description	Time = 11h ***
Architecture setup:	Packages, layers, Gradle config	0.5h
API + Retrofit + Models, interface, Retrofit instance	0.5h
Custom File Cache	Serialization, temp file swap, error recovery	2h
Repositories	Network + cache + flow merging	0.5h
Domain logic	MatchStatus enum, mapping helpers	0.5h
UI Models & Mappers	Mapping domain → UI (live & upcoming)	0.5h
ViewModel	Combine flows, filtering, UI state	2h
SVG Loading Setup	Coil + coil-svg + media loaders, Jetpack Compose UI	Screens, match cards, grid layout	3h
Bug fixing (Nested Scroll / Layout)	Fixing LazyColumn/Layout interactions	0.5h
Testing	Repository tests, 0.5h
README & cleanup	documentation + polish	0.5h

    
