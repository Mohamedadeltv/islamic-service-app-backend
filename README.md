# Islamic Services API Demo

Yo, welcome to the **Islamic Services API Demo**! This Spring Boot app hooks you up with prayer times, mosque locations, Quran data, and geolocation services by hitting external APIs. CORS is locked in for your frontend to vibe with it.

## Features
- **Prayer Times:** Grab Fajr, Dhuhr, Asr, Maghrib, and Isha.
- **Mosque Finder:** Find nearby mosques .
- **Quran Data:** Fetch Surah details or random Ayahs.
- **Geolocation:** Get city, country, and coordinates from an IP-based API.
- **CORS:** Frontend-friendly cross-origin access.

## Project Structure
Here’s the breakdown in a tree vibe:

```
com.example.demo
├── Prayer
│   ├── PrayerTimeResponse.java → DTO for prayer times API responses
│   ├── PrayerTimeConfig.java → Sets up api.aladhan.com URL & RestTemplate
│   ├── PrayerTimeController.java → REST endpoint for prayer times
│   └── PrayerTimeService.java → Logic for prayer times
├── Geolocation
│   ├── GeolocationService.java → Fetches city, country, and coords
│   ├── GeolocationConfig.java → Configs geolocation API URL
│   └── GeolocationResponse.java → DTO for geolocation responses
├── Mosque
│   ├── MosqueController.java → REST endpoint for nearest mosques
│   ├── MosqueConfiguration.java → Config for api.geoapify.com (URL, key, etc.)
│   ├── MosqueService.java → Logic for mosque data
│   └── Mosque.java → Model for mosque data
├── Quran
│   ├── QuranConfig.java → Config for api.al-quran.cloud (URL, version, etc.)
│   ├── QuranService.java → Fetches Surah details and random Ayahs
│   ├── QuranController.java → REST endpoints for Surah and random Ayah
│   └── Quran.java → Model for Quran data
└── Configuration
    └── CorsConfig.java → Handles CORS for frontend (localhost:3000)
```

## Prerequisites
- **Java 17+**: Get your JDK sorted.
- **Maven**: For builds and dependencies.
- **API Keys**: Geoapify key needed (sign up required).
- **Frontend**: React on `http://localhost:3000`.

## Setup
### Clone It:
```bash
git clone https://github.com/Mohamedadeltv/islamic-service-app-backend.git
```

### Set Properties:
Edit `src/main/resources/application.properties`. For `geoapify.api.key`, sign up at [Geoapify](https://www.geoapify.com/), generate a key, and plug it in:

```properties
# Prayer Times (via api.aladhan.com)
prayer.times.api.url=https://api.aladhan.com/v1/timingsByCity

# Geolocation (example API)
geolocation.api.url=https://ipapi.co/json/

# Mosque Finder (via api.geoapify.com)
geoapify.api.url=https://api.geoapify.com/v2/places
geoapify.api.key=your_geoapify_key_here  # Sign up at geoapify.com to get this
geoapify.api.categories=religion.mosque
geoapify.api.radius=5000

# Quran (via api.al-quran.cloud)
quran.api.url=https://api.al-quran.cloud/v1
quran.api.version=en.asad
randomAyah.api.url=https://random-quran-ayah.deta.dev/random-ayah
```

### Build It:
```bash
mvn clean install
```

### Run It:
```bash
mvn spring-boot:run
```
Hits `http://localhost:8080` by default.

## Usage
### Prayer Times
**Files:** `PrayerTimeController.java`, `PrayerTimeResponse.java`, `PrayerTimeConfig.java`

**Endpoint:** `GET /prayer-times?city={city}&country={country}&date={date}&method={method}`

| Parameter  | Type     | Example               | Required |
|-----------|---------|-----------------------|----------|
| city      | String  | "London"              | No       |
| country   | String  | "United Kingdom"      | No       |
| date      | String  | "2025-02-20"          | No       |
| method    | Integer | 1 (Muslim World League) | Yes      |

**Sample Response:**
```json
{
  "Fajr": "05:00",
  "Dhuhr": "12:30",
  "Asr": "15:45",
  "Maghrib": "18:15",
  "Isha": "19:30"
}
```

### Mosque Finder
**Files:** `MosqueController.java`, `MosqueConfiguration.java`

**Endpoint:** `GET /nearest-mosque`

**Sample Response:**
```json
[
  {
    "name": "جامع المرسى أبو العباس",
    "district": "Al Sayala West",
    "street": "Al Sayed Omar Karim Street"
  },
  {
    "name": "جامع البوصيرى",
    "district": "Al Sayala West",
    "street": "Al Sayed Mohamed Karim Street"
  }
]
```

### Quran Data
**Endpoints:**

- `GET /surah?num={num}` - Fetches Surah details.
- `GET /random-ayah` - Fetches a random Ayah.

**Sample Response:**
```json
{
  "name": "سُورَةُ ٱلْفَاتِحَةِ",
  "revelationType": "Meccan",
  "text": "﻿بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ۞الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ۞الرَّحْمَٰنِ الرَّحِيمِ۞مَالِكِ يَوْمِ الدِّينِ۞إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ۞اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ۞صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ۞"
}
```

## CORS Config
**File:** `CorsConfig.java`

Allows your frontend (e.g., React at `http://localhost:3000`) to hit all endpoints (`/**`) with `GET`, `POST`, `PUT`, `DELETE`.

## Dependencies
**Required in `pom.xml`:**
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

## Contributing
- Fork it.
- Branch it: `git checkout -b feature/your-thing`.
- Commit it: `git commit -m "Added my thing"`.
- Push it: `git push origin feature/your-thing`.
- PR it.

## License
MIT License—check `LICENSE`.

## Contact
Got ideas or issues? Open a ticket or email **[mohamedadelsm@outlook.com]**.

