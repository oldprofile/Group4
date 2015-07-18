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
    };
        
    courseInfoService.leave = function(courseData){
        return $http.post('/user_controller/leave_training',courseData).success(function(data) {
              console.log("Leave Success");
              return data;
            })
            .error(function(err) {
             console.log("Leave error:" + err.statusCode);
              return err;
            })
    };
        
        
    courseInfoService.subscribe = function(courseData){
        return $http.post('/user_controller/join_training',courseData).success(function(data) {
              console.log("Sub success");
              return data;
            })
            .error(function(err) {
             console.log("Sub error:" + err.statusCode);
              return err;
            })
    };
    
  return courseInfoService
}]);