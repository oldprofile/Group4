angular.module('myApp')
.factory('getCategories', ['$http', function($http) {
  return $http.get('http://localhost:8080/allCategories')
            .success(function(data) {
              return data;
            })
            .error(function(err) {
              return err;
            });
}])
.factory('categoriesLocal', [function() {
  var categories = [];
       
  var api = {};
  api.getCategories = function(){ 
     if (categories.length == 0){
                 alert("no local cat!!!");           
     }
     return categories; }
  api.setCategories = function(cat){ categories = cat; }
  api.getCategoryNameById = function(id){
    
     if (categories.length == 0){
       alert("no local cat!!!");    
       return "";
     }
    if (id < categories.length){
      //alert(categories[id].name)
      return (categories[id].name);  
    }
  }
  return api;
}]);