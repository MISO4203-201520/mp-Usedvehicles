(function(ng){
    var mod = ng.module('checkoutModule');
    
    mod.service('checkoutService', ['CrudCreator','checkoutContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);
            var self = this;
            this.addItem = function (record) {
                this.fetchRecords().then(function (data) {
                    console.log(data);
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
    }]);
})(window.angular);
