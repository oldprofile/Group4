angular.module('myApp.createcourse')
	.factory('createcourse', ['$http', '$location', function ($http, $location) {

		var createcourse = {};

		createcourse.createCourse = function (courseData) {
			var fd = new FormData();
			fd.append('courseInfo', JSON.stringify(courseData));

			return $http.post('/training_controller/create_training', fd, {
				headers: {
					'Content-Type': undefined
				},
				transformRequest: angular.identity
			}).then(function (results) {
				console.log('Course created successfully!');
				$location.path("/mycourses");
				return results.data;
			});
		}
		return createcourse;
	}
	]);

angular.module('myApp.createcourse')
	.factory('editcourse', ['$http', '$location', '$filter', function ($http, $location, $filter) {

		var editcourse = {};

		editcourse.editCourse = function (courseData, isDraft, justEdit) {
			var fd = new FormData();
			if (isDraft) {
				console.log("Course just created");
				if (justEdit) {
					fd.append('courseInfo', JSON.stringify(courseData));
					return $http.post('/training_controller/edit_training', fd, {
						headers: {
							'Content-Type': undefined
						},
						transformRequest: angular.identity
					})
						.then(function (results) {
							console.log('Course edited successfully!');
							$location.path("/mycourses");
							return results.data;
						});
				}
				else {
					console.log("admin approves course!");
					for (var i = 0; i < courseData.dateTime.length; i++) {
						courseData.dateTime[i] = $filter('date')(courseData.dateTime[i], 'yyyy-MM-dd HH:mm');
					}
					fd.append('courseInfo', JSON.stringify(courseData));
					return $http.post('/training_controller/approve_create_training', fd, {
						headers: {
							'Content-Type': undefined
						},
						transformRequest: angular.identity
					})
						.then(function (results) {
							console.log('Course edited successfully!');
							$location.path("/mycourses");
							return results.data;
						});
				}

			}
			else {
				console.log("isnt draft");
				courseData.additional = "";
				courseData.dateTime = [""];
				courseData.places = [""];

				fd.append('courseInfo', JSON.stringify(courseData));

				if (justEdit) {
					console.log(courseData);
					fd.append('courseInfo', JSON.stringify(courseData));
					return $http.post('/training_controller/edit_training', fd, {
						headers: {
							'Content-Type': undefined
						},
						transformRequest: angular.identity
					})
						.then(function (results) {
							console.log('Course edited successfully!');
							$location.path("/mycourses");
							return results.data;
						});
				}
				else {
					return $http.post('/training_controller/approve_edit_training', fd, {
						headers: {
							'Content-Type': undefined
						},
						transformRequest: angular.identity
					})
						.then(function (results) {
							console.log('Course approved successfully!');
							$location.path("/mycourses");
							return results.data;
						});

				}
			}
		}
		return editcourse;
	}
	]);

