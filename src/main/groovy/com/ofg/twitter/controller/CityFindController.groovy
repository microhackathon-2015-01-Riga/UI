package com.ofg.twitter.controller

import com.ofg.twitter.controller.place.extractor.CityFinder
import com.wordnik.swagger.annotations.Api
import com.wordnik.swagger.annotations.ApiOperation
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.validation.constraints.NotNull

import static com.ofg.twitter.config.Versions.TWITTER_PLACES_ANALYZER_JSON_VERSION_1_CONTENT_TYPE
import static org.springframework.web.bind.annotation.RequestMethod.POST

@Slf4j
@RestController
@RequestMapping('/city')
@Api(value = "city", description = "Operations on cities")
class CityFindController {

    private final CityFinder cityFinder

    @Autowired CityFindController(CityFinder cityFinder) {
        this.cityFinder = cityFinder
    }

    @RequestMapping(
            value = '/{lat}/{lon}',
            method = POST,
            consumes = TWITTER_PLACES_ANALYZER_JSON_VERSION_1_CONTENT_TYPE,
            produces = TWITTER_PLACES_ANALYZER_JSON_VERSION_1_CONTENT_TYPE)
    @ApiOperation(value = "Gets city name from the coordinates",
            notes = "The code calls openweather to get the city")
    String findCity(@PathVariable("lat") @NotNull Double lat, @PathVariable("lon") @NotNull Double lon) {
        def city = cityFinder.findCityFromCoordinates(lat, lon)
        log.info("Lat: ${lat}, Lon: ${lon} = city: ${city}")
        return city.present ? city.get().name : null
    }
}
