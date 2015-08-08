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
    "myApp.ohman",
    'tableSort',
    'angular-toasty',
  
    
]).config(['$routeProvider', 'toastyConfigProvider', function($routeProvider, toastyConfigProvider ) {
  $routeProvider.otherwise({redirectTo: '/mycourses'});
  toastyConfigProvider.setConfig({
        sound: true,
        theme: 'bootstrap',
        position:"top-right",
    });
}]);

app.controller('MainController',['$scope',function($scope){
    //MainController
    
    
    
}]);


app.directive('authClass', ['$location','userService','loginService','toasty',function($location,userService,loginService, toasty ) {
    return {
      restrict: 'C',
      link: function(scope, elem, attrs) {
          //hide content before auth
        
        scope.isLogged = loginService.checkCreds();
        
          if(scope.isLogged == false){
              scope.prevPath = $location.path();
              $location.path('login');
          } else {
            //loggedin;
            var data = loginService.getCreds();
            userService.setUser(data.login,data.role,data.token);
            
            if(scope.prevPath === undefined){
              //$location.path("/");
            } else {
              $location.path(scope.prevPath);
            }
          }
          
        elem.removeClass('waiting-for-angular-login');
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
            loginService.clearCreds();
            scope.prevPath = $location.path();
            $location.path('login');
        });
          
          
        scope.$on('event:auth-loginConfirmed', function(event,data) {
          //confirm form server   
         
            console.log("Confirm User:" + JSON.stringify(data)); 
            scope.isLogged = true;
            //alert(JSON.stringify(data));
            userService.setUser(data.login,data.role,data.token);
            if(scope.prevPath.toLowerCase() === "/login"){
              scope.prevPath = "/";
            }
            $location.path(scope.prevPath);
          //hideLoginForm(elem);
        });
        
          function presentErrorPage(statusCode){
            
            $location.path("/ohman/" + statusCode);
            
          }
          
          scope.$on('event:bad-request',function(event,rejection){
            presentErrorPage(rejection.status);
          });
        
          scope.$on('event:not-found',function(event,rejection){
            alert(JSON.stringify(rejection))
            presentErrorPage(rejection.status);
          });
        
          scope.$on('event:server-down',function(event,rejection){
            presentErrorPage(rejection.status);
          });
          
          scope.$on('event:auth-forbidden',function(event,rejection){
            presentErrorPage(rejection.status);
          });

          scope.$on('event:server-not-responds',function(event,rejection){
            // somehow on client
            //alert("Is Server off?");
          });
        
        
          scope.$on('need-toast',function(event,toast){
            if(toast.type === "default"){
              toasty({title: toast.title, msg: toast.message});
            }
            
            
          })
        
          
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
