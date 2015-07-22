angular.module('myApp.createcourse')
.controller('CreateCourseController', ['$scope', 'createcourse', 'initCourseService', function($scope, createcourse, initCourseService) {
    $scope.isEdited = false;
    $scope.header = 'Create';
    
    initCourseService($scope);
                                       
    /////Hardcode/////
    $scope.courseInfo.userLogin="1";
    //////////////////
    
    $scope.saveData = function() {
        console.log($scope.courseInfo);                //TEMP
        createcourse.createCourse($scope.courseInfo);
    }
    
}]);