angular.module('myApp.createcourse')
.factory('createcourse',['$http', '$location', function($http, $location) {
    
    var createcourse = {};
    
    createcourse.createCourse = function(courseData){
        return $http.post('/training_controller/create_training', courseData)
            .success(function(data) {
            alert('Course created successfully!');
            $location.path("/createdSuccessfully");
            return data;
        })
            .error(function(err) {
              return err;
            }) 
    }
    return createcourse;
}
]);