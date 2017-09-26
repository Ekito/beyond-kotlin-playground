package fr.ekito.weather.json.geocode

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import fr.ekito.weather.json.geocode.Northeast
import fr.ekito.weather.json.geocode.Southwest

class Viewport {

    /**
     * @return The northeast
     */
    /**
     * @param northeast The northeast
     */
    @SerializedName("northeast")
    @Expose
    var northeast: Northeast? = null
    /**
     * @return The southwest
     */
    /**
     * @param southwest The southwest
     */
    @SerializedName("southwest")
    @Expose
    var southwest: Southwest? = null

}
