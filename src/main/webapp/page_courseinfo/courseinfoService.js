angular.module('myApp.courseinfo')
	.factory('courseInfoService', ['$http', function ($http) {
		var courseInfoService = {};

		courseInfoService.getCourseInfo = function (courseName) {
			return $http.get('/training_controller/training_info/' + courseName)
				.success(function (data) {
					return data;
				})
				.error(function (err) {
					return err;
				})
		};

		courseInfoService.getEditedCourseInfo = function (courseName) {
			return $http.get('/training_controller/edited_training_info/' + courseName)
				.success(function (data) {
					return data;
				})
				.error(function (err) {
					return err;
				})
		};

		courseInfoService.getOmissions = function (courseName, lessonDate) {
			var lessonData = {
				trainingName: courseName,
				date: lessonDate
			};
			return $http.post('/omission_controller/get_omissions', lessonData)
				.success(function (result) {
					console.log(result);
					return result;
				})
				.error(function (err) {
					return err;
				})
		};

		courseInfoService.updateDates = function (courseName) {
			return $http.get('/training_controller/date_info/' + courseName)
				.success(function (result) {
					return result.data;
				})
				.error(function (err) {
					return err;
				})
		};

		courseInfoService.getParticipants = function(trainingName) {
			return $http.get('/training_controller/listeners/' + trainingName).then(function(data) {
				console.log("Got paarticipants successfully");
				return data.data;
			}, function(err) {
				console.log("Error getting participants: " + err.statusCode);
				return err;
			});
		};

		courseInfoService.addFiles = function(filesData, trainingName) {
			var fd = new FormData();
			fd.append('files', JSON.stringify(filesData));
			fd.append('trainingName', JSON.stringify(trainingName));

			return $http.post('/training_controller/add_files', fd, {
				headers: {
					'Content-Type': undefined
				},
				transformRequest: angular.identity
			}).then(function (results) {
				console.log('Files added successfully!');
				return results.data;
			});
		};

		courseInfoService.getFiles = function(trainingName) {
			return $http.get('/training_controller/files_info/' + trainingName).then(function(data) {
				console.log("Got files successfully");
				return data.data;
			}, function(err) {
				console.log("Error getting files: " + err.statusCode);
				return err;
			});
		};

		courseInfoService.leave = function (courseData) {
			return $http.post('/user_controller/leave_training', courseData)
				.then(function (data) {
					console.log("Leave Success");
					return data.data;
				}, function (err) {
					console.log("Leave error:" + err.statusCode);
					return err;
				})
		};

		courseInfoService.subscribe = function (courseData) {
			return $http.post('/user_controller/join_training', courseData)
				.then(
				function (data) {
					console.log("Sub success");
				},
				function (err) {
					if(err.statusCode == 502) {
						alert("Sorry, there are no places available for this course for now. You were added to the waiating list.");
					}
					console.log("Sub error:" + err.statusCode);
					return err;
				});
		};

		courseInfoService.getPromtText = function (data) {
			var promtText = "";
			if (data.subscriber === true) {
				promtText = "You are in!";
			} else {
				if (data.participantsNumber > data.listeners.length) {
					promtText = "You're Welcome!"
				} else {
					promtText = "I can add you to queue"
				}
			}
			return promtText;
		};

		courseInfoService.addLesson = function (courseName, newDate, newPlace) {
			var lessonData = {
				trainingName: courseName,
				lessonNumber: 0,
				newDate: newDate,
				newPlace: newPlace
			};
			return $http.post('/training_controller/add_date', lessonData).then(function (result) {
				console.log("Lesson added successfully");
				return result.data;
			}, function (err) {
				console.log("Add lesson error: " + err.statusCode);
				return err;
			});
		};

		courseInfoService.editLesson = function (lessonNumber, courseName, newDate, newPlace) {
			var lessonData = {
				trainingName: courseName,
				lessonNumber: lessonNumber,
				newDate: newDate,
				newPlace: newPlace
			};
			return $http.post('/training_controller/change_date', lessonData).then(function (result) {
				console.log("Lesson edited successfully");
				return result.data;
			}, function (err) {
				console.log("Edit lesson error: " + err.statusCode);
				return err;
			});
		};

		courseInfoService.deleteLesson = function (lessonNumber, courseName) {
			var lessonData = {
				trainingName: courseName,
				lessonNumber: lessonNumber,
				newDate: "",
				newPlace: ""
			};
			return $http.post('/training_controller/delete_date', lessonData).then(function (result) {
				console.log("Lesson deleted successfully");
				return result.data;
			}, function (err) {
				console.log("Delete lesson error: " + err.statusCode);
				return err;
			});
		};

		courseInfoService.addOmissions = function (omissionData) {
			return $http.post('/omission_controller/add_omissions', omissionData).then(function (result) {
				console.log("Omissions added successfully");
				return result.data;
			}, function (err) {
				console.log("Omissions adding error: " + err.statusCode);
				return err;
			});
		};

		courseInfoService.addParticipant = function(participantData) {
			return $http.post('/user_controller/insert_external_employee', participantData).then(function(result) {
				console.log("Participant added successfully");
				return result.data;
			}, function (err) {
				console.log("Participant adding error: " + err.statusCode);
				return err;
			});

		};

		return courseInfoService;
	}]);