package fr.ekito.weather.json.weather

import com.google.gson.annotations.Expose
import fr.ekito.weather.json.weather.Forecastday_

import java.util.ArrayList

class Simpleforecast {

    /**
     * @return The forecastday
     */
    /**
     * @param forecastday The forecastday
     */
    @Expose
    var forecastday: List<Forecastday_> = ArrayList()

}
