angular.module('myApp.createcourse')
.factory('createcourse',['$http', '$location', function($http, $location) {
    
    var createcourse = {};
    
    createcourse.createCourse = function(courseData) {
        var fd = new FormData();
        fd.append('courseInfo', JSON.stringify(courseData));
        
        return $http.post('/training_controller/create_training', fd, {
            headers : {
                'Content-Type' : undefined
            },
            transformRequest : angular.identity
        }).then(function(results) {
            alert('Course created successfully!');
            $location.path("/mycourses");
            return results.data;
        });
    }
    return createcourse;
}
]);

angular.module('myApp.createcourse')
.factory('editcourse',['$http', '$location', function($http, $location) {
    
    var editcourse = {};
    
    editcourse.editCourse = function(courseData, isDraft, justEdit){
        var fd = new FormData();
        if(isDraft) {
            fd.append('courseInfo', JSON.stringify(courseData));
            if(justEdit) {
                return $http.post('/training_controller/edit_training', fd, {
                    headers : {
                        'Content-Type' : undefined
                    },
                    transformRequest : angular.identity
                })
                    .then(function(results) {
                        alert('Course edited successfully!');
                        $location.path("/mycourses");
                        return results.data;
                    });
            }
            else {
                return $http.post('/training_controller/approve_create_training', fd, {
                    headers : {
                        'Content-Type' : undefined
                    },
                    transformRequest : angular.identity
                })
                    .then(function(results) {
                        alert('Course edited successfully!');
                        $location.path("/mycourses");
                        return results.data;
                    });
            }

        }
        else {
            courseData.additional = "";
            courseData.dateTime = [""];
            courseData.places = [""];

            fd.append('courseInfo', JSON.stringify(courseData));

            return $http.post('/training_controller/approve_edit_training', fd, {
                headers: {
                    'Content-Type': undefined
                },
                transformRequest: angular.identity
            })
                .then(function (results) {
                    alert('Course approved successfully!');
                    $location.path("/mycourses");
                    return results.data;
                });

        }
    }
    return editcourse;
}
]);

