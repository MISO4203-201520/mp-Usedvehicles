(function (ng) {
    var mod = ng.module('userModule', ['ngCrud']);

    mod.constant('userContext', 'users');
    
    mod.constant('userModel', {
        fields: [{
                name: 'name',
                displayName: 'Name',
                type: 'String',
                required: true
            }, {
                name: 'rememberMe',
                displayName: 'Is provider?',
                type: 'Boolean',
                required: true
            }]});
    
})(window.angular);
