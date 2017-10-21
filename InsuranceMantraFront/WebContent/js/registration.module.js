/**
 * 
 */
var registrationModule = angular.module("registration.module", []);
registrationModule.controller('registrationController', function($location,
		$scope, $uibModal, $http, registrationService) {

	var regCtrl = this;

	regCtrl.registration = {
				 userName:"",
				 password:"",
				 userEmail:"",
				 userRole:"",
				 userDob:"",
				 userAddress:"",
				 userCity:"",
				 userState:"",
				 userCountry:"",
				 userPhone:"",
				 creationDate:"",
				 name:""
	};

	regCtrl.dobOptions = {
		maxDate : new Date(),
		popup : false,
		format : 'dd-MMMM-yyyy',
		altInputFormats : [ 'M!/d!/yyyy' ]
	};

	regCtrl.setUpDob = function() {
		var todayDate = new Date();
		regCtrl.dobOptions.maxDate = new Date(todayDate.getFullYear() - 18,
				todayDate.getMonth(), todayDate.getDate());
		regCtrl.registration.userDob = regCtrl.dobOptions.maxDate;
	};

	regCtrl.dateOptions = {
		dateDisabled : disabled,
		formatYear : 'yy',
		maxDate : regCtrl.dobOptions.maxDate,
		// minDate : new Date(),
		startingDay : 1
	};
	// calling today function
	regCtrl.setUpDob();

	// Disable weekend selection
	function disabled(data) {
		// var date = data.date, mode = data.mode;
		// return mode === 'day'
		// && (date.getDay() === 0 || date.getDay() === 6);
		return false;
	}

	regCtrl.open = function() {
		regCtrl.dobOptions.popup = true;
	};

	regCtrl.cancel = function() {
		$location.path('/');
	}

	regCtrl.register = function() {
		console.log(regCtrl.registration);
		//registrationService.register(regCtrl.registration, callbackSuccess,
			//	callbackError);
		
		
		$http({
			method : 'POST',
			url : 'http://localhost:8080/InsuranceMantra/rest/customers/registration',
			data : angular.toJson(regCtrl.registration),
			dataType: 'json',
			headers : {
				'Accept': 'application/json',
				'Content-Type' : 'application/json'
			}
		}).then(function successCallback(response) {
			//$scope.planDetail = response.data;
			regCtrl.openComponentModal('Registration Successful');
			$location.path('/');
			
		}, function errorCallback(response) {
			regCtrl.openComponentModal('Registration was not Successful');
			console.log(response.statusText);
		})
		

	}

	regCtrl.error = false;
	regCtrl.message = "";

	var callbackSuccess = function(data, headers) { // Status
		// Code:200
		if (data.success) {
			regCtrl.openComponentModal('Registration Successful');
			$location.path('/');

		} else {
			regCtrl.message = data.message;
			regCtrl.error = true;
		}

	};

	var callbackError = function(data, headers) {
		regCtrl.message = data.message;
		regCtrl.error = true;

	};

	regCtrl.openComponentModal = function(msgToDisplay) {
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
		
		alert("alertch1" + data)
		
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
			alert("URL" + APP_CONSTANT.REMOTE_HOST + '/customers/registration');
			$http.post('http://localhost:8080/InsuranceMantra/rest/customers/registration', data

			)
			// On Success of $http call
			.success(function(data, status, headers, config) {
				callback(data, headers);
			}).error(function(data, status, headers, config) { // IF
				// STATUS
				// CODE
				// NOT
				// 200
				data.message="something went wrong";
				callbackError(data, headers);
			});
		}

	};
	
	return regService;

});
