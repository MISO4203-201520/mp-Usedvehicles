(function (ng) {
    var mod = ng.module('providerModule');

    mod.controller('providerCtrl', ['CrudCreator', '$scope', 'providerService', 'providerModel', function (CrudCreator, $scope, svc, model) {
            CrudCreator.extendController(this, svc, $scope, model, 'provider', 'Provider');
            
             $scope.findProviderbyBrand = function () {
                svc.findProviderbyBrand($scope.brandFilter).then(function (ProviderBrand) {
                        $scope.records = [];
                        $scope.records.push(ProviderBrand);
                    });

            };
            $scope.findProviderbyModel = function () {
               svc.findProviderbyModel($scope.modelFilter).then(function (ProviderModel) {
                        $scope.records = [];
                        $scope.records.push(ProviderModel);
                    });

            };
            $scope.findProviderbyCity = function () {
               svc.findProviderbyCity($scope.cityFilter).then(function (ProviderCity) {
                        $scope.records = [];
                        $scope.records.push(ProviderCity);
                    });

            };

            $scope.findProviderbyPrice = function () {
               svc.findProviderbyPrice($scope.lowerFilter, $scope.upperFilter).then(function (ProviderPrice) {
                        $scope.records = [];
                        $scope.records.push(ProviderPrice);
                    });

            };
            this.fetchRecords();
        }]);

    
})(window.angular);
