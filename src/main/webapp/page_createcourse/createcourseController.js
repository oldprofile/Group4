angular.module('myApp.createcourse').controller('CreateCourseController', [
	'$scope',
	'$filter',
	'createcourse',
	'initCourseService',
	'categoriesLocal',
	'$timeout',
	function ($scope, $filter, createcourse, initCourseService, categoriesLocal, $timeout) {
		$scope.isEdited = false;
		$scope.header = 'Create';
		$scope.disabled = 'courseForm.name.$dirty && courseForm.name.$invalid || courseForm.description.$dirty && courseForm.description.$invalid || courseForm.audience.$dirty && courseForm.audience.$invalid || courseForm.participantsNumber.$dirty && courseForm.participantsNumber.$invalid || courseForm.place.$dirty && courseForm.place.$invalid';
		$scope.disabled2 = 'courseForm.$invalid';

		$scope.categoryToggle = false;

		initCourseService($scope);

		$scope.courseInfo.pictureLink = "";

		$scope.saveData = function () {
			if($scope.courseInfo.isRepeating) {
				$scope.courseInfo.repeatOn = angular.copy($scope.temp.repeatOn);
				$scope.courseInfo.startsOn = $scope.temp.startsOn;
				$scope.courseInfo.startsOn = $filter('date')($scope.courseInfo.startsOn, 'yyyy-MM-dd HH:mm');
				$scope.courseInfo.lessonsNumber = $scope.temp.lessonsNumber;
				for (var i = 0; i < $scope.courseInfo.lessonsNumber; i++) {
					$scope.courseInfo.places.push($scope.temp.place);
				}
			}
			else {
				$scope.courseInfo.dateTime = angular.copy($scope.temp.tempDates); //TEMP
				for (var i = 0; i < $scope.courseInfo.dateTime.length; i++) {
					$scope.courseInfo.dateTime[i] = $filter('date')($scope.courseInfo.dateTime[i], 'yyyy-MM-dd HH:mm');
					$scope.courseInfo.places.push($scope.temp.place);
				}
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