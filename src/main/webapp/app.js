'use strict';

// Declare app level module which depends on views, and components
var app = angular.module('myApp', [
    'ngRoute',
    'http-auth-interceptor',
    'myApp.view1',
    'myApp.view2',
    'myApp.mycourses',
    'myApp.browse',
    'myApp.profile',
    'myApp.admin',
    'myApp.version',
    'myApp.login'
    
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/mycourses'});
}]);

app.controller('MainController',['$scope',function($scope){
    //MainController
    
    
    
}]);

app.directive('authClass', function() {
    return {
      restrict: 'C',
      link: function(scope, elem, attrs) {
          
//        //once Angular is started, remove class:
//        elem.removeClass('waiting-for-angular');
        
//        var login = elem.find('#login-holder');
//        var main = elem.find('#content');
//        
//        login.hide();
//        
//        scope.$on('event:auth-loginRequired', function() {
//          login.slideDown('slow', function() {
//            main.hide();
//          });
//        });
//        scope.$on('event:auth-loginConfirmed', function() {
//          main.show();
//          login.slideUp();
//        });
          
          
          scope.$on('hideLoginEvent',function(event, data){
            
            var loginCard = $.find("#login-card");
              alert(loginCard);
              $('#login-card').hide();
              loginCard.hide();
              
          });
          
      }
    }
  });


