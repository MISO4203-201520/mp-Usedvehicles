(function (ng) {
    var mod = ng.module('productModule');


    mod.controller('productCtrl', ['CrudCreator', '$scope', 'productService', 'productModel', 'cartItemService', '$location', 'authService', function (CrudCreator, $scope, svc, model, cartItemSvc, $location, authSvc) {
            CrudCreator.extendController(this, svc, $scope, model, 'product', 'Products');
            $scope.varEnable = true;
            $scope.providerName = '';
            $scope.findItem = function () {
                console.log("$scope.records" + $scope.records.length);
                console.log("Ingresa text2Search" + $scope.text2Search);
                if ($scope.searchCriteria == "byProvider")
                {
                    console.log("searchCriteria byProvider" + $scope.searchCriteria);
                    svc.findCheaperbyProvider($scope.text2Search).then(function (Cheaperprovider) {
                        $scope.records = [];
                        $scope.records.push(Cheaperprovider);
                        console.log("Ingresa Cheaperprovider" + Cheaperprovider.id);
                    });
                } else
                {
                    console.log("searchCriteria byVehicle" + $scope.searchCriteria);
                    svc.findCheaperbyVehicle($scope.text2Search).then(function (CheaperVehicle) {
                        $scope.records = [];
                        $scope.records.push(CheaperVehicle);
                        console.log("Ingresa Cheaperprovider" + CheaperVehicle.id);
                    });
                }
            };

            $scope.listItems = function () {
                $scope.findItem();
            };
            $scope.enableSubmit = function(){
                $scope.varEnable = false;
            }
            this.searchByName = function (vehicleName) {
                var search;
                if (vehicleName) {
                    search = '?q=' + vehicleName;
                }
                $location.url('/catalog' + search);
            };
            var self = this;
            this.recordActions = [{
                    name: 'addToCart',
                    displayName: 'Add to Cart',
                    icon: 'shopping-cart',
                    class: 'primary',
                    fn: function (record) {
                        if (authSvc.getCurrentUser()) {
                            return cartItemSvc.addItem({
                                product: record,
                                name: record.vehicle.name,
                                quantity: 1});
                        } else {
                            $location.path('/login');
                        }
                    },
                    show: function () {
                        return true;
                    }
                }
            ];

//            this.loadRefOptions();
            this.fetchRecords();
        }]);
})(window.angular);
