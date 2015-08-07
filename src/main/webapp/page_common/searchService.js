angular.module("myApp.menuApp")
.factory("searchService",["$http","$q",function($http,$q){
  
  var searchApi = {};
  searchApi.searchTrainigs = function(query){
    return $http.post("/search_controller/search_trainings",query).success(function(data){
      console.log("Training Result for " + query +"\n" + JSON.stringify(data));
    });
  }
  searchApi.searchUsers = function(query){
    return $http.post("/search_controller/search_users",query).success(function(data){
      console.log("User Result for " + query +"\n" + JSON.stringify(data));
    });
  }
  
  searchApi.searchBoth = function(query){
    var users = searchApi.searchUsers(query);
    var tr = searchApi.searchTrainigs(query);
    
    return $q.all({users:users, trainings: tr});
  }
  
  return searchApi;
  
}])