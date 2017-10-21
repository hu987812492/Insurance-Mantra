var app = angular.module("app");

app.controller("menuController", function($scope, $rootScope, $window, $http) {

	$scope.catDatalist = []; 
	$scope.brandDataList = [];

	$http({
		method : 'GET',
		url : 'http://localhost:8080/InsuranceMantra/rest/front/categories'
	}).then(function successCallback(response) {
		$scope.catDatalist = response.data;
	}, function errorCallback(response) {
		console.log(response.statusText);
	});

	$http({
		method : 'GET',
		url : 'http://localhost:8080/InsuranceMantra/rest/front/brands'
	}).then(function successCallback(response) {
		$scope.brandDataList = response.data;
	}, function errorCallback(response) {
		console.log(response.statusText);
	});

	$scope.menus = [
	                {
	                	title: "Category", 
	                	action: "/category", 
	                	catList: []
	                },
	                {
	                	title: "Brand", 
	                	action: "/brand", 
	                	brandList: []
	                },
	                {
	                	title: "Become A Seller",
	                	action: "/seller"
	                }
	                ]




	$rootScope.userSession;
	$rootScope.showCustomerMenu = false;

	$scope.init = function () {
		$scope.parentmethod();
	}


	$rootScope.$on("CallParentMethod", function(){
		$scope.parentmethod();
	});

	$scope.parentmethod = function() {
		// task

		var globals = JSON.parse($window.localStorage.getItem("globals"));
		if(globals){
			console.log('globals exits');
			$('div#guest').hide();
			$('div#logout').show();
			$rootScope.userSession = globals.userSession;
//			if($rootScope.userSession.role === 'customer'){
//			console.log('customer is valid');
//			$rootScope.showCustomerMenu = true;
//			console.log($scope.showCustomerMenu);
//			$('div#custMenu').show();
//			// $scope.$digest()
//			}

		}else{
//			$('div#custMenu').hide();
//			$('div#merchantMenu').hide();
			$('div#guest').show();
			$('div#logout').hide();
			console.log('globals note exits');
		}

	}


});
$(document).ready(function() {});



/*
app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "productpages/welcomeFirst.html"
     })

     .when("/category", {
        templateUrl : "{{$location.path()}}"
     })

     .when("/brand", {
        templateUrl : "{{$location.path()}}"
     })

    .when("/seller", {
        templateUrl : "productpages/sellerRegistration.html"
    })

    .when("/subCategoryList/:category", {
        templateUrl : "productpages/getAllSubCategoryList.html",
        controller  : "subCategoryController"
    })

    .when("/subCategoryListDetail/:getSubCat", {
        templateUrl : "productpages/getSubCategoryDetailWithProductList.html",
        controller  : "subCategoryProductController"
    })

    .when("/brandList/:brandDetail", {
        templateUrl : "productpages/getBrandDetailsWithProductList.html",
        controller  : "brandProductController"
    })

    .when("/planDescription/:planId", {
        templateUrl : "productpages/productDescription.html",
        controller  : "planDescriptionController"
    })  

     .when("/requestQuote", {
        templateUrl : "productpages/requestQuote.html",
        controller  : "requestQuoteController"
    })

    .when("/logout", {
        templateUrl : "userpages/userProfileLayout.html",
        controller  : "userProfileController"
    })

    .when("/logout", {
        templateUrl : "sellerpages/sellerProfileLayout.html",
        controller  : "sellerProfileController"
    })

    .when("/logout", {
        templateUrl : "adminpages/adminProfileLayout.html",
        controller  : "adminProfileController"
    })
});*/


app.controller("subCategoryController", function($scope, $routeParams, $http) {

	$scope.category = [];
	$scope.category = angular.fromJson($routeParams.category); 
	$scope.subCat;
	var id =  $scope.category.categoryId ;

	console.log(id);
	$http({
		method : 'GET',
		url : 'http://localhost:8080/InsuranceMantra/rest/front/category/'+id
	}).then(function successCallback(response) {
		$scope.subCat = response.data;
	}, function errorCallback(response) {
		console.log(response.statusText);
	});
});


app.controller("brandProductController", function($scope, $routeParams, $http) {

	$scope.brandProductData = [];
	$scope.brandProductData = angular.fromJson($routeParams.brandDetail); 
	$scope.brandDetailWithProductList;
	var id = $scope.brandProductData.id;
	$http({
		method : 'GET',
		url : 'http://localhost:8080/insurance/rest/shop/10000' //+id
	}).then(function successCallback(response) {
		$scope.brandDetailWithProductList = response.data;
	}, function errorCallback(response) {
		console.log(response.statusText);
	});
});


