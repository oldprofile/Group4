angular.module('myApp.createcourse')
.controller('CreateCourseController', ['$scope', 'createcourse', 'initCourseService', function($scope, createcourse, initCourseService) {
    $scope.isEdited = false;
    
    initCourseService($scope);
                                       
    /////Hardcode/////
    $scope.courseInfo.dateTime=['08-08-2015 23:10:00', '04-09-2015 13:15:00', '28-01-2013 02:10:00'];
    $scope.courseInfo.userLogin="1";
    //////////////////
    
    $scope.saveData = function() {
        console.log($scope.courseInfo);                //TEMP
        createcourse.createCourse($scope.courseInfo);
    }
    
}]);