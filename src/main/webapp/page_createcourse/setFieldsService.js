angular.module('myApp')
	.factory('setFieldsService', ['userService', function (userService) {
		var fieldsService = {};

		fieldsService.setCategory = function (scope, id) {
			scope.courseInfo.idCategory = id;
		};

		fieldsService.setType = function (scope, type) {
			scope.courseInfo.isInternal = type;
		};

		fieldsService.setLanguage = function (scope, lang) {
			scope.courseInfo.language = lang;
		};

		fieldsService.setCoach = function (scope, coach) {
			scope.temp.coach = coach;
			scope.courseInfo.coachLogin = coach.login;
		};

		fieldsService.setAdminAsCoach = function (scope) {
			scope.temp.coach.name = 'me';
			scope.courseInfo.coachLogin = userService.getUser().login;
		}

		return fieldsService;
	}]);