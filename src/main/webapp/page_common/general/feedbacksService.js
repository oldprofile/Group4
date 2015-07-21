angular.module("feedbacks.myApp",[])
.factory("feedbacksService",['$http',function($http){
  var feedbacksApi = {
    createTrainingFeedback : function(createTrainingFeedbackData){
    
    return $http.post("feedbacks/create_training_feedback",createTrainingFeedbackData).success(function(data){
    return data
  }).error(function(err){
    return err;
  });    
  }
  }
  
  return feedbacksApi;

}]);