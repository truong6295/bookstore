
var app = angular.module('crudApp',['ui.router','ngStorage']);
///
app.constant('urls', {
    BASE: 'http://localhost:8080',
    BOOKSTORE_SERVICE_API : 'http://localhost:8080/app/book/'
});
////
app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {
 
        $stateProvider
        	.state('home',{
        		url:'/',
        		templateUrl:'/'
        	})
            .state('bookstore', {
                url: '/bookstore',
                templateUrl: 'partials/bookstore',
                controller:'BookController',
                controllerAs:'ctrl',
                resolve: {
                    books: function ($q, BookService) {
                        console.log('Load all book');
                        var deferred = $q.defer();
                        BookService.loadAllBooks().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            })
        $urlRouterProvider.otherwise('/');
    }

]);
