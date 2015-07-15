angular.module('myApp.courseinfo')
.factory('courseInfoService', ['$http', function($http) {
  var courseInfoService = {};
    
    courseInfoService.getCourseInfo = function(courseName){
        return $http.post('/training_info',courseName).success(function(data) {
              
              return data;
            })
            .error(function(err) {
              return err;
            }) 
    }
    
  return courseInfoService
}]);