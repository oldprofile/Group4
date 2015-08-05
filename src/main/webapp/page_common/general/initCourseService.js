angular.module('myApp')
	.factory('initCourseService', ['$http', 'categoriesLocal', 'userService', function ($http, categoriesLocal, userService) {
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
				scope.categoriesObj = data;
				scope.categoriesObj.shift();
			});
		};
	}]);