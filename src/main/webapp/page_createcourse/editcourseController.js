angular.module('myApp.createcourse')
.controller('EditCourseController', ['$scope', '$routeParams', '$filter', 'editcourse', 'courseInfoService', 'initCourseService', function($scope, $routeParams, $filter, editcourse, courseInfoService, initCourseService) {
    $scope.isEdited = true;
    $scope.header = 'Edit';
    
    initCourseService($scope);
    
    courseInfoService.getCourseInfo($routeParams.coursename).success(function(data) {
        $scope.courseInfo = angular.copy(data);
        $scope.temp.tempDates = angular.copy($scope.courseInfo.dateTime);
        
        $scope.temp.place = ($scope.courseInfo.placces.length != 0) ? $scope.courseInfo.places[0] : "";
        
        $scope.temp.pictureHolder = $scope.courseInfo.pictureLink;
        console.log($scope.courseInfo);
    });
    
    $scope.editCourse = function() {
        $scope.courseInfo.dateTime = angular.copy($scope.temp.tempDates); //TEMP
        for(var i = 0; i < $scope.courseInfo.dateTime.length; i++) {
            $scope.courseInfo.dateTime[i] = $filter('date')($scope.courseInfo.dateTime[i], 'yyyy-MM-dd HH:mm');
        }
        
        //checking, if replacing all places is needed
        if($scope.temp.place != $scope.courseInfo.places[0]) {
           for(var i = 0; i < $scope.courseInfo.dateTime.length; i++) {
            $scope.courseInfo.places[i] = $scope.temp.place;
           }
        }
        console.log($scope.courseInfo);
        //editcourse.editCourse($scope.courseInfo); //! ? some then()...?
    };
}]);