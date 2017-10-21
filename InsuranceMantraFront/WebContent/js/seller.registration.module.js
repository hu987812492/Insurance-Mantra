/**
 * 
 */
var registrationModule = angular.module("seller.registration.module", []);
registrationModule.controller('sellerRegistrationController', function($location,
		$scope, $uibModal, registrationService, $http) {

	var sellerRegCtrl = this;

	sellerRegCtrl.registration = {
		brandName : "",
		creationDate :"",
		brandEmail :"",
		password :"",
		brandAddress :"",
		brandCity :"",
		brandState :"",
		brandCountry :"",
		brandPhone :"",
		brandStatus :"Pending"
	}
	

	sellerRegCtrl.dobOptions = {
		maxDate : new Date(),
		popup : false,
		format : 'dd-MMMM-yyyy',
		altInputFormats : [ 'M!/d!/yyyy' ]
	};

	sellerRegCtrl.setUpDob = function() {
		var todayDate = new Date();
		sellerRegCtrl.dobOptions.maxDate = new Date(todayDate.getFullYear() - 18,
				todayDate.getMonth(), todayDate.getDate());
		sellerRegCtrl.registration.creationDate = sellerRegCtrl.dobOptions.maxDate;
	};

	sellerRegCtrl.dateOptions = {
		dateDisabled : disabled,
		formatYear : 'yy',
		maxDate : sellerRegCtrl.dobOptions.maxDate,
		// minDate : new Date(),
		startingDay : 1
	};
	// calling today function
	sellerRegCtrl.setUpDob();

	// Disable weekend selection
	function disabled(data) {
		// var date = data.date, mode = data.mode;
		// return mode === 'day'
		// && (date.getDay() === 0 || date.getDay() === 6);
		return false;
	}

	sellerRegCtrl.open = function() {
		sellerRegCtrl.dobOptions.popup = true;
	};

	sellerRegCtrl.cancel = function() {
		$location.path('/');
	}

	sellerRegCtrl.register = function() {
		console.log(sellerRegCtrl.registration);
		//registrationService.register(sellerRegCtrl.registration, callbackSuccess,
			//	callbackError);
		
		//alert("checj");
		 //alert(sellerRegCtrl.registration);
		 
		$http({
			method : 'POST',
			url : 'http://localhost:8080/InsuranceMantra/rest/seller/registration',
			data : angular.toJson(sellerRegCtrl.registration),
			dataType: 'json',
			headers : {
				'Accept': 'application/json',
				'Content-Type' : 'application/json'
			}
		}).then(function successCallback(response) {
			//$scope.planDetail = response.data;
			sellerRegCtrl.openComponentModal('Registration Successful');
			$location.path('/');
			
		}, function errorCallback(response) {
			sellerRegCtrl.openComponentModal('Registration was not Successful');
			console.log(response.statusText);
		})
		
		
		
		

	}

	sellerRegCtrl.error = false;
	sellerRegCtrl.message = "";

	var callbackSuccess = function(data, headers) { // Status
		// Code:200
		if (data.success) {
			sellerRegCtrl.openComponentModal('Seller Registration Successful.');
			$location.path('/');

		} else {
			sellerRegCtrl.message = data.message;
			sellerRegCtrl.error = true;
		}

	};

	var callbackError = function(data, headers) {
		sellerRegCtrl.message = data.message;
		sellerRegCtrl.error = true;

	};

	sellerRegCtrl.openComponentModal = function(msgToDisplay) {
		var modalInstance = $uibModal.open({
			animation : true,
			component : 'successComponent',
			resolve : {
				msg : function() {
					return msgToDisplay;
				}
			}
		});

	};

});

registrationModule.factory('registrationService', function($rootScope, $http,
		$timeout, $cookieStore, $window, APP_CONSTANT, AUTH_EVENTS) {
	var regService = {};

	regService.register = function(data, callback, callbackError) {
		if (APP_CONSTANT.DEMO) {

			/*
			 * Dummy authentication for testing, uses $timeout to simulate api
			 * call ----------------------------------------------
			 */
			$timeout(function() {

				var response;
				if (data.username === 'test' && data.password === 'test') {
					response = {
						success : true,
					};
				} else {
					response = {
						message : 'Registration was not successful'
					};
				}

				callback(response);
			}, 1000);
		} else {

			/*
			 * Use this for real authentication
			 * ----------------------------------------------
			 */
			$http.post(APP_CONSTANT.REMOTE_HOST + '/registration', data

			)
			// On Success of $http call
			.success(function(data, status, headers, config) {
				callback(data, headers);
			}).error(function(data, status, headers, config) { // IF
				// STATUS
				// CODE
				// NOT
				// 200
				callbackError(data, headers);
			});
		}

	};
	
	return regService;

});
