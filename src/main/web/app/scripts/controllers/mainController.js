'use strict';

/**
 * @ngdoc function
 * # MainCtrl
 */
angular.module('BootstrapApplication.controllers')
        .controller('MainCtrl', ['$scope', 'LoanApplicationService', function ($scope, LoanApplicationService) {
            $scope.awesomeThings = [
                'HTML5 Boilerplate',
                'AngularJS',
                'Karma'
            ];

            $scope.loanApplication = {
                firstName: 'testName',
                lastName: 'testLastName',
                age: 20,
                amount: 300
            };

            $scope.alerts = [];

            $scope.send = function() {
                LoanApplicationService
                    .send($scope.loanApplication)
                    .success(function() {
                        $scope.alerts = [{msg: 'Bingo!'}];
                    })
                    .error(function(reason) {
                        $scope.alerts = [{msg: reason}];
                    });
                // CityService
                //     .findCity($scope.coords)
                //     .success(function (data) {
                //         $scope.city = data;
                //     })
                //     .error(function(reason) {
                //         $scope.alerts = [{msg: reason}];
                //     });
            };

            $scope.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };
        }]);
