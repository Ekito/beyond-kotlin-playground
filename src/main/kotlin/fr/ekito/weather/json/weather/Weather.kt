package fr.ekito.weather.json.weather

import com.google.gson.annotations.Expose
import fr.ekito.weather.json.weather.Forecast
import fr.ekito.weather.json.weather.Response

class Weather {

    /**
     * @return The response
     */
    /**
     * @param response The response
     */
    @Expose
    var response: Response? = null
    /**
     * @return The forecast
     */
    /**
     * @param forecast The forecast
     */
    @Expose
    var forecast: Forecast? = null

}
