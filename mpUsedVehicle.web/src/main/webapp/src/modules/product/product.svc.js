(function(ng){
    var mod = ng.module('productModule');
    
    mod.service('productService', ['CrudCreator','productContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);
            
            this.getProductsByAdvancedSearch = function(brandFilter, modelFilter, capacityFilter, priceFilter, plateFilter, locationFilter, colorFilter){
                
                return this.api.all('advancedsearch').getList({'brandFilter': brandFilter, 
                                                                'modelFilter': modelFilter, 
                                                                'capacityFilter': capacityFilter, 
                                                                'priceFilter': priceFilter,
                                                                'plateFilter': plateFilter,
                                                                'locationFilter': locationFilter,
                                                                'colorFilter': colorFilter
                                                            });;
            };
            this.getVehiclesName = function(){
                return this.api.all('listVehiclesName').getList({});
            };
            this.getVehiclesBrand = function(){
                return this.api.all('listVehiclesBrand').getList({});
            };
            this.getVehiclesCapacity = function(){
                return this.api.all('listVehiclesCapacity').getList({});
            };
            this.getVehiclesColor = function(){
                return this.api.all('listVehiclesColor').getList({});
            };
            this.getVehiclesModel = function(){
                return this.api.all('listVehiclesModel').getList({});
            };
            this.getVehiclesPlate = function(){
                return this.api.all('listVehiclesPlate').getList({});
            };
            this.getVehiclesLocation = function(){
                return this.api.all('listVehiclesLocation').getList({});
            };            
            this.findCheaperbyProvider = function(idProvider){
                return this.api.one('cheapest', idProvider).get();
            };
            this.findCheaperbyVehicle = function(nameVehicle){
                return this.api.one('cheapestbyvehicle', nameVehicle).get();
            };
            this.askQuestion = function(question){
                this.saveRecord(question);
            };
            var selectedProductId = 'ini';
            this.getSelectedProductId = function () {
                return selectedProductId;
            };
            this.setSelectedProductId = function (productId) {
                selectedProductId=productId;
            };
            this.updateRating = function (id,newRate) {
                return this.api.one('rate/'+id+"/").customPUT(newRate);
            };
            this.canRateProduct = function (id,idClient) {
                return this.api.one('rate/'+id+"/").customGET(idClient);
            };
    }]);
})(window.angular);