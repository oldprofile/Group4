angular.module('myApp.createcourse')
.controller('EditCourseController', ['$scope', '$routeParams', 'createcourse', 'courseInfoService', 'initCourseService', function($scope, $routeParams, createcourse, courseInfoService, initCourseService) {
    $scope.isEdited = true;
    $scope.header = 'Edit';
    
    initCourseService($scope);
    
    courseInfoService.getCourseInfo($routeParams.coursename).success(function(data) {
        $scope.courseInfo = angular.copy(data);
        $scope.temp.tempDates = angular.copy($scope.courseInfo.dateTime);
        console.log(data.dateTime.length);
        console.log($scope.courseInfo);
    });
    
    $scope.editData = function() {
        $scope.courseInfo.dateTime = angular.copy($scope.temp.tempDates); //TEMP
        for(var i = 0; i < $scope.courseInfo.dateTime.length; i++) {
            $scope.courseInfo.dateTime[i] = $filter('date')($scope.courseInfo.dateTime[i], 'yyyy-MM-dd HH:mm');
        }
        console.log($scope.courseInfo);
        editcourse.editCourse($scope.courseInfo); //! ? some then()...?
    };
}]);