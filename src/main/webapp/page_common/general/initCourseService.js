angular.module('myApp')
	.factory('initCourseService', ['$http', 'categoriesLocal', 'userService', 'coachesService', function ($http, categoriesLocal, userService, coachesService) {
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

			for(var i = 0; i <7; i++) {
				scope.temp.repeatOn.push(false);
			}

			scope.temp.pictureHolder = "";  //pictureLink or pictureData

			scope.isAdmin = userService.isAdmin();

			if(scope.isAdmin) {
				scope.externalCoaches = [];
				coachesService.getCoaches().then(function(data) {
					scope.externalCoaches = data;
					console.log(scope.externalCoaches);
				});
			}

			scope.courseInfo = {};
			scope.courseInfo.dateTime = [];
			scope.courseInfo.places = [];
			scope.courseInfo.additional = "";
			scope.courseInfo.isRepeating = false;

			if (!scope.isAdmin) {
				scope.courseInfo.isInternal = true;
			}

			scope.courseInfo.userLogin = userService.getUser().login;

			categoriesLocal.getCategories().then(function (data) {
				scope.categoriesObj = data.slice(1);
			});
		};
	}]);