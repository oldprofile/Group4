angular.module('myApp.createcourse').controller('CreateCourseController', [
	'$scope',
	'$filter',
	'createcourse',
	'initCourseService',
	'$timeout',
	function ($scope, $filter, createcourse, initCourseService, $timeout) {
		$scope.isEdited = false;
		$scope.header = 'Create';
		$scope.disabled = 'courseForm.name.$dirty && courseForm.name.$invalid || courseForm.description.$dirty && courseForm.description.$invalid || courseForm.audience.$dirty && courseForm.audience.$invalid || courseForm.participantsNumber.$dirty && courseForm.participantsNumber.$invalid || courseForm.place.$dirty && courseForm.place.$invalid || courseForm.$invalid';
		$scope.disabled2 = 'courseForm.$invalid';

		$scope.categoryToggle = false;

		initCourseService($scope);

		$scope.courseInfo.pictureLink = "";

		$scope.saveData = function () {
			$scope.courseInfo.dateTime = angular.copy($scope.temp.tempDates); //TEMP
			for (var i = 0; i < $scope.courseInfo.dateTime.length; i++) {
				$scope.courseInfo.dateTime[i] = $filter('date')($scope.courseInfo.dateTime[i], 'yyyy-MM-dd HH:mm');
				$scope.courseInfo.places.push($scope.temp.place);
			}
			console.log($scope.courseInfo);
			createcourse.createCourse($scope.courseInfo); //! ? some then() with alert...?
		};

		$scope.setCategory = function (id) {
			$scope.courseInfo.idCategory = id;
		};

		$scope.setType = function (type) {
			$scope.courseInfo.isInternal = type;
		};

		$scope.setLanguage = function(lang) {
			$scope.courseInfo.language = lang;
		}

	}]);