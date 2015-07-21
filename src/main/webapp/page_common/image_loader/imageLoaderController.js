angular.module('myApp.imageLoaderApp')
    .controller("UploadController",['$scope', 'fileReader', function($scope, fileReader) {
        $scope.progress = 0;
        console.log(fileReader);
        $scope.getFile = function () {
            $scope.progress = 0;
            fileReader.readAsDataUrl($scope.file, $scope).then(function(result) {
                $scope.courseInfo.pictureLink = result;
            });
        };
        $scope.$on("fileProgress", function(e, progress) {
            $scope.progress = progress.loaded / progress.total;
        });
        
        $scope.nullProgress = function() {
            $scope.progress = 0;
        };
}]);