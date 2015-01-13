package com.ofg.twitter.controller

import static org.springframework.web.bind.annotation.RequestMethod.*
import groovy.util.logging.Slf4j

import javax.ws.rs.core.Response

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient
import com.ofg.twitter.config.Collaborators
import com.ofg.twitter.model.LoanApplication
import com.wordnik.swagger.annotations.Api
import com.wordnik.swagger.annotations.ApiOperation

@Slf4j
@RestController
@RequestMapping('/api')
@Api(value = "city", description = "Operations on cities")
class ApiController {

    @Autowired
    private ServiceRestClient serviceRestClient

    @RequestMapping(
    value = '/loanApplication',
    method = POST,
    consumes = "application/json")
    @ApiOperation(value = "Gets city name from the coordinates",
    notes = "The code calls openweather to get the city")
    apply(@RequestBody LoanApplication loanApplication) {
        loanApplication.loanId = System.nanoTime()
        log.info("${loanApplication}")

        serviceRestClient.forService(Collaborators.LOAN_APPLICATION)
                .post()
                .onUrl("api/LoanApplication")
                .body(loanApplication)

        return Response.Status.CREATED
    }
}
