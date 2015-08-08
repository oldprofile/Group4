angular.module('myApp.createcourse')
	.factory('coachService', ['$http', function ($http) {
		var coachService = {};

		coachService.getCoaches = function () {
			return $http.get('http://localhost:8080/user_controller/select_all_users_excoach')
				.then(function(result) {
					console.log("Coaches loaded successfully");
					return result.data;
				});
		};

		coachService.addCoach = function(coachData) {
			return $http.post('http://localhost:8080/user_controller/insert_external_coach', coachData)
				.then(function(result) {
					console.log("Coach added successfully");
					return result.data;
				});
		};

		return coachService;
	}]);