angular.module('myApp.createcourse')
.factory('createcourse',['$http', '$location', function($http, $location) {
    
    var createcourse = {};
    
    createcourse.createCourse = function(courseData){
        var fd = new FormData();
        fd.append('courseInfo', JSON.stringify(courseData));
        
        return $http.post('/training_controller/create_training', fd, {
            headers : {
                'Content-Type' : undefined
            },
            transformRequest : angular.identity
        })
            .success(function(data) {
            alert('Course created successfully!');
            $location.path("/mycourses");
            return data;
        })
            .error(function(err) {
              return err;
            }) 
    }
    return createcourse;
}
]);