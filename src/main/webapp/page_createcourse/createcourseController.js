angular.module('myApp.createcourse')
.controller('CreateCourseController', ['$scope', 'getCategories', 'createcourse', function($scope, getCategories, createcourse) {
    $scope.categoriesObj = [];
    $scope.categories = [];
    $scope.types = [
        {name: 'Internal',
        isInternal: true},
        {name: 'External',
        isInternal: false}];
    $scope.repeat = ['Once-only', 'Repeating'];
    $scope.languages = ['Russian', 'English'];
    console.log($scope.types);
    
    $scope.courseInfo = {};
    
    /////Hardcode/////
    $scope.courseInfo.dateTimes=['2015-07-09 02:10 AM', '2015-07-13 05:21 AM', '2015-03-02 21:08 AM'];
    $scope.courseInfo.userLogin="user123";
    //////////////////
    
    $scope.courseInfo.categoryName = "";
    $scope.courseInfo.category = 0;
    
    $scope.saveData = function() {
        var index = $scope.categories.indexOf($scope.courseInfo.categoryName);
        $scope.courseInfo.category = $scope.categoriesObj[index].id;
        console.log($scope.courseInfo);
        createcourse.createCourse($scope.courseInfo);
    }
    getCategories.success(function(data) {
        $scope.categoriesObj = data;
        for (var c=0; c<$scope.categoriesObj.length; c++){
            $scope.categories.push($scope.categoriesObj[c].name);
        }
        $scope.courseInfo.category = $scope.categories[0]; 
    });
}]);