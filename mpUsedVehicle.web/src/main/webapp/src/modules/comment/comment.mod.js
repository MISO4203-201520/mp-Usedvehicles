(function (ng) {
    var mod = ng.module('commentModule', ['ngCrud']);

    mod.constant('commentContext', 'comments');

    mod.constant('commentModel', {
        fields: [{
                name: 'description',
                displayName: 'description',
                type: 'String',
                required: true
            }, {
                name: 'client',
                displayName: 'Client',
                type: 'Reference',
                service: 'clientService',
                required: true
            }]});
})(window.angular);