app.controller("subCategoryProductController", function($scope, $routeParams, $http) {
	$scope.subCategory = [];
	$scope.subCategory = angular.fromJson($routeParams.getSubCat);
	$scope.subCategoryWithPlans = []; 
	var id = $scope.subCategory.subCategoryId;
	$http({
		method : 'GET',
		url : 'http://localhost:8080/InsuranceMantra/rest/front/subCategory/'+id
	}).then(function successCallback(response) {
		$scope.subCategoryWithPlans = response.data;
	}, function errorCallback(response) {
		console.log(response.statusText);
	});

	/*    $scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.numberOfPages=function(){
        return Math.ceil($scope.data.length/$scope.pageSize);                
    }*/

});


app.controller("planDescriptionController", function($scope, $routeParams, $http){

	var planId = $routeParams.planId;
	$scope.planDetail;
	$http({
		method : 'GET',
		url : 'http://localhost:8080/InsuranceMantra/rest/front/plan/'+planId
	}).then(function successCallback(response) {
		$scope.planDetail = response.data;
	}, function errorCallback(response) {
		console.log(response.statusText);
	})
});

app.controller("requestQuoteController", function($scope, $routeParams, $http) {


	$scope.requestQuote = {
			requesterName:"",
			requesterEmail:"",
			requesterPhone:"",
			requesterDob:"",
			requesterIncome:"",
			orderStatus:"",
			orderCreationDate:"",
			orderCompletionDate:""
	};

	$scope.submitQuote = function () {

		$http({
			method : 'POST',
			//  url : 'http://localhost:8080/InsuranceMantra/rest/customers/'+customerId+'/plan/'+planId+'/seller/'+brandId+'/order',
			url : 'http://localhost:8080/InsuranceMantra/rest/customers/2/plan/1/seller/1/order',
			data : angular.toJson($scope.requestQuote),
			dataType: 'json',
			headers : {
				'Accept': 'application/json',
				'Content-Type' : 'application/json'
			}
		}).then( _success, _error );
		console.log($scope.requestQuote);
	}
});

//We already have a limitTo filter built-in to angular,
//let's make a startFrom filter
app.filter('startFrom', function() {
	return function(input, start) {
		start = +start; //parse to int
		return input.slice(start);
	}
});






app.controller('userProfileController', function($scope, $routeParams,  $http) {

	$scope.gotopage = '';

	$scope.userDetail = {

			userId: "",
			userName: "",
			password: "",
			userEmail: "",
			userRole: "",
			userDob: "",
			userAddress: "",
			userCity: "",
			userState: "",
			userCountry: "",
			userPhone: "",
			creationDate: "",
			name: ""

	};

	$scope.displayUserInfo = function(flow, userName){
		$scope.gotopage = flow;
		$scope.userDetail;
		$http({
			method : 'GET',
			url : 'http://localhost:8080/InsuranceMantra/rest/customers/'+userName
		}).then(function successCallback(response) {
			$scope.userDetail = response.data;
		}, function errorCallback(response) {
			console.log(response.statusText);
		})
	};




	$scope.updateUserProfile = function(){

		$http({
			method : 'PUT',
			url : 'http://localhost:8080/InsuranceMantra/rest/customers/update',
			data : angular.toJson($scope.userDetail),
			dataType: 'json',
			headers : {
				'Accept': 'application/json',
				'Content-Type' : 'application/json'
			}
		}).then( _success, _error );
		console.log($scope.userDetail);
	};

	$scope.displayOrderHistory = function(flow, userId){

		$scope.gotopage = flow;
		$scope.orderDetailList = [];
		$http({
			method : 'GET',
			url : 'http://localhost:8080/InsuranceMantra/rest/customers/'+userId+'/orders'
		}).then(function successCallback(response) {
			$scope.orderDetailList = response.data;
		}, function errorCallback(response) {
			console.log(response.statusText);
		})
	};


})

