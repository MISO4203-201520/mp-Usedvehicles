(function (ng) {
    var mod = ng.module('productModule');

    //Provider Products Controller
    mod.controller('productsCtrl', ['CrudCreator', '$scope', 'productService', 'productModel', function (CrudCreator, $scope, svc, model) {
            CrudCreator.extendController(this, svc, $scope, model, 'product', 'Product');
            this.fetchRecords();
            this.loadRefOptions();
        }]);
    
    //User/Buyer Controller
    mod.controller('productCtrl', ['CrudCreator', '$scope', 'productService', 'productModel', 
        'cartItemService', 'messageService','$location', 'authService', 'vehicleService', 
        'userService', 
        function (CrudCreator, $scope, svc, model, cartItemSvc, messageSvc, $location, authSvc, vehicleSvc, userSvc) {
            CrudCreator.extendController(this, svc, $scope, model, 'product', 'Products');
            //Variables
            $scope.varEnable = true;
            $scope.providerName = ''; 
            $scope.records=[];
            $scope.text2Search=""; 
            
            // Vars for advanced search
            $scope.brand = "";
            $scope.model = "";
            $scope.capacity = "";
            $scope.price = "";
            
            //Funciones
            $scope.getProductsByAdvancedSearch = function () {
                console.log("Getting by advanced search...");
                
                svc.getProductsByAdvancedSearch(
                        $scope.brand, $scope.model,
                        $scope.capacity, $scope.price).then(function (products) {
                            
                    $scope.records = [];
                    for (var i = 0; i < products.length; i++) {
                        $scope.records.push(products[i]);
                        
                    }
                });
            };
            
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
            
            $("#users").hide();
            if (authSvc.getCurrentUser()) {
                userSvc.isAdmin().then(function (data) {
                    
                    if (data){
                        $("#users").show();
                    } else {
                        $("#users").hide();
                    }
                });
            }
            $(".dropdown-menu > li > a").click(function () {
                $("#users").hide();
            });
            
            this.question='';
            $scope.tmpRecord;

            var self = this;
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
                }, {
                    name: 'reviews',
                    displayName: 'Reviews',
                    icon: 'list',
                    class: 'info',
                    dataToggle:'modal',
                    dataTarget:'#modalReviews',
                    fn: function (record) {
                        vehicleSvc.api.get(record.vehicle.id).then(function (data) {
                            self.detailsMode = true;
                            $scope.vehicleRecord = data;
                            console.log($scope.vehicleRecord.reviews);
                        });
                    },
                    show: function () {
                        return !self.detailsMode;
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
                //clean question
                this.question='';
            };
        }]);
})(window.angular);
