package fr.ekito.coroutines

import fr.ekito.weather.WeatherWS
import fr.ekito.weather.json.Components
import fr.ekito.weather.json.geocode.Location
import fr.ekito.weather.json.getLocation
import fr.ekito.weather.json.weather.Weather
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test

class AsyncWeatherTest {

    @Test
    fun testWeatherAsync() = runBlocking {
        val ws = Components.retrofitWS(Components.SERVER_URL)
        val address = "Paris, france"

        val location = asyncGeocode(address, ws).await()
        if (location != null) {
            println("Location is $location")
            val weather = asyncWeather(location, ws).await()
            println("weather is $weather")
        } else {
            println("No location :(")
        }
    }

    fun asyncWeather(g: Location, ws: WeatherWS): Deferred<Weather> = async(CommonPool) {
        ws.weather(g.lat, g.lng, "EN").execute().body()
    }

    fun asyncGeocode(location: String, ws: WeatherWS): Deferred<Location?> = async(CommonPool) {
        val body = ws.geocode(location).execute().body()
        body.getLocation()
    }
}