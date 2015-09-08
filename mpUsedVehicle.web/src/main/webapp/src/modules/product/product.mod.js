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
            }], 
        childs: [{
                name: 'comments',
                displayName: 'Comments',
                //template: '', //override generic template
                ctrl: 'commentCtrl'            }]});
})(window.angular);
