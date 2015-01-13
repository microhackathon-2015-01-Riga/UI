'use strict';

angular.module('BootstrapApplication.services')
.factory('LoanApplicationService', ['$http', function($http) {
    var loanApplicationService = {};
    loanApplicationService.send = function(data) {
        console.log('Sending data!');
        return $http({
            url: '/api/loanApplication',
            dataType: 'json',
            method: 'POST',
            data: {
                'firstName': data.firstName,
                'lastName': data.lastName,
                'age': data.age,
                'amount': data.amount
            },
            headers: {
                'Content-Type': 'application/json'
            }
        });
    };

    return loanApplicationService;
}
]);
