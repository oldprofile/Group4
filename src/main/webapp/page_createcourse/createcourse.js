'use strict';
angular.module('myApp.createcourse', ['ngRoute', 'ui.bootstrap.datetimepicker', 'ui.bootstrap'])
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/createcourse', {
            templateUrl: 'page_createcourse/createcourse.html',
            controller: 'CreateCourseController'
        });
    }])
    .directive('preloadable', function () {
    return {
        link: function(scope, element) {
            element.addClass("empty");
            element.bind("load" , function(e){
                element.removeClass("empty");
            });
        }
    }
});