(function(ng){
    var mod = ng.module('providerModule');
    
    mod.service('providerService', ['CrudCreator','providerContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);
            this.findProviderbyBrand = function(brand){
                return this.api.one('providerbybrand', brand).get();
            };
            this.findVehicleBrands = function(){
                return this.api.all('vehiclebrands').getList();
            };
    }]);
})(window.angular);
