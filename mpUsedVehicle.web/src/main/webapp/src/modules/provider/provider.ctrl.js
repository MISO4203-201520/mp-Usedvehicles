(function (ng) {
    var mod = ng.module('providerModule');

    mod.controller('providerCtrl', ['CrudCreator', '$scope', 'providerService', 'providerModel', function (CrudCreator, $scope, svc, model) {
            CrudCreator.extendController(this, svc, $scope, model, 'provider', 'Provider');
            
            $scope.findItem = function () {
                console.log("$scope.records" + $scope.records.length);
                svc.findVehicleBrands().then(function (brand) {
                    $scope.records = [];
                    console.log("Ingresa provider");
                   
                });
            };
            $scope.listItems = function () {
                $scope.findItem();
            };
            this.fetchRecords();
        }]);

})(window.angular);
