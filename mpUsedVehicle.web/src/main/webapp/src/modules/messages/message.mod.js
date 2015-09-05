(function (ng) {
    var mod = ng.module('messageModule', ['ngCrud']);

    mod.constant('messageContext', 'messages');

    mod.constant('messageModel', {
        fields: [{
                name: 'question',
                displayName: 'Question',
                type: 'String',
                required: true
            }]});
})(window.angular);
