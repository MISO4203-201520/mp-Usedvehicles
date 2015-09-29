(function(ng){
    var mod = ng.module('userModule');
    
    mod.service('userService', ['CrudCreator','userContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);
            
            this.isAdmin = function() {
                return this.api.one("../users/isAdmin").get();
            };
            
            this.getCurrentUser = function(){
                return this.api.one('currentUser').get();
            };
    }]);
})(window.angular);
