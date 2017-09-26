package fr.ekito.weather.json.weather

import com.google.gson.annotations.Expose
import fr.ekito.weather.json.weather.Features

class Response {

    /**
     * @return The version
     */
    /**
     * @param version The version
     */
    @Expose
    var version: String? = null
    /**
     * @return The termsofService
     */
    /**
     * @param termsofService The termsofService
     */
    @Expose
    var termsofService: String? = null
    /**
     * @return The features
     */
    /**
     * @param features The features
     */
    @Expose
    var features: Features? = null

}
