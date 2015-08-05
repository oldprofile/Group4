angular.module("myApp")
.directive("accordionSave",["$cookies","$compile",function($cookies,$compile){
  return{
    compile: function(elem,attrs){
      
      var panes = elem.find("v-pane");
      var pcount = panes.length;
      
      
      var path = attrs.accordionSave;
      var data = $cookies.getObject(path);
      
      
      if(data === undefined){
        data = [];
        for(var i = 0; i<pcount ; i++){
          data.push(false);
        }
        $cookies.putObject(path,data)
      }
      
     
      
      panes.each(function(i,elem){
        if (data[i]){
          angular.element(elem).attr("expanded","");
          
        } 
        
      })
      return function(scope,elem,attrs){
       
        var panes = elem.find("v-pane");
        var pcount = panes.length;
        
        
        function save(){
          data = [];
          panes.each(function(i,elem){
            data.push(angular.element(elem).hasClass("is-expanded"));
        
          })
          
          $cookies.putObject(attrs.accordionSave,data);
          
         }
        
        scope.$on("vAccordion:onExpandAnimationEnd",function(){
          
          save();
        });
        scope.$on("vAccordion:onCollapseAnimationEnd",function(){
          
          save();
        });
        
      }
     
                          
      
    
    },
    
    
    
    
  }
}]);