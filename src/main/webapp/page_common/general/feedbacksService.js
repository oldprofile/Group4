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
        alert("Feedbacks: " + JSON.stringify(data));
    return data
  }).error(function(err){
    return err;
  });
    
  }
  }
  
  return feedbacksApi;

}]);