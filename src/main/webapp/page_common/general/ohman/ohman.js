angular.module("myApp.ohman",[])
.config(['$routeProvider',function($routeProvider){
  
  $routeProvider.when('/ohman/:status',{
    templateUrl: "page_common/general/ohman/ohman.html",
    controller: "OhManController"
  });
  
}])
.controller("OhManController",["$scope","$routeParams",function($scope,$routeParams){
  
  if($routeParams.status === undefined){
    $scope.status = 404;
  }else {
    $scope.status = $routeParams.status;
  }
   
  switch (+$scope.status){
      case 400:
        $scope.message = "Hmmm... I am stuck right now";
        break;
      case 403:
        $scope.message = "Hmmm... You ain't got rights to do this";
        break;
      
      case 404:
        $scope.message = "Hmmm... You're Asking smth I can't do";
        break;
      
      
      case 500:
        $scope.message = "Hmmm... I am stuck right now";
        break;
        
      
      default:
        $scope.message = "Hmmm... I am stuck right now";
        break;
  }
  
  
  
}])