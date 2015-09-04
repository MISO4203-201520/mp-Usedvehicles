(function (ng) {
    var mod = ng.module('productModule');

    mod.controller('productCtrl', ['CrudCreator', '$scope', 'productService', 'productModel', 'cartItemService', 'messageService','$location', 'authService', function (CrudCreator, $scope, svc, model, cartItemSvc, messageSvc,$location, authSvc) {
            CrudCreator.extendController(this, svc, $scope, model, 'product', 'Products');

            this.searchByName = function (vehicleName) {
                var search;
                if (vehicleName) {
                    search = '?q=' + vehicleName;
                }
                $location.url('/catalog' + search);
            };
            
            this.question='';
            $scope.tmpRecord;

            this.recordActions = [{
                    name: 'addToCart',
                    displayName: 'Add to Cart',
                    icon: 'shopping-cart',
                    class: 'primary',
                    dataToggle: '',
                    dataTarget: '',
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
                },{
                    name: 'askAQuestion',
                    displayName: 'Ask a Question',
                    icon: 'question-sign',
                    class: 'primary',
                    dataToggle:'modal',
                    dataTarget:'#myModalNorm',
                    fn: function (record) {
                        if (authSvc.getCurrentUser()) {
                            $scope.tmpRecord=record;
                        } else {
                            $location.path('/login');
                        }
                    },
                    show: function () {
                        return true;
                    }
                }];

//            this.loadRefOptions();
            this.fetchRecords();
            
            this.sendQuestion = function(){
                console.log("askAQuestion");
                //Tmp question
                newQuestion={
                    question: this.question,
                    product:$scope.tmpRecord,
                    provider:$scope.tmpRecord.provider,
                    client:authSvc.getCurrentUser()
                };
                messageSvc.askQuestion(newQuestion);
                //Hide modal
                $('#myModalNorm').modal('hide');
            };
        }]);
})(window.angular);
