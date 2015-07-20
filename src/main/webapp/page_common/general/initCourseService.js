angular.module('myApp')
.factory('initCourseService', ['$http', 'getCategories', function($http, getCategories) {
    return function(scope) {
        scope.categoriesObj = [];
        scope.types = [
            {name: 'Internal',
             isInternal: true},
            {name: 'External',
             isInternal: false}];
        console.log(scope.types);
        scope.languages = ['Russian', 'English'];
        
        scope.courseInfo = {};
        
        getCategories.success(function(data) {
            scope.categoriesObj = data;
        });
    };
}]);