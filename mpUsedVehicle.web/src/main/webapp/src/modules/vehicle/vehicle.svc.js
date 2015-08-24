(function(ng){
    var mod = ng.module('vehicleModule');
    
    mod.service('vehicleService', ['CrudCreator','vehicleContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);
    }]);
})(window.angular);
