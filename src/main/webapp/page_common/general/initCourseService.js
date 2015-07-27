angular.module('myApp')
.factory('initCourseService', ['$http', 'getCategories', 'userService', function($http, getCategories, userService) {
    return function(scope) {
        scope.categoriesObj = [];
        scope.types = [
            {name: 'Internal',
             isInternal: true},
            {name: 'External',
             isInternal: false}];
        console.log(scope.types);
        scope.languages = ['Russian', 'English'];
        
        scope.temp = {};
        scope.temp.tempDates = [];
        scope.temp.place = "";
        scope.temp.pictureHolder = "";  //pictureLink or pictureData
        
        scope.isAdmin = userService.isAdmin();
        
        scope.courseInfo = {};
        scope.courseInfo.dateTime = [];
        scope.courseInfo.places = [];
        scope.courseInfo.additional = "";
        scope.courseInfo.isInternal = true;
        scope.courseInfo.userLogin = userService.getUser().login;
        
        getCategories.success(function(data) {
            scope.categoriesObj = data;
        });
    };
}]);