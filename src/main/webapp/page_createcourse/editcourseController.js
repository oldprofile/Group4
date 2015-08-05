angular.module('myApp.createcourse')
.controller('EditCourseController', ['$scope', '$routeParams', '$filter', 'editcourse', 'courseInfoService', 'initCourseService', function($scope, $routeParams, $filter, editcourse, courseInfoService, initCourseService) {
    $scope.isEdited = true;

    if(!$routeParams.coursestate) {
        $scope.justEdit = true;
    }
    else {
        $scope.justEdit = false;
        $scope.isDraft = (($routeParams.coursestate == "Draft") ? true : false);
    }
    $scope.header = 'Edit';
    
    initCourseService($scope);

    if(!$scope.isDraft && !$scope.justEdit) {
        courseInfoService.getEditedCourseInfo($routeParams.coursename).success(function(data) {
            $scope.courseInfo = angular.copy(data);
           $scope.temp.tempDates = angular.copy($scope.courseInfo.dateTime);

            $scope.temp.place = (($scope.courseInfo.places != null) ? $scope.courseInfo.places[0] : "");

            $scope.temp.pictureHolder = $scope.courseInfo.pictureLink;
            console.log($scope.courseInfo);
        });
    }
    else {
        courseInfoService.getCourseInfo($routeParams.coursename).success(function(data) {
            $scope.courseInfo = angular.copy(data);
            $scope.temp.tempDates = angular.copy($scope.courseInfo.dateTime);

            $scope.temp.place = (($scope.courseInfo.places != null) ? $scope.courseInfo.places[0] : "");

            $scope.temp.pictureHolder = $scope.courseInfo.pictureLink;
            console.log($scope.courseInfo);
        });
    }
    
    $scope.editCourse = function() {
        console.log($scope.courseInfo);
        editcourse.editCourse($scope.courseInfo, $scope.isDraft, $scope.justEdit); //! ? some then()...?
    };
}]);