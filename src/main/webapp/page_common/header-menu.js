var menuApp = angular.module("myApp.menuApp",['ngAnimate']);
menuApp.directive('headerMenu',function(){
    return {
        restrict: "E",
        templateUrl: "page_common/header-menu.html"
    };
});

menuApp.controller("HeaderMenuController",['$scope','$location',"getCategories",'userService',"notificationService", "$modal", function($scope, $location,getCategories, userService,notificationService, $modal){
    $scope.categories = [];
    $scope.username = "";
  
    $scope.notifications = 0;
    $scope.anim = false;
    function polling(c){
      notificationService.notification(c).success(function(data,status){
        
      console.log("getting notification " + data + ", status" + status);
      
      if((typeof data) === "number"){  
       // alert(data + " is Number");  
        $scope.notifications = data;
        $scope.anim = !$scope.anim;
      }
      setTimeout(function(){
        polling($scope.notifications);
        
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
    
    
    $scope.openSearch = function(){
      
      var searchModalInstance = $modal.open({
      animation: true,
      templateUrl: 'page_common/SearchModal.html',
      controller: 'SearchController',
      size: "lg",
      windowClass:"search-modal",
      resolve: {
        
      }
      });
    
      searchModalInstance.result.then(function (data) {
         
      //sending new settings
        profileService.saveSettings(data);
      
      }, function () {
      
      //cancel feedback
      });
    
      
    }
}])
.controller("SearchController",['$scope','$modalInstance',"searchService","$location","userService",function($scope,$modalInstance,searchService,$location,userService){
  $scope.goto = function(path){
    $location.path(path);
    $scope.cancel();
  }
  
  $scope.searchQuery = "";
  $scope.result = [];
  $scope.resultC = [];
  
  $scope.search = function(){
    if($scope.searchQuery.length == 0){
      $scope.result = [];
      $scope.resultC = [];
      $scope.message = "";
      return;
    }
    if($scope.searchQuery.length < 3){
      $scope.message = "More More More ..."
      $scope.result = [];
      $scope.resultC = [];
      return;
    }
    $scope.message = "Wait a sec, I got dozy backend ..."
    if(!userService.isAdmin()){
      searchService.searchTrainigs($scope.searchQuery).success(function(data){
        if (data.length > 0){
          $scope.message = "";
        } else { 
          $scope.message = "Oh Man, Oh Man ..."
        }

        $scope.result = data;
      });
    } else { 
    
      searchService.searchBoth($scope.searchQuery).then(function(result){
        console.log("Both:\n" + JSON.stringify(result));
        
        
        if(result.trainings.data.length > 0 || result.users.data.length > 0 ){
          $scope.message = "";
        } else {
          $scope.message = "Oh Man, Oh Man ..."
        }
        
        $scope.result = result.trainings.data;
        $scope.resultC = result.users.data;
        
        
      });
    
    } 
  }
  
  
  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
}])
                          
 