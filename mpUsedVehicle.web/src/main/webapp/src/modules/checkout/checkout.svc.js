(function(ng){
    var mod = ng.module('checkoutModule');
    
    mod.service('checkoutService', ['CrudCreator','checkoutContext', 'Restangular', function(CrudCreator, context, restangular){
            CrudCreator.extendService(this, context);
            var self = this;
            this.addItem = function (record) {
                this.fetchRecords().then(function (data) {
                    for (var i = 0; i < data.length; i++) {
                        if (record.product.name === data[i].product.name && record.product.id === data[i].product.id) {
                            record = data[i];
                            record.quantity = data[i].quantity + 1;
                            break;
                        }
                    }
                    self.saveRecord(record);
                });
            };
            
            this.createNewOrder = function(){
                var order = {
                    'orderStatus':'NEW'
                };
                return self.saveRecord(order);
            };
            this.saveOrder = function(record){
                return self.saveRecord(record);
            };
            this.saveStatus = function(record){
                return this.api.one(record['id']+"/").customPUT(record);
            };
            this.getOrderByProvider = function (idProvider)
            {
                return this.api.one("provider", idProvider).get();
            };
            this.getOrderByClient = function (idClient)
            {
                return this.api.one("client", idClient).get();
            };
            this.getPaymentMethods = function ()
            {
                return this.api.one("../paymentMethods").get();
            };
    }]);
})(window.angular);
