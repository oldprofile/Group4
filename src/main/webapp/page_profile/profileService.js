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
    return $http.post('',data).success(function(data){
      return data;
    }).error(function(err){
      return err;});
  };
  
  profileService.getUsersCoachArchive = function(data){
    return $http.post('',data).success(function(data){
      return data;
    }).error(function(err){
      return err;});
    
    profileService.getCoachFeedbacks = function(data){
    return $http.post('',data).success(function(data){
      return data;
    }).error(function(err){
      return err;});
  };
  
  profileService.getStudentFeedbacks = function(data){
    return $http.post('',data).success(function(data){
      return data;
    }).error(function(err){
      return err;});
    
}
     
    
  return profileService
}]);