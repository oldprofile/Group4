angular.module('myApp.createcourse')
.controller('CreateCourseController', ['$scope', '$filter', 'createcourse', 'initCourseService', 'userService', function($scope, $filter, createcourse, initCourseService, userService) {
    $scope.isEdited = false;
    $scope.isAdmin = userService.isAdmin();
    console.log($scope.isAdmin);
    $scope.header = 'Create';
    
    initCourseService($scope);
                                       
    /////Hardcode/////
    $scope.courseInfo.userLogin = userService.getUser().login;
    //////////////////
    
    $scope.saveData = function() {
        $scope.courseInfo.dateTime = angular.copy($scope.temp.tempDates); //TEMP
        for(var i = 0; i < $scope.courseInfo.dateTime.length; i++) {
            $scope.courseInfo.dateTime[i] = $filter('date')($scope.courseInfo.dateTime[i], 'yyyy-MM-dd HH:mm');
        }
        console.log($scope.courseInfo);
        createcourse.createCourse($scope.courseInfo);
    }
    
}]);