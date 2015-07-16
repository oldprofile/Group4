angular.module('myApp.courseinfo')
.factory('courseInfoService', ['$http', function($http) {
  var courseInfoService = {};
    
    courseInfoService.getCourseInfo = function(courseData){
        return $http.post('/training_controller/training_info',courseData).success(function(data) {
              
              return data;
            })
            .error(function(err) {
              return err;
            }) 
    }
    
  return courseInfoService
}]);