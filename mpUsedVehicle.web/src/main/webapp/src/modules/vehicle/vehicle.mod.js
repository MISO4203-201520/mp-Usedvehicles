(function (ng) {
    var mod = ng.module('vehicleModule', ['ngCrud']);

    mod.constant('vehicleContext', 'vehicles');

    mod.constant('vehicleModel', {
        fields: [{
                name: 'name',
                displayName: 'Name',
                type: 'String',
                required: true
            }, {
                name: 'description',
                displayName: 'Description',
                type: 'String',
                required: true
            }, {
                name: 'color',
                displayName: 'Color',
                type: 'String',
                required: true
            }, {
                name: 'model',
                displayName: 'Model',
                type: 'String',
                required: true
            }, {
                name: 'capacity',
                displayName: 'Capacity',
                type: 'Integer',
                required: true
            }, {
                name: 'brand',
                displayName: 'Brand',
                type: 'String',
                required: true
            }, {
                name: 'image',
                displayName: 'Image',
                type: 'String',
                required: true
            }]});
})(window.angular);
