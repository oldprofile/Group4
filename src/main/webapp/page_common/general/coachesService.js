angular.module('myApp')
	.factory('coachesService', ['$http', 'userService', function ($http, userService) {
		var coachesService = {};

		coachesService.getCoaches = function () {
			return $http.get('http://localhost:8080/user_controller/select_all_users_excoach')
				.then(function(result) {
					console.log(result);
					return result;
				});
		}

		return coachesService;
	}]);