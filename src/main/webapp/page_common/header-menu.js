var menuApp = angular.module("myApp.menuApp",[]);
menuApp.directive('headerMenu',function(){
    return {
        restrict: "E",
        templateUrl: "page_common/header-menu.html"
    };
});

menuApp.controller("HeaderMenuController",['$scope','$location',"getCategories",'userService',"notificationService",function($scope, $location,getCategories, userService,notificationService){
    $scope.categories = [];
    $scope.username = "";
  
    $scope.notifications = 0;
  
    function polling(c){
      notificationService.notification(c).success(function(data){
      alert("getting notification " + data);
      JSON.stringify(data);  
      $scope.notifications = data;
      
      setTimeout(function(){
        polling(data);
      },5000);
    })
    }
    polling($scope.notifications);
    
    userService.loginPromise().then(function(un){
      $scope.username = un;
    });
    $scope.isActive = function (viewLocation) {
     var viewLocationArray = viewLocation.split("/");
     var locationArray = $location.path().split("/");
     
     
     var active = (locationArray[1] === viewLocationArray[1]);
     return active;
    };
    
    getCategories.success(function(data){
                          //alert(JSON.stringify(data))
                          $scope.categories = data;
    }).error(function(err){
        alert("Can't get categories")
    })
    
    $scope.logoutFunction = function(){
      
        userService.logout();
    }
    
    $scope.isAdmin = function(){
      return userService.isAdmin();
    }
}])
                          
 