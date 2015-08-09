angular.module('myApp.createcourse')
	.controller('EditCourseController', ['$scope', '$routeParams', '$filter', 'editcourse', 'courseInfoService', 'initCourseService', 'setFieldsService', function ($scope, $routeParams, $filter, editcourse, courseInfoService, initCourseService, setFieldsService) {
		$scope.isEdited = true;

		if (!$routeParams.coursestate) {
			$scope.justEdit = true;
		}
		else {
			$scope.justEdit = false;
			$scope.isDraft = (($routeParams.coursestate == "Draft") ? true : false);
		}
		$scope.header = 'Edit';

		initCourseService($scope);

		if (!$scope.isDraft && !$scope.justEdit) {
			courseInfoService.getEditedCourseInfo($routeParams.coursename).success(function (data) {
				$scope.courseInfo = angular.copy(data);

				$scope.temp.coach = angular.copy($scope.courseInfo.coach);

				$scope.temp.tempDates = angular.copy($scope.courseInfo.dateTime);

				$scope.temp.place = (($scope.courseInfo.places != null) ? $scope.courseInfo.places[0] : "");

				$scope.temp.pictureHolder = $scope.courseInfo.picture.link;
				console.log($scope.courseInfo);
			});
		}
		else {
			courseInfoService.getCourseInfo($routeParams.coursename).success(function (data) {
				$scope.courseInfo = angular.copy(data);

				$scope.temp.coach = angular.copy($scope.courseInfo.coach);
				$scope.temp.tempDates = angular.copy($scope.courseInfo.dateTime);

				$scope.temp.place = (($scope.courseInfo.places != null) ? $scope.courseInfo.places[0] : "");

				$scope.temp.pictureHolder = $scope.courseInfo.picture.link;


				console.log($scope.courseInfo);
			});
		}

		$scope.editCourse = function () {

			//edited 06.08.2015
			for (var i = 0; i < $scope.temp.tempDates.length; i++) {
				$scope.courseInfo.dateTime[i] = $filter('date')($scope.temp.tempDates[i], 'yyyy-MM-dd HH:mm');
				$scope.courseInfo.places[i] = $scope.temp.place;
			}

			$scope.courseInfo.coach = $scope.temp.coach.login;

			console.log($scope.courseInfo);
			editcourse.editCourse($scope.courseInfo, $scope.isDraft, $scope.justEdit); //! ? some then()...?
		};

		$scope.setCategory = function (id) {
			setFieldsService.setCategory($scope, id);
		};

		$scope.setType = function (type) {
			setFieldsService.setType($scope, type);
		};

		$scope.setLanguage = function (lang) {
			setFieldsService.setLanguage($scope, lang);
		};

		$scope.setCoach = function(coach) {
			setFieldsService.setCoach($scope, coach);
		};

		$scope.setAdminAsCoach = function() {
			setFieldsService.setAdminAsCoach($scope);
		};

	}]);