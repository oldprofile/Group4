'use strict';

// Declare app level module which depends on views, and components
var app = angular.module('myApp', [
    'ngRoute',
    'ui.bootstrap',
    'http-auth-interceptor',
    'myApp.view1',
    'myApp.view2',
    'myApp.mycourses',
    'myApp.browse',
    'myApp.createcourse',
  'myApp.notifications',
    'myApp.profile',
    'myApp.admin',
    'myApp.version',
    'myApp.login',
    'myApp.menuApp',
    'createcourse.datepickerApp',
    'myApp.imageLoaderApp',
    'myApp.footerApp',
    'myApp.login',
    'myApp.courseinfo',
    
]).config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/mycourses'});
}]);

app.controller('MainController',['$scope',function($scope){
    //MainController
    
    
    
}]);


app.directive('authClass', ['$location','userService',function($location,userService) {
    return {
      restrict: 'C',
      link: function(scope, elem, attrs) {
          //hide content before auth
        
        scope.isLogged = userService.isLogged; 
        
          if(scope.isLogged == false){
              scope.prevPath = $location.path();
              $location.path('login');
          }
          
        elem.removeClass('waiting-for-angular');
//        var login = elem.find('#login-holder');
//        var main = elem.find('#content');
//        
//        login.hide();
//      
          
          
        scope.$on('event:auth-loginRequired', function() {
            //deny from server
            //showLoginForm(elem);
            scope.isLogged = false;
            userService.clearUser();
            scope.prevPath = $location.path();
            $location.path('login');
        });
          
          
        scope.$on('event:auth-loginConfirmed', function(event,data) {
          //confirm form server    
            console.log("Confirm User:" + JSON.stringify(data)); 
            scope.isLogged = true;
            userService.setUser(data.login,data.name,data.password,data.role,data.token);
            
            $location.path(scope.prevPath);
          //hideLoginForm(elem);
        });
          
          
        scope.$on('hideLoginEvent',function(event, data){
            //hideLoginForm(elem);
            
            if(data.login == "test" && data.password == "test"){
                scope.isLogged = true;
                userService.setUser(data.login,data.login,data.password,{},"");
                $location.path(scope.prevPath);
            }
        });
          
      }
    }
  }]);





// ###### shared functions

    function showLoginForm(elem){
//        if (elem == null){
//            $('login-card').show();
//            $('#content-outer').hide();
//        } else {
//            elem.find('login-card').show();
//            elem.find('#content-outer').hide();
//        }
    }

    function hideLoginForm(elem){
//        if (elem == null){
//            $('login-card').hide();
//            $('#content-outer').show();
//        } else {
//            elem.find('login-card').hide();
//            elem.find('#content-outer').show();
//        }
    }
