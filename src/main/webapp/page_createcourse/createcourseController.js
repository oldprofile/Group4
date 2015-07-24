angular.module('myApp.createcourse')
.controller('CreateCourseController', ['$scope', '$filter', 'createcourse', 'initCourseService', function($scope, $filter, createcourse, initCourseService) {
    $scope.isEdited = false;
    $scope.header = 'Create';
    
    initCourseService($scope);
                                       
    /////Hardcode/////
    
    //////////////////
    
    $scope.saveData = function() {
        $scope.courseInfo.dateTime = angular.copy($scope.temp.tempDates); //TEMP
        for(var i = 0; i < $scope.courseInfo.dateTime.length; i++) {
            $scope.courseInfo.dateTime[i] = $filter('date')($scope.courseInfo.dateTime[i], 'yyyy-MM-dd HH:mm');
        }
        console.log($scope.courseInfo);
        createcourse.createCourse($scope.courseInfo); //! ? some then() with alert...?
    };
    
}]);