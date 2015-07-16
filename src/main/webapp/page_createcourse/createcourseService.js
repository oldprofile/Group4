angular.module('myApp.createcourse')
.factory('createcourse',['$http', function($http) {
    
    var createcourse = {};
    
    createcourse.createCourse = function(courseData){
        return $http.post('/create_training', courseData).success(function(data) {
              return data;
            })
            .error(function(err) {
              return err;
            }) 
    }
    
  return createcourse;
  }
]);