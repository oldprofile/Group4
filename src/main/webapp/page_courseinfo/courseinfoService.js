angular.module('myApp.courseinfo')
.factory('courseInfoService', ['$http', function($http) {
  var courseInfoService = {};
    
    courseInfoService.getCourseInfo = function(courseName){
        return $http.get('/training_controller/training_info/' + courseName).success(function(data) {
          
          
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
  
     courseInfoService.getPromtText = function(data){
       var promtText = "";
          if(data.subscriber === true){
            promtText = "You are in!";
          } else {
            if (data.participantsNumber > data.listeners.length){
              promtText = "You're Welcome!"
            } else {
              promtText = "I can add you to queue"
            }
          }
       return promtText;
     };
  
  courseInfoService.editLesson = function(lessonNumber, courseName, newDate, newPlace) {
    var lessonData = {
      trainingName: courseName,
      lessonNumber: lessonNumber,
      newDate: newDate,
      newPlace: newPlace
    };
    return $http.post('/training_controller/change_date', lessonData).then(function(result) {
      console.log("Lesson edited successfully");
      return result.data;
    }, function(err) {
      console.log("Edit lesson error: " + err.statusCode);
      return err;
    });
  };
    
  return courseInfoService;
}]);