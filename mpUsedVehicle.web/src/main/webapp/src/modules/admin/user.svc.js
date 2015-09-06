(function(ng){
    var mod = ng.module('userModule');
    
    mod.service('userService', ['CrudCreator','userContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);
    }]);
})(window.angular);
