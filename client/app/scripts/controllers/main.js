'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')

  .controller('MainCtrl', function ($scope,$http) {

    $scope.host = location.host;

    $scope.longUrl = "";
    $scope.custom = "";
    $scope.btn = "GO!";
    
  	$scope.takeShort = function() {
  		
      var res = $http.post("/rest/go-shorty", {longUrl:$scope.longUrl, custom:$scope.custom});
  		
      res.success(function(data) {
  			console.log('Inserted!');
  			$scope.rispostaJson = data;
  			$scope.shortUrl = "http://" + $scope.host + "/" + $scope.rispostaJson.shortUrl;
        $scope.err = "";
  		});

      res.error(function(data) {
        $scope.rispostaJson = data || "request failed";
        $scope.err = $scope.rispostaJson.err ;
        $scope.shortUrl = "";
        });
    };

});
