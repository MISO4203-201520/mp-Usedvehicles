(function (ng) {
    var mod = ng.module('cartItemModule');

    mod.controller('cartItemCtrl', ['CrudCreator', '$scope', 'cartItemService', 'checkoutService', 'cartItemModel', '$location', 'authService', function (CrudCreator, $scope, svc, chkSvc, model, $location, authSvc) {
            CrudCreator.extendController(this, svc, $scope, model, 'cartItem', 'My Shopping Cart');
            var self = this;

            var oldFetch = this.fetchRecords;
            this.fetchRecords = function () {
                return oldFetch.call(this).then(function (data) {
                    self.calcTotal();
                    return data;
                });
            };
            this.fetchRecords();
            this.readOnly = true;
            $scope.lastQuantity = 0;
            $scope.total = 0;

            this.recordActions = {
                delete: {
                    displayName: ' ',
                    icon: 'trash',
                    class: 'primary',
                    fn: function (record) {
                        svc.deleteRecord(record).then(function () {
                            self.fetchRecords();
                        });
                    },
                    show: function () {
                        return true;
                    }
                }};

            this.calcTotal = function () {
                $scope.total = 0;
                for (var i = 0; i < $scope.records.length; i++) {
                    $scope.total += $scope.records[i].product.price + ($scope.records[i].product.price * 0.16);
                }
            };

            $scope.verify = function (quantity) {
                $scope.lastQuantity = quantity;
            };//guarda la cantidad anterior

            $scope.postVerify = function (record) {
                var patron = /^\d*$/; //^[0-9]{3}$
                if (patron.test(record.quantity) && record.quantity > 0) {
                    self.calcTotal();
                } else {
                    self.showError("You must enter a valid quantity");
                    record.quantity = $scope.lastQuantity;
                    $scope.currentRecord = record;
                }
            };//Realiza la validacion de la nueva cantidad asignada.
            $scope.checkout = function () {
                var order = {};
                
                chkSvc.createNewOrder().then(function(data){
                    order = data;
                    for(var i= 0; i< $scope.records.length ;i++){
                        $scope.records[i].order = order;
                        svc.editItem($scope.records[i]);
                    }
                    $location.path('/checkout');
                });                                              
            };
            $scope.history = function () {
                $('#historyTransactions').modal('show');
                svc.getHistoryByClient(authSvc.getCurrentUser().id).then(function (result) {
                    $scope.historyRecords = [];
                    for (var i = 0; i < result.length; i++) {
                           $scope.historyRecords.push(result[i]);                   
                    }
                    console.log("$scope.result" +$scope.historyRecords.length);
                });                
                
            };            
            $scope.taxes = function (record) {
                return record.product.price * 0.16;
            };
        }]);

})(window.angular);
