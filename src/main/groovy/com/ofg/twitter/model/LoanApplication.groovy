package com.ofg.twitter.model

import groovy.transform.ToString

@ToString
class LoanApplication {
    String loanId
    String firstName
    String lastName
    Integer age

    BigDecimal amount
}
