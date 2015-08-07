angular.module('myApp.courseinfo')
	.controller('CourseInfoController', ['$scope', '$routeParams', '$filter', '$location', 'courseInfoService', 'userService', 'categoriesLocal', '$modal', 'feedbacksService', function ($scope, $routeParams, $filter, $location, courseInfoService, userService, categoriesLocal, $modal, feedbacksService) {
		$scope.isAdmin = userService.isAdmin();

		$scope.courseName = $routeParams.coursename;
		$scope.subButtonText = "Subscribe";
		$scope.isContentLoaded = false;
		$scope.course = {};
		$scope.courseCategory = "";

		$scope.goTo = function (path) {
			console.log(path);
			$location.path(path);
		};

		$scope.getCategory = function(id) {
			categoriesLocal.getCategoryNameById(id).then(
				function(result) {
					console.log(result);
					$scope.courseCategory = result;
				}
			);
		}


		var courseInfoData1 = {
			login: userService.getUser().login,
			nameTraining: $routeParams.coursename
		};

		$scope.isSubscriber = false;
		$scope.subscribe = function () {
			var isSub = $scope.isSubscriber,
				promise;
			$scope.subscriptionLoading = true;
			if (isSub) {
				promise = courseInfoService.leave(courseInfoData1).then(function (data) {
					$scope.course.subscriber = false;
					$scope.course.promtText = courseInfoService.getPromtText($scope.course);
					$scope.isSubscriber = false;
				}).catch(function (err) {
				});
			} else {
				promise = courseInfoService.subscribe(courseInfoData1).then(function (data) {
					$scope.course.subscriber = true;
					$scope.course.promtText = courseInfoService.getPromtText($scope.course);
					$scope.isSubscriber = true;
				}).catch(function (err) {
				});
			}
			promise.finally(
				function () {
                 
					$scope.subscriptionLoading = false;
				}
			);
		}

		courseInfoService.getCourseInfo($routeParams.coursename).success(function (data) {
			//$scope.isSubscriber = false;
			console.log(JSON.stringify(data));
			$scope.courseName = $routeParams.coursename;


			data.courseImg = "assets/angular_bg1.png";
			data.promtText = courseInfoService.getPromtText(data);
			$scope.course = data;
			$scope.getCategory($scope.course.idCategory);

			$scope.isSubscriber = data.isSubscriber;
			$scope.isContentLoaded = true;

			feedbacksService.getTrainingFeedbacks($scope.courseName)
				.success(function (data) {
					$scope.course.feedbacks = data;
				})
		}).error(function (err) {
			alert("Can't Access training info");
		});


		$scope.leaveFeedback = function () {

			var feedbackModalInstance = $modal.open({
				animation: true,
				templateUrl: 'page_courseinfo/feedback.html',
				controller: 'LeaveFeedbackModalInstanceController',
				size: "lg",
				resolve: {
					courseinfo: function () {
						return $scope.course;
					}
				}
			});

			feedbackModalInstance.result.then(function (feedback) {
				feedback.feedbackerLogin = userService.getUser().login;
				feedbacksService.createTrainingFeedback(feedback);
			}, function () {
				//cancel feedback
			});

		}

		$scope.viewFeedback = function (feedback) {
			var feedbackModalInstance = $modal.open({
				animation: true,
				templateUrl: 'page_courseinfo/feedback.html',
				controller: 'ViewFeedbackModalInstanceController',
				size: "lg",
				resolve: {
					feedback: function () {
						return feedback;
					},
				}
			});

			feedbackModalInstance.result.then(function (feedback) {
				//
			}, function () {
				//cancel feedback
			});

		}

		$scope.editDatePlace = function (index) {
			var dateModalInstance = $modal.open({
				animation: true,
				templateUrl: "page_courseinfo/dateModal.html",
				controller: "EditDateModalInstanceController",
				size: "lg",
				resolve: {
					courseinfo: function () {
						return $scope.course;
					},
					index: function () {
						return index;
					}
				},
			});

			dateModalInstance.result.then(function (data) {
				$scope.course.dateTime[index] = $filter('date')(data, 'medium');
			}, function () {
			});
		};

		$scope.addLesson = function () {
			var addLessonModalInstance = $modal.open({
				animation: true,
				templateUrl: "page_courseinfo/addLessonModal.html",
				controller: "AddLessonModalInstanceController",
				size: "lg",
				resolve: {
					courseinfo: function () {
						return $scope.course;
					},
					index: function () {
						return $scope.course.dateTime.length;
					}
				}
			});
			addLessonModalInstance.result.then(function (data) {
				$scope.course.dateTime[$scope.course.dateTime.length] = $filter('date')(data, 'medium');
				;
			});
		};


		$scope.addParticipant = function() {
			var participantModalInstance = $modal.open({
				animation: true,
				templateUrl: "page_courseinfo/participantModal.html",
				controller: "ParticipantsModalInstanceController",
				size: "lg",
				resolve: {
					courseinfo: function () {
						return $scope.course;
					}
				},
			});

			participantModalInstance.result.then(function (data) {
			}, function () {
			});
		};

		$scope.manageOmissions = function (index) {
			var omissionsModalInstance = $modal.open({
				animation: true,
				templateUrl: "page_courseinfo/omissionsModal.html",
				controller: "OmissionsModalInstanceController",
				size: "lg",
				resolve: {
					courseinfo: function () {
						return $scope.course;
					},
					index: function () {
						return index;
					}
				},
			});

			omissionsModalInstance.result.then(function (data) {
			}, function () {
			});
		}
	}])


	.controller('LeaveFeedbackModalInstanceController', ['$scope', '$modalInstance', 'courseinfo', function ($scope, $modalInstance, courseinfo) {
		$scope.courseinfo = courseinfo;
		$scope.isView = false;
		$scope.feedback = {
			clear: true,
			interesting: true,
			newMaterial: true,
			recommendation: true,
			effective: "5",
			other: "",
			trainingName: courseinfo.name,
		};

		$scope.ok = function () {

			$modalInstance.close($scope.feedback);
		};

		$scope.cancel = function () {
			$modalInstance.dismiss('cancel');
		};
	}])

	.controller('ViewFeedbackModalInstanceController', ['$scope', '$modalInstance', 'feedback', function ($scope, $modalInstance, feedback) {

		$scope.feedback = feedback;
		$scope.isView = true;


		$scope.ok = function () {

			$modalInstance.close($scope.feedback);
		};

		$scope.cancel = function () {
			$modalInstance.dismiss('cancel');
		};
	}])

	.controller('AddLessonModalInstanceController', ['$scope', '$modalInstance', '$filter', 'courseInfoService', 'courseinfo', 'index', function ($scope, $modalInstance, $filter, courseInfoService, courseinfo, index) {
		$scope.courseinfo = courseinfo;
		$scope.index = index;

		$scope.newDate = "";
		$scope.newPlace = "";

		$scope.ok = function () {
			$scope.newDate = $filter('date')($scope.newDate, 'yyyy-MM-dd HH:mm');
			courseInfoService.addLesson($scope.courseinfo.name, $scope.newDate, $scope.newPlace).then(function (data) {
				courseInfoService.updateDates($scope.courseinfo.name).then(function (result) {
					$scope.courseinfo.dateTime = angular.copy(result.data.dateTimes);
					for (var i = 0; i < $scope.courseinfo.dateTime.length; i++) {
						$scope.courseinfo.dateTime[i] = $filter('date')($scope.courseinfo.dateTime[i], 'medium');
					}
					$scope.courseinfo.places = angular.copy(result.data.places);
				});
			});
			$modalInstance.close();
		};

		$scope.cancel = function () {
			$modalInstance.dismiss('cancel');
		};
	}])

	.controller('EditDateModalInstanceController', ['$scope', '$modalInstance', '$filter', 'courseInfoService', 'courseinfo', 'index', function ($scope, $modalInstance, $filter, courseInfoService, courseinfo, index) {
		$scope.courseinfo = courseinfo;
		$scope.index = index;

		$scope.deleteLesson = function() {
			courseInfoService.deleteLesson($scope.index + 1, $scope.courseinfo.name).then(function(data) {
				courseInfoService.updateDates($scope.courseinfo.name).then(
					function(result) {
						$scope.courseinfo.dateTime = angular.copy(result.data.dateTimes);
						for (var i = 0; i < $scope.courseinfo.dateTime.length; i++) {
							$scope.courseinfo.dateTime[i] = $filter('date')($scope.courseinfo.dateTime[i], 'medium');
						}
						$scope.courseinfo.places = angular.copy(result.data.places);
					});
			});
			$modalInstance.dismiss();
		};

		$scope.ok = function () {
			$scope.courseinfo.dateTime[$scope.index] = $filter('date')($scope.courseinfo.dateTime[$scope.index], 'yyyy-MM-dd HH:mm');
			courseInfoService.editLesson($scope.index + 1, $scope.courseinfo.name, $scope.courseinfo.dateTime[$scope.index], $scope.courseinfo.places[$scope.index]).then(function (data) {
				courseInfoService.updateDates($scope.courseinfo.name).then(function (result) {
					$scope.courseinfo.dateTime = angular.copy(result.data.dateTimes);
					for (var i = 0; i < $scope.courseinfo.dateTime.length; i++) {
						$scope.courseinfo.dateTime[i] = $filter('date')($scope.courseinfo.dateTime[i], 'medium');
					}
					$scope.courseinfo.places = angular.copy(result.data.places);
				});
			});
			$modalInstance.close($scope.courseinfo.dateTime[$scope.index], $scope.index);
		};

		$scope.cancel = function () {
			$modalInstance.dismiss('cancel');
		};
	}])

	.controller('OmissionsModalInstanceController', ['$scope', '$modalInstance', 'courseInfoService', 'courseinfo', 'index', function ($scope, $modalInstance, courseInfoService, courseinfo, index) {
		$scope.courseinfo = courseinfo;
		$scope.index = index;

		$scope.omissionData = [];

		$scope.existingOmissions = [];

		courseInfoService.getOmissions(courseinfo.name, courseinfo.dateTime[index]).then(
			function (result) {
				$scope.existingOmissions = angular.copy(result.data);
				console.log($scope.existingOmissions);
				for (var i = 0; i < courseinfo.listeners.length; i++) {
					var info = {
						trainingName: courseinfo.name,
						date: courseinfo.dateTime[index],
						userLogin: courseinfo.listeners[i].login,
						isOmission: ($scope.existingOmissions.length != 0 ? $scope.existingOmissions[i].omission : false),
						reason: ($scope.existingOmissions.length != 0 ? $scope.existingOmissions[i].reason : "")
					};
					$scope.omissionData.push(info);
				}
				console.log("omissions loaded successfully");
			}
		);


		$scope.selectAll = function () {
			for (var i = 0; i < courseinfo.listeners.length; i++) {
				$scope.omissionData[i].isOmission = true;
			}
		};

		$scope.ok = function () {
			console.log($scope.omissionData);
			courseInfoService.addOmissions($scope.omissionData);
			$modalInstance.close($scope.course, $scope.index);
		};

		$scope.cancel = function () {
			$modalInstance.dismiss('cancel');
		};
	}])

	.controller('ParticipantsModalInstanceController', ['$scope', '$modalInstance', 'courseInfoService', 'courseinfo', function ($scope, $modalInstance, courseInfoService, courseinfo) {

		$scope.participantInfo = {
			numberPhone: ""
		};

		$scope.ok = function () {
			console.log($scope.participantInfo);
			courseInfoService.addParticipant($scope.participantInfo).then(
				function(result) {

				});
			$modalInstance.close(courseinfo);
		};

		$scope.cancel = function () {
			$modalInstance.dismiss('cancel');
		};
	}]);