angular.module("feedbacks.myApp",[])
.factory("feedbacksService",['$http',function($http){
  var feedbacksApi = {
    createTrainingFeedback : function(createTrainingFeedbackData){
    
    return $http.post("feedbacks/create_training_feedback",createTrainingFeedbackData).success(function(data){
    return data
  }).error(function(err){
    return err;
  });    
  },
    
    getTrainingFeedbacks : function(trainingName){
      return $http.post("feedbacks/training_feedback",trainingName).success(function(data){
        console.log("Feedbacks: " + JSON.stringify(data));
    return data
  }).error(function(err){
    return err;
  });
    
  },
    createUserFeedback : function(feedback){
    return $http.post("feedbacks/create_user_feedback",feedback);
  },
    createCoachFeedback : function(feedback){
    return $http.post("feedbacks/create_coach_feedback",feedback);
  },
    
    requestFeedback : function(data){
      
   
      return $http.post("/feedbacks/request_user_feedback",data).success(function(){
      console.log("Feedback Requested for" + JSON.stringify(data));
      });
  
    }
    
  };
      feedbacksApi.isCanLeaveCoachFeedback = function(current,user){
        var fbdata = {userLogin: user,
                   feedbackerLogin:current};
        
        return $http.post("/feedbacks/can_leave_coach_feedback",fbdata).success(function(data){
          console.log(current + " Leave Coach On " + user + " == " + data);
        });
        
        
      };
  
    feedbacksApi.isCanLeaveStudentFeedback = function(current,user){
        var fbdata = {userLogin: user,
                   feedbackerLogin:current};
        
        return $http.post("/feedbacks/can_leave_user_feedback",fbdata).success(function(data){
          console.log(current + " Leave Student On " + user + " == " + data);
        });
        
        
      };
  
  feedbacksApi.isCanLeaveTrainingFeedback = function(training,user){
        var fbdata = {login: user,
                   trainingName:training};
        
        return $http.post("/feedbacks/can_leave_training_feedback",fbdata).success(function(data){
          console.log(user + " Leave Training On " + training + " == " + data);
        });
        
        
      };
      
    
    feedbacksApi.getCoachFeedbacks = function(data){
    return $http.post('/feedbacks/coach_feedback',data).success(function(data){
      return data;
    }).error(function(err){
      return err;});
  };
  
    feedbacksApi.getStudentFeedbacks = function(data){
    return $http.post('/feedbacks/user_feedback',data).success(function(data){
      return data;
    }).error(function(err){
      return err;});
    
}
    
    feedbacksApi.deleteFeedback = function(data){
      alert("Delete")
      return $http.post('/feedbacks/delete_feedback',data).success(function(d){
        console.log("Feedback deleted");
      })
    };
  
  return feedbacksApi;

}]);