app.controller('sellerProfileController', function($scope, $routeParams,  $http) {

	$scope.gotopage = '';

	$scope.sellerDetail = {
			brandId:"",
			brandName: "",
			creationDate:"",
			brandEmail:"",
			password:"",
			brandAddress:"",
			brandCity:"",
			brandState:"",
			brandCountry:"",
			brandPhone:"",
			brandStatus:""	
	};

	$scope.displaySellerInfo = function(flow, brandName){
		$scope.gotopage = flow;
		$scope.sellerDetail;
		$http({
			method : 'GET',
			url : 'http://localhost:8080/InsuranceMantra/rest/seller/'+brandName
		}).then(function successCallback(response) {
			$scope.sellerDetail = response.data;
		}, function errorCallback(response) {
			console.log(response.statusText);
		})
	};

	$scope.updateSellerProfile = function(){

		$http({
			method : 'PUT',
			url : 'http://localhost:8080/InsuranceMantra/rest/seller/update',
			data : angular.toJson($scope.sellerDetail),
			dataType: 'json',
			headers : {
				'Accept': 'application/json',
				'Content-Type' : 'application/json'
			}
		}).then( _success, _error );
		console.log($scope.sellerDetail);
	};

	$scope.displaySellerOrderHistory = function(flow, brandId){
		$scope.gotopage = flow;
		$scope.sellerOrderDetailList = [];
		$http({
			method : 'GET',
			url : 'http://localhost:8080/InsuranceMantra/rest/customers/'+brandId+'/orders'
		}).then(function successCallback(response) {
			$scope.sellerOrderDetailList = response.data;
		}, function errorCallback(response) {
			console.log(response.statusText);
		})
	};
	
	
	$scope.policyPlan = {
		 planName:"",
		 brandId:"",
		 subCategoryId:"",
		 minEntryAge:"",
		 maxEntryAge:"",
		 planMaturity:"",
		 premiumAmount:"",
		 sumAssured:"",
		 planDesc:"",
		 planFeatures:"",
		 creationDate:""
		};

	$scope.displaySellerAddPlanPage =function (flow){
		$scope.gotopage = flow;
		// Rest call for getting all sub category //
		$scope.subCategorytList = [];
		$http({
			method : 'GET',
			url : 'http://localhost:8080/InsuranceMantra/rest/front/category/sub-category'
		}).then(function successCallback(response) {
			$scope.subCategorytList = response.data;
		}, function errorCallback(response) {
			console.log(response.statusText);
		})
				
	};

	$scope.addPlan = function (flow, brandId, subCategoryId) {

		$http({
			method : 'POST',
			//  url : 'http://localhost:8080/InsuranceMantra/rest/customers/'+customerId+'/plan/'+planId+'/seller/'+brandId+'/order',
			url : 'http://localhost:8080/InsuranceMantra/rest/'+brandId+'/sub-category/'+subCategoryId+'/plan',
			data : angular.toJson($scope.policyPlan),
			dataType: 'json',
			headers : {
				'Accept': 'application/json',
				'Content-Type' : 'application/json'
			}
		}).then( _success, _error );
		console.log($scope.policyPlan);
	};
	
	$scope.displayProductList = function(flow, brandId){
		$scope.gotopage = flow;
		$scope.sellerProductList = [];
		$http({
			method : 'GET',
			url : 'http://localhost:8080/InsuranceMantra/rest/seller/'+brandId+'/plans'
		}).then(function successCallback(response) {
			$scope.sellerProductList = response.data;
		}, function errorCallback(response) {
			console.log(response.statusText);
		})
	};



});

app.controller('adminProfileController', function($scope, $routeParams,  $http) {

	$scope.gotopage = '';
	
	$scope.categoryDetail = {
			 categoryName:"",
			 creationDate:"",
			 categoryDesc:""
	};

	$scope.displayPage = function(flow) {

		$scope.gotopage = flow;
	}
	
	$scope.displayCreateCategory = function() {
		
		$http({
			method : 'POST',
			//  url : 'http://localhost:8080/InsuranceMantra/rest/customers/'+customerId+'/plan/'+planId+'/seller/'+brandId+'/order',
			url : 'http://localhost:8080/InsuranceMantra/rest/admin/categories',
			data : angular.toJson($scope.categoryDetail),
			dataType: 'json',
			headers : {
				'Accept': 'application/json',
				'Content-Type' : 'application/json'
			}
		}).then( _success, _error );
		console.log($scope.policyPlan);
	}


});


function _success(response) {
	// _refreshCountryData();
	//_clearFormData()
	console.log("Successful Response"+response);
}

function _error(response) {
	console.log(response.statusText);
}


