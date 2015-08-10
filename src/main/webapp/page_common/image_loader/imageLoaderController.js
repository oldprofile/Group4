angular.module('myApp.imageLoaderApp')
	.controller("UploadController", ['$scope', 'fileReader', function ($scope, fileReader) {
		$scope.progress = 0;
		$scope.getFile = function () {
			$scope.progress = 0;
			fileReader.readAsDataUrl($scope.file, $scope).then(function (result) {
				$scope.temp.pictureHolder = result;
				$scope.courseInfo.picture.data = result;
				$scope.courseInfo.picture.link = "";
				$scope.courseInfo.pictureLink = "";
				$scope.courseInfo.picture.name = ($scope.file.webkitRelativePath || $scope.file.name);
			});
		};
		$scope.$on("fileProgress", function (e, progress) {
			$scope.progress = progress.loaded / progress.total;
		});

		$scope.nullProgress = function () {
			$scope.progress = 0;
		};
	}])
	.controller("FileUploadController", ['$scope', 'fileReader', function ($scope, fileReader) {
		$scope.progress = 0;
		$scope.getFile = function () {
			$scope.progress = 0;
			fileReader.readAsDataUrl($scope.file, $scope).then(function (result) {
				$scope.courseInfo.files.push({
					data: result,
					link: "",
					name: ($scope.file.webkitRelativePath || $scope.file.name)
				});
			});
		};
		$scope.$on("fileProgress", function (e, progress) {
			$scope.progress = progress.loaded / progress.total;
		});

		$scope.nullProgress = function () {
			$scope.progress = 0;
		};
	}])

	.controller("AddFileUploadController", ['$scope', 'fileReader', function ($scope, fileReader) {
		$scope.progress = 0;
		$scope.getFile = function () {
			$scope.progress = 0;
			fileReader.readAsDataUrl($scope.file, $scope).then(function (result) {
				$scope.filesToUpload.push({
					data: result,
					link: "",
					name: ($scope.file.webkitRelativePath || $scope.file.name)
				});
			});
		};
		$scope.$on("fileProgress", function (e, progress) {
			$scope.progress = progress.loaded / progress.total;
		});

		$scope.nullProgress = function () {
			$scope.progress = 0;
		};
	}])

;