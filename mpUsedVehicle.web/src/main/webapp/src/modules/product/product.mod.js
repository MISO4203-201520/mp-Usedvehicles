(function (ng) {
    var mod = ng.module('productModule', ['ngCrud']);

    mod.constant('productContext', 'products');

    mod.constant('productModel', {
        fields: [{
                name: 'name',
                displayName: 'Name',
                type: 'String',
                required: true
            }, {
                name: 'price',
                displayName: 'Price',
                type: 'Integer',
                required: true
            }, {
                name: 'vehicle',
                displayName: 'Vehicle',
                type: 'Reference',
                service: 'vehicleService',
                options: [],
                required: true
            }, {
                name: 'provider',
                displayName: 'Provider',
                type: 'Reference',
                service: 'providerService',
                options: [],
                required: true
            }, {
                name: 'discount',
                displayName: 'Discount',
                type: 'Integer',
                required: true
            }, {
                name: 'availability',
                displayName: 'Availability',
                type: 'Boolean',
                required: true
            }], 
        childs: [{
                name: 'comments',
                displayName: 'Comments',
                //template: '', //override generic template
                ctrl: 'commentCtrl'            }]});
})(window.angular);
