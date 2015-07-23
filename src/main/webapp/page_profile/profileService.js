angular.module('myApp.profile')
.factory('profileService', ['$http', function($http) {
  var profileService = {};
    
    profileService.getUserInfo = function(userlogin){
        return $http.get('/user_controller/user_info/' + userlogin).success(function(data) {
          
          
              return data;
            })
            .error(function(err) {
              return err;
            })
    };
  
  profileService.getUsersStudentArchive = function(data){
    return $http.post('/user_controller/all_trainings_of_user_by_type_student',data).success(function(data){
      return data;
    }).error(function(err){
      return err;});
  };
  
  profileService.getUsersCoachArchive = function(data){
    return $http.post('/user_controller/all_trainings_of_user_by_type_coach',data).success(function(data){
      return data;
    }).error(function(err){
      return err;});
  }
    
    profileService.getCoachFeedbacks = function(data){
    return $http.post('/feedbacks/coach_feedback',data).success(function(data){
      return data;
    }).error(function(err){
      return err;});
  };
  
  profileService.getStudentFeedbacks = function(data){
    return $http.post('/feedbacks/user_feedback',data).success(function(data){
      return data;
    }).error(function(err){
      return err;});
    
}
     
    
  return profileService
}]);