angular.module('myApp.createcourse')
.controller('CreateCourseController', ['$scope', 'getCategories', function($scope, getCategories) {
    $scope.categoriesObj = [];
    $scope.categories = [];
    $scope.accessibility = ['Internal', 'External'];
    $scope.repeat = ['Once-only', 'Repeating'];
    $scope.languages = ['Russian', 'English'];
    
    $scope.courseInfo = {};
    
    /////Hardcode/////
    $scope.courseInfo.dateTimes=['2015-07-09 02:10 AM', '2015-07-13 05:21 AM', '2015-03-02 21:08 AM'];
    $scope.courseInfo.userLogin="user123";
    //////////////////
    
    $scope.courseInfo.categoryName = "";
    $scope.courseInfo.category = 0;
    $scope.courseInfo.isInternal = true;
    
    $scope.saveData = function() {
        var index = $scope.categories.indexOf($scope.courseInfo.categoryName);
        $scope.courseInfo.category = $scope.categoriesObj[index].id;
        $scope.courseInfo.isInternal = (($scope.courseInfo.accessibility == 'External') ? false : true);
    }
    getCategories.success(function(data) {
        $scope.categoriesObj = data;
        alert(JSON.stringify(data));
        
        for (var c=0; c<$scope.categoriesObj.length; c++){
            alert(JSON.stringify($scope.categoriesObj[c]));
            $scope.categories.push($scope.categoriesObj[c].name);
        }
        $scope.courseInfo.category = $scope.categories[0]; 
    });
}]);