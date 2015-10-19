(function(ng){
    var mod = ng.module('orderModule');
    mod.service('orderService', ['CrudCreator','orderContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);
            this.getOrderByProvider = function ()
            {
                return this.api.one("provider/301").get();
            };
            this.getOrderByClient = function ()
            {
                return this.api.one("client/101").get();
            };
    }]);
})(window.angular);
