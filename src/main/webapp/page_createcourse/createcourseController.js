angular.module('myApp.createcourse')
.controller('CreateCourseController', ['$scope', 'getCategories', 'createcourse', function($scope, getCategories, createcourse) {
    $scope.categoriesObj = [];
    $scope.types = [
        {name: 'Internal',
        isInternal: true},
        {name: 'External',
        isInternal: false}];
    $scope.languages = ['Russian', 'English'];
    console.log($scope.types);
    
    $scope.courseInfo = {};
    
    /////Hardcode/////
    $scope.courseInfo.dateTimes=['2015-07-09 02:10 AM', '2015-07-13 05:21 AM', '2015-03-02 21:08 AM'];
    $scope.courseInfo.userLogin="user123";
    //////////////////
    
    $scope.saveData = function() {
        console.log($scope.courseInfo);
        createcourse.createCourse($scope.courseInfo);
    }
    getCategories.success(function(data) {
        $scope.categoriesObj = data;
    });
}]);