(function (ng) {
    var mod = ng.module('providerModule');

    mod.controller('providerCtrl', ['CrudCreator', '$scope', 'providerService', 'providerModel', function (CrudCreator, $scope, svc, model) {
            CrudCreator.extendController(this, svc, $scope, model, 'provider', 'Provider');
            this.fetchRecords();
        }]);
//TODO Revisar este controlador
//    mod.controller('productsCtrl', ['CrudCreator', '$scope', 'productModel', function (CrudCreator, $scope, model) {
//            CrudCreator.extendCompChildCtrl(this, $scope, model, 'products', 'provider');
//            this.loadRefOptions();
//        }]);
})(window.angular);
