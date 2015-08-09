angular.module('myApp')
	.factory('initCourseService', ['$http', 'categoriesLocal', 'userService', 'coachService', function ($http, categoriesLocal, userService, coachService) {
		return function (scope) {
			scope.categoriesObj = [];
			scope.types = [
				{
					name: 'Internal',
					isInternal: true
				},
				{
					name: 'External',
					isInternal: false
				}];
			console.log(scope.types);
			scope.languages = ['Russian', 'English'];

			scope.temp = {};
			scope.temp.tempDates = [];
			scope.temp.place = "";
			scope.temp.startsOn = "";
			scope.temp.repeatOn = [];
			scope.temp.coach = {};

			for(var i = 0; i <7; i++) {
				scope.temp.repeatOn.push(false);
			}

			scope.temp.pictureHolder = "";  //pictureLink or pictureData
			scope.temp.fileHolder = [];

			scope.isAdmin = userService.isAdmin();

			if(scope.isAdmin) {
				scope.externalCoaches = [];
				coachService.getCoaches().then(function(data) {
					scope.externalCoaches = data;
				});
			}

			scope.courseInfo = {};
			scope.courseInfo.dateTime = [];
			scope.courseInfo.places = [];
			scope.courseInfo.additional = "";
			scope.courseInfo.isRepeating = false;
			scope.courseInfo.files = [];
			scope.courseInfo.picture = {};

			scope.courseInfo.userLogin = userService.getUser().login;

			if (!scope.isAdmin) {
				scope.courseInfo.isInternal = true;
				scope.courseInfo.coachLogin = scope.courseInfo.userLogin;
			}

			categoriesLocal.getCategories().then(function (data) {
				scope.categoriesObj = data.slice(1);
			});
		};
	}]);