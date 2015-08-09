angular.module('dosHeadToolModule',[])
.controller("DosHeadToolController",['$scope',function($scope){
  $scope.radioModel = 'All';   
}])
.directive('dosHeadTool',function(){
    
    
    
    return {
      restrict: "E",
      templateUrl: "page_common/dosHeadToolsDirecitve/dosHeadTool.html",
      require: "ngModel",
      replace: true,
      controller: "DosHeadToolController",    
      link: function(scope,elem,attrs,ngModelController){
          
          
        
          ngModelController.$formatters.push(function(modelValue){
              if (modelValue === undefined){
                  return {
                      search: "",
                      qType: "All"
                    };
              }
              var qType = modelValue.qType;
              var search = modelValue.search;
              
              if(qType === ""){
                  qType = "All";
              } else if(qType === true){
                  qType = "Coach";
              } else if (qType === false){
                  qType = "Student";
              } else{
                  qType = "All";
              }
              
              return{
                  search: search,
                  qType: qType
              };
              
          });
           
          ngModelController.$render = function() {
                scope.search = ngModelController.$viewValue.search;
                scope.radioModel  = ngModelController.$viewValue.qType;
            };
          
          ngModelController.$parsers.push(function(viewValue){
              var qType = viewValue.qType;
              var filterString;
              var filterObject = {$:""}
              if(qType === "All"){
                  qType = "";
                  filterString = "$:'" + viewValue.search.toLowerCase() + "'";
                  filterObject = {$:viewValue.search.toLowerCase()}
                  
              } else if (qType === "Coach"){
                  qType = true;
                  filterString = "$:'" + viewValue.search.toLowerCase() + "', isCoach:" + qType;
                  filterObject = {$:viewValue.search.toLowerCase(),isCoach:qType}
              } else if (qType === "Student"){
                  qType = false;
                  filterString = "$:'" + viewValue.search.toLowerCase() + "', isCoach:" + qType;
                  filterObject = {$:viewValue.search.toLowerCase(),isCoach:qType}
              }
              
              return {
                  qType: qType,
                  search : viewValue.search,
                  filterString: filterString.toString(),
                  filterObject: filterObject,
              };
              
              
          });
          
          scope.$watch('[radioModel,search]', function(){
              ngModelController.$setViewValue({qType:scope.radioModel, search: scope.search})
              
          });
        
        
        
          
      }
          
     };    
    })