'use strict';
 
angular.module('crudApp').controller('BookController',
    ['BookService', '$scope',  function( BookService, $scope) {
 
        var self = this;
        self.book = {};
        self.books=[];
        self.bookCatergorys=[];
        self.bookNames=[];
        self.bookAuthors=[];
        self.bookPrices=[];
 
        self.submit = submit;
        self.editBook = editBook;
        self.reset = reset;
        
        self.loadAllBooks= loadAllBooks,
        self.getAllBooks= getAllBooks,
        self.getIdBook= getIdBook,
        self.createBook= createBook,
        self.updateBook= updateBook,
        self.removeBook= removeBook,
        self.getlistCatergory= getlistCatergory,
        self.getName= getName,
        self.getAuthor= getAuthor,
        self.getPrice= getPrice
 
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
 
        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
 
        function submit() {
            console.log('Submitting');
            if (self.book.idbook === undefined || self.book.idbook === null) {
                console.log('Saving New book', self.book);
                createTask(self.book);
            } else {
                updateBook(self.book, self.book.idbook);
                console.log('book updated with id ', self.book.idbook);
            }
        }
 
        function createBook(book) {
            console.log('About to create book');
            BookService.createBook(book)
                .then(
                    function (response) {
                        console.log('book created successfully');
                        self.successMessage = 'book created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.task={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating User');
                        self.errorMessage = 'Error while creating User: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }
 
 
        function updateBook(book, id){
            console.log('About to update book');
            BookService.updateBook(book, id)
                .then(
                    function (response){
                        console.log('book updated successfully');
                        self.successMessage='book updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating book');
                        self.errorMessage='Error while updating book '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }
 
 
        function removeBook(id){
            console.log('About to remove book with id '+id);
            BookService.removeBook(id)
                .then(
                    function(){
                        console.log('book '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing book '+id +', Error :'+errResponse.data);
                    }
                );
        }
 
 
        function getAllBooks(){
            return BookService.getAllBooks();
        }
 
        function editBook(id) {
            self.successMessage='';
            self.errorMessage='';
            BookService.getIdBook(id).then(
                function (book) {
                    self.book = book;
                },
                function (errResponse) {
                    console.error('Error while removing book ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.book={};
            $scope.myForm.$setPristine(); //reset Form
        }
        function getIdBook(id){
        	BookService.getIdBook(id).then(
                    function (book) {
                        self.book = book;
                    },
                    function (errResponse) {
                        console.error('Error while removing book ' + id + ', Error :' + errResponse.data);
                    }
        	);
        }
        function getlistCatergory(catergory,page,pageSize){
        	return BookService.getlistCatergory(catergory,page,pageSize);
        }
        function getName(name,page,pageSize){
        	return BookService.getName(name,page,pageSize);
        }
        function getAuthor(author,page,pageSize){
        	return BookService.getAuthor(author,page,pageSize);
        }
        function getPrice(price,page,pageSize){
        	return BookService.getPrice(price,page,pageSize);
        }
    }
    ]);