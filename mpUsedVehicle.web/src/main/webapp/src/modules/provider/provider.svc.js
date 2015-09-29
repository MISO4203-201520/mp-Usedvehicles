(function(ng){
    var mod = ng.module('providerModule');
    
    mod.service('providerService', ['CrudCreator','providerContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);
            this.findProviderbyBrand = function(brandFilter){
                return this.api.one('providerbybrand', brandFilter).get();
            };
            this.findProviderbyModel = function(modelFilter){
                return this.api.one('providerbymodel', modelFilter).get();
            };
            this.findProviderbyCity = function(cityFilter){
                return this.api.one('providerbycity', cityFilter).get();
            };      
            this.findProviderbyCity = function(lowerFilter, upperFilter){
                return this.api.one('providerbypricerange', lowerFilter+"-"+upperFilter).get();
            }; 
    }]);
})(window.angular);
