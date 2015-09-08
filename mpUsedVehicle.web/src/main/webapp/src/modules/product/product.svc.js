(function(ng){
    var mod = ng.module('productModule');
    
    mod.service('productService', ['CrudCreator','productContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);
            this.findCheaperbyProvider = function(idProvider){
                return this.api.one('cheapest', idProvider).get();
            };
            this.findCheaperbyVehicle = function(nameVehicle){
                return this.api.one('cheapestbyvehicle', nameVehicle).get();
            };
    }]);

    mod.service('productService', ['CrudCreator','productContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);
            this.askQuestion = function(question){
                this.saveRecord(question);
            };
     }]);
})(window.angular);