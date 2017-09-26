package fr.ekito.weather.json

import fr.ekito.weather.json.geocode.Geocode
import fr.ekito.weather.json.geocode.Location


fun Geocode.getLocation(): Location? = results.firstOrNull()?.geometry?.location