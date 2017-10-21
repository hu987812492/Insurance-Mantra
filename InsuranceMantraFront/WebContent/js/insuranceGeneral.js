var app = angular.module("app");

app.controller("menuController", function($scope, $rootScope, $window, $http,
		APP_CONSTANT) {

	$scope.catDatalist = [];
	$scope.brandDataList = [];

	$http({
		method : 'GET',
		url : APP_CONSTANT.REMOTE_HOST + '/front/categories'
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

	$scope.menus = [ {
		title : "Category",
		action : "/category",
		catList : []
	}, {
		title : "Brand",
		action : "/brand",
		brandList : []
	}, {
		title : "Become A Seller",
		action : "/seller"
	} ]

	$rootScope.userSession;
	$rootScope.showCustomerMenu = false;

	$scope.init = function() {
		$scope.parentmethod();
	}

	$rootScope.$on("CallParentMethod", function() {
		$scope.parentmethod();
	});

	$scope.parentmethod = function() {
		// task

		var globals = JSON.parse($window.localStorage.getItem("globals"));
		if (globals) {
			console.log('globals exits');
			$('div#guest').hide();
			$('div#logout').show();
			$rootScope.userSession = globals.userSession;
			// if($rootScope.userSession.role === 'customer'){
			// console.log('customer is valid');
			// $rootScope.showCustomerMenu = true;
			// console.log($scope.showCustomerMenu);
			// $('div#custMenu').show();
			// // $scope.$digest()
			// }

		} else {
			// $('div#custMenu').hide();
			// $('div#merchantMenu').hide();
			$('div#guest').show();
			$('div#logout').hide();
			console.log('globals note exits');
		}

	}

});
$(document).ready(function() {
});

app.controller("subCategoryController", function($scope, $routeParams, $http) {

	$scope.category = [];
	$scope.category = angular.fromJson($routeParams.category);
	$scope.subCat;
	var id = $scope.category.categoryId;

	console.log(id);
	$http({
		method : 'GET',
		url : 'http://localhost:8080/InsuranceMantra/rest/front/category/' + id
	}).then(function successCallback(response) {
		$scope.subCat = response.data;
	}, function errorCallback(response) {
		console.log(response.statusText);
	});
});

app.controller("brandProductController", function($scope, $routeParams, $http) {

	$scope.brandProductDataList;

	$scope.brandDisplayName = $routeParams.brandName;
	var id = $routeParams.brandId;

	$http({
		method : 'GET',
		url : 'http://localhost:8080/InsuranceMantra/rest/front/brand/' + id

	}).then(function successCallback(response) {
		$scope.brandProductDataList = response.data;
	}, function errorCallback(response) {
		// alert(response.statusText);
		console.log(response.statusText);
	});

});

app
		.controller(
				"subCategoryProductController",
				function($scope, $routeParams, $http) {
					$scope.subCategory = [];
					$scope.subCategory = angular
							.fromJson($routeParams.getSubCat);
					$scope.subCategoryWithPlans = [];
					var id = $scope.subCategory.subCategoryId;
					$http(
							{
								method : 'GET',
								url : 'http://localhost:8080/InsuranceMantra/rest/front/subCategory/'
										+ id
							}).then(function successCallback(response) {
						$scope.subCategoryWithPlans = response.data;
					}, function errorCallback(response) {
						console.log(response.statusText);
					});

					/*
					 * $scope.currentPage = 0; $scope.pageSize = 10;
					 * $scope.numberOfPages=function(){ return
					 * Math.ceil($scope.data.length/$scope.pageSize); }
					 */

				});

app.controller("planDescriptionController", function($scope, $routeParams,
		$http) {

	var planId = $routeParams.planId;
	$scope.planDetail;
	$http({
		method : 'GET',
		url : 'http://localhost:8080/InsuranceMantra/rest/front/plan/' + planId
	}).then(function successCallback(response) {
		$scope.planDetail = response.data;
	}, function errorCallback(response) {
		console.log(response.statusText);
	})
});

app.controller("requestQuoteController", function($scope, $rootScope, $routeParams, $http, $uibModal, $location) {
					var quoteCtl = this;
					$scope.requestQuote = {
						requesterName : "",
						requesterEmail : "",
						requesterPhone : "",
						requesterDob : "",
						requesterIncome : "",
						orderStatus : "",
						orderCreationDate : "",
						orderCompletionDate : ""
					};

					$scope.submitQuote = function() {
						$http(
								{
									method : 'POST',
									url : 'http://localhost:8080/InsuranceMantra/rest/customers/'
											+ $rootScope.globals.userSession.id
											+ '/plan/'
											+ $routeParams.planId
											+ '/seller/'
											+ $routeParams.brandId
											+ '/order',
									data : angular.toJson($scope.requestQuote),
									dataType : 'json',
									headers : {
										'Accept' : 'application/json',
										'Content-Type' : 'application/json'
									}
								})
								.then(
										function successCallback(response) {
											// $scope.orderDetailList =
											// response.data;
											quoteCtl
													.openComponentModal("Order Requested. Seller will get back to you shortly!");
											$location.path('/');
										}, function errorCallback(response) {
											console.log(response.statusText);
										});

						// quoteCtl.openComponentModal("Order Requested. Seller
						// will get back to you shortly!");
					}

					quoteCtl.openComponentModal = function(msgToDisplay) {
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

// We already have a limitTo filter built-in to angular,
// let's make a startFrom filter
app.filter('startFrom', function() {
	return function(input, start) {
		start = +start; // parse to int
		return input.slice(start);
	}
});

// =================================================================USER
// CONTROLLER====================================================================
// =================================================================USER
// CONTROLLER====================================================================

app
		.controller(
				'userProfileController',
				function($scope, $routeParams, $http, $uibModal) {

					var thectl = this;

					$scope.gotopage = '';

					$scope.userDetail = {

						userId : "",
						userName : "",
						password : "",
						userEmail : "",
						userRole : "",
						userDob : "",
						userAddress : "",
						userCity : "",
						userState : "",
						userCountry : "",
						userPhone : "",
						creationDate : "",
						name : ""

					};

					$scope.displayUserInfo = function(flow, userName) {
						$scope.gotopage = flow;
						$scope.userDetail;
						$http(
								{
									method : 'GET',
									url : 'http://localhost:8080/InsuranceMantra/rest/customers/'
											+ userName
								}).then(function successCallback(response) {
							$scope.userDetail = response.data;
						}, function errorCallback(response) {
							console.log(response.statusText);
						})
					};

					$scope.updateUserProfile = function() {

						$http(
								{
									method : 'PUT',
									url : 'http://localhost:8080/InsuranceMantra/rest/customers/update',
									data : angular.toJson($scope.userDetail),
									dataType : 'json',
									headers : {
										'Accept' : 'application/json',
										'Content-Type' : 'application/json'
									}
								}).then(function successCallback(response) {
							// $scope.orderDetailList = response.data;
							thectl.openComponentModal("Updated Profile");
						}, function errorCallback(response) {
							console.log(response.statusText);
						});
					};

					$scope.displayOrderHistory = function(flow, userId) {

						$scope.gotopage = flow;
						$scope.orderDetailList = [];
						$http(
								{
									method : 'GET',
									url : 'http://localhost:8080/InsuranceMantra/rest/customers/'
											+ userId + '/orders'
								}).then(function successCallback(response) {
							$scope.orderDetailList = response.data;
						}, function errorCallback(response) {
							console.log(response.statusText);
						})
					};

					thectl.openComponentModal = function(msgToDisplay) {
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

				})

// ================================================================SELLER/BRAND
// CONTROLLER==============================================

app
		.controller(
				'sellerProfileController',
				function($scope, $routeParams, $http, $uibModal) {

					var sellerCstl = this;

					$scope.gotopage = '';

					$scope.sellerDetail = {
						brandId : "",
						brandName : "",
						creationDate : "",
						brandEmail : "",
						password : "",
						brandAddress : "",
						brandCity : "",
						brandState : "",
						brandCountry : "",
						brandPhone : "",
						brandStatus : ""
					};

					$scope.displaySellerInfo = function(flow, brandName) {
						$scope.gotopage = flow;
						$scope.sellerDetail;
						$http(
								{
									method : 'GET',
									url : 'http://localhost:8080/InsuranceMantra/rest/seller/'
											+ brandName
								}).then(function successCallback(response) {
							$scope.sellerDetail = response.data;
						}, function errorCallback(response) {
							console.log(response.statusText);
						})
					};

					$scope.updateSellerProfile = function() {

						$http(
								{
									method : 'PUT',
									url : 'http://localhost:8080/InsuranceMantra/rest/seller/update',
									data : angular.toJson($scope.sellerDetail),
									dataType : 'json',
									headers : {
										'Accept' : 'application/json',
										'Content-Type' : 'application/json'
									}
								}).then(_success, _error);
						console.log($scope.sellerDetail);
					};

					$scope.displaySellerOrderHistory = function(flow, brandId) {
						$scope.gotopage = flow;
						$scope.sellerOrderDetailList = [];
						$http(
								{
									method : 'GET',
									url : 'http://localhost:8080/InsuranceMantra/rest/seller/'
											+ brandId + '/orders'
								}).then(function successCallback(response) {
							$scope.sellerOrderDetailList = response.data;
						}, function errorCallback(response) {
							console.log(response.statusText);
						})
					};

					$scope.displaySellerAddPlanPage = function(flow) {
						$scope.gotopage = flow;
						// Rest call for getting all sub category //
						$scope.subCategoryList = [];
						$http(
								{
									method : 'GET',
									url : 'http://localhost:8080/InsuranceMantra/rest/front/category/sub-category'
								}).then(function successCallback(response) {
							$scope.subCategoryList = response.data;
						}, function errorCallback(response) {
							console.log(response.statusText);
						})

					};

					$scope.policyPlanSeller = {
						planName : "",
						minEntryAge : "",
						maxEntryAge : "",
						planMaturity : "",
						premiumAmount : "",
						sumAssured : "",
						planDesc : "",
						planFeatures : "",
						creationDate : ""
					};

					$scope.addPlanForSeller = function(userId) {

						$http(
								{
									method : 'POST',
									// url :
									// 'http://localhost:8080/InsuranceMantra/rest/admin/categories',
									// /{brandId}/sub-category/{subCategoryId}/plan
									url : 'http://localhost:8080/InsuranceMantra/rest/seller/'
											+ userId
											+ '/sub-category/'
											+ $scope.policyPlanSeller.subCategoryId
											+ '/plan',
									data : angular
											.toJson($scope.policyPlanSeller),
									dataType : 'json',
									headers : {
										'Accept' : 'application/json',
										'Content-Type' : 'application/json'
									}
								})
								.then(
										function successCallback(response) {
											sellerCstl
													.openComponentModal("POLICY PLAN FOR SELLER "
															+ response.statusText);
											console
													.log($scope.policyPlanSeller);
										}, function errorCallback(response) {
											console.log(response.statusText);
										})
					};

					$scope.displayProductList = function(flow, brandId) {
						$scope.gotopage = flow;
						$scope.sellerProductList = [];
						$http(
								{
									method : 'GET',
									url : 'http://localhost:8080/InsuranceMantra/rest/seller/'
											+ brandId + '/plans'
								}).then(function successCallback(response) {
							$scope.sellerProductList = response.data;
						}, function errorCallback(response) {
							console.log(response.statusText);
						})
					};

					$scope.updateOrderStatusBasedOnId = function(orderId) {

						$http(
								{
									method : 'POST',
									url : 'http://localhost:8080/InsuranceMantra/rest/seller/'
											+ orderId + '/update',
									dataType : 'json',
									headers : {
										'Accept' : 'application/json',
										'Content-Type' : 'application/json'
									}
								})
								.then(
										function successCallback(response) {
											// $scope.orderDetailList =
											// response.data;
											sellerCstl
													.openComponentModal("Order has completed"
															+ response.statusText);
										}, function errorCallback(response) {
											console.log(response.statusText);
										});
					};
					
					// =========================== DISPLAY ALL POLICY PLAN AND DELETE ONE==============================================================================
					$scope.displaySellerPolicy = function(flow) {
						$scope.gotopage = flow;
						$scope.sellerPlanList = [];
						$http(
								{
									method : 'GET',
									url : 'http://localhost:8080/InsuranceMantra/rest/seller/'+$rootScope.globals.userSession.id+'/plans'
								}).then(function successCallback(response) {
							$scope.sellerPlanList = response.data;
						}, function errorCallback(response) {
							console.log(response.statusText);
						})
					};
					

					$scope.sellerDeletePlan = function(planId) {
					//	alert(brandId)
						$http(
								{
									method : 'POST',
									url : 'http://localhost:8080/InsuranceMantra/rest/seller/'+planId,
									dataType : 'json',
									headers : {
										'Accept' : 'application/json',
										'Content-Type' : 'application/json'
									}
								})
								.then(
										function successCallback(response) {
											//$scope.orderDetailList = response.data;
											sellerCstl
													.openComponentModal("Plan Deleted!"
															+ response.statusText);
										}, function errorCallback(response) {
											console.log(response.statusText);
										});

					}


					sellerCstl.openComponentModal = function(msgToDisplay) {
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

// =======================================================================ADMIN=======================================================================
// ========================================================================ADMIN======================================================================
app
		.controller(
				'adminProfileController',
				function($scope, $routeParams, $http, $uibModal) {

					var adminCstl = this;
					$scope.gotopage = '';

					$scope.totalCounts = {
						totalPolicy : "",
						totalCategory : "",
						totalSubCategory : "",
						totalSeller : "",
						totalCustomer : "",
						todayPolicy : "",
						todaySeller : "",
						todayCustomer : "",
						todayClosedOrder : "",
						totalOrderCount : ""
					};

					// ===============================================================
					// DISPLAY DASHBORD PAGE =================================
					$scope.displayDashPage = function(flow) {
						$scope.gotopage = flow;
						$scope.totalCounts;
						$http(
								{
									method : 'GET',
									url : 'http://localhost:8080/InsuranceMantra/rest/admin/totalCounts'
								}).then(function successCallback(response) {
							$scope.totalCounts = response.data;
						}, function errorCallback(response) {
							console.log(response.statusText);
						})
					};

					// ==================================== DISPLAY CAT PAGE
					// =======================================================
					$scope.displayCatPage = function(flow) {
						$scope.gotopage = flow;
					}

					// ============================== POST CREATE CAT DATA
					// ===================================

					$scope.categoryDetail = {
						categoryName : "",
						creationDate : "",
						categoryDesc : ""
					};

					$scope.createCategory = function() {

						$http(
								{
									method : 'POST',
									// url :
									// 'http://localhost:8080/InsuranceMantra/rest/customers/'+customerId+'/plan/'+planId+'/seller/'+brandId+'/order',
									url : 'http://localhost:8080/InsuranceMantra/rest/admin/categories',
									data : angular
											.toJson($scope.categoryDetail),
									dataType : 'json',
									headers : {
										'Accept' : 'application/json',
										'Content-Type' : 'application/json'
									}
								})
								.then(
										function successCallback(response) {
											adminCstl
													.openComponentModal("	Policy category created successfully!!"
															+ response.statusText);
											console.log(categoryDetail);
										},
										function errorCallback(response) {
											adminCstl
													.openComponentModal(" Policy category not created ! Something went wrong"
															+ response.statusText);
											console.log(response.statusText);
										})
					};

					// =================================DISPLAY SUB CAT
					// PAGE====================================

					$scope.displaySubCategoryAddPage = function(flow) {
						$scope.gotopage = flow;
						// Rest call for getting all sub category //
						$scope.categoryList = [];
						$http(
								{
									method : 'GET',
									url : 'http://localhost:8080/InsuranceMantra/rest/front/categories'
								}).then(function successCallback(response) {
							$scope.categoryList = response.data;
						}, function errorCallback(response) {
							console.log(response.statusText);
						})

					};

					// =========================================ADD SUB CAT PAGE
					// ======================================

					$scope.subCategoryDetail = {
						categoryId : "",
						subCategoryName : "",
						creationDate : "",
						subCategoryDesc : "",
						subCategoryFeatures : "",
						subCategoryBenefits : ""
					};

					$scope.addSubCategory = function() {

						$http(
								{
									method : 'POST',
									// url :
									// 'http://localhost:8080/InsuranceMantra/rest/customers/'+customerId+'/plan/'+planId+'/seller/'+brandId+'/order',
									url : 'http://localhost:8080/InsuranceMantra/rest/admin/'
											+ $scope.subCategoryDetail.categoryId
											+ '/subCategories',
									data : angular
											.toJson($scope.subCategoryDetail),
									dataType : 'json',
									headers : {
										'Accept' : 'application/json',
										'Content-Type' : 'application/json'
									}
								})
								.then(
										function successCallback(response) {
											adminCstl
													.openComponentModal("SUB-CATEGORY PLAN ADDED FOR ADMIN "
															+ response.statusText);
											console.log($scope.policyPlan);
										}, function errorCallback(response) {
											console.log(response.statusText);
										})
					};

					// ================================ DISPLAY ADD PLAN PAGE
					// ======================================

					$scope.displayAdminAddPlanPage = function(flow) {
						$scope.gotopage = flow;
						// Rest call for getting all sub category //

						$scope.subCategorytListData = [];
						$http(
								{
									url : 'http://localhost:8080/InsuranceMantra/rest/front/category/sub-category'
								// url :
								// 'http://localhost:8080/InsuranceMantra/rest/front/brands'
								}).then(function successCallback(response) {
							$scope.subCategorytListData = response.data;
						}, function errorCallback(response) {
							console.log(response.statusText);
						})

						$scope.brandList = [];

						$http(
								{
									method : 'GET',
									url : 'http://localhost:8080/InsuranceMantra/rest/front/brands'
								}).then(function successCallback(response) {
							$scope.brandList = response.data;
						}, function errorCallback(response) {
							console.log(response.statusText);
						})

					};

					// =============================== ADD PLAN
					// =======================================================

					$scope.policyPlan = {
						planName : "",
						minEntryAge : "",
						maxEntryAge : "",
						planMaturity : "",
						premiumAmount : "",
						sumAssured : "",
						planDesc : "",
						planFeatures : "",
						creationDate : ""
					};

					$scope.addPolicyPlan = function() {

						$http(
								{
									method : 'POST',
									// url :
									// 'http://localhost:8080/InsuranceMantra/rest/admin/categories',
									// /{brandId}/sub-category/{subCategoryId}/plan
									url : 'http://localhost:8080/InsuranceMantra/rest/seller/'
											+ $scope.policyPlan.brandId
											+ '/sub-category/'
											+ $scope.policyPlan.subCategoryId
											+ '/plan',
									data : angular.toJson($scope.policyPlan),
									dataType : 'json',
									headers : {
										'Accept' : 'application/json',
										'Content-Type' : 'application/json'
									}
								})
								.then(
										function successCallback(response) {
											adminCstl
													.openComponentModal("POLICY PLAN ADDED FOR ADMIN "
															+ response.statusText);
											console.log($scope.policyPlan);
										}, function errorCallback(response) {
											console.log(response.statusText);
										})
					};
					
// =========================== DISPLAY ALL SUB-CATEGORY AND DELETE ONE==============================================================================
					$scope.displaySubCategory = function(flow) {
						$scope.gotopage = flow;
						$scope.subCategoryList = [];
						$http(
								{
									method : 'GET',
									url : 'http://localhost:8080/InsuranceMantra/rest/front/category/sub-category'
								}).then(function successCallback(response) {
							$scope.subCategoryList = response.data;
						}, function errorCallback(response) {
							console.log(response.statusText);
						})
					};
					

					$scope.adminDeleteSubcate = function(subCategoryId) {
					//	alert(brandId)
						$http(
								{
									method : 'POST',
									url : 'http://localhost:8080/InsuranceMantra/rest/admin/subCategories/'+subCategoryId+'/delete',
									dataType : 'json',
									headers : {
										'Accept' : 'application/json',
										'Content-Type' : 'application/json'
									}
								})
								.then(
										function successCallback(response) {
											//$scope.orderDetailList = response.data;
											adminCstl
													.openComponentModal("Sub-category Deleted!"
															+ response.statusText);
										}, function errorCallback(response) {
											console.log(response.statusText);
										});

					}

// =========================== DISPLAY ALL POLICY PLAN AND DELETE ONE==============================================================================
					$scope.displayPolicyPlanPage = function(flow) {
						$scope.gotopage = flow;
						$scope.adminPlanList = [];
						$http(
								{
									method : 'GET',
									url : 'http://localhost:8080/InsuranceMantra/rest/front/policies'
								}).then(function successCallback(response) {
							$scope.adminPlanList = response.data;
						}, function errorCallback(response) {
							console.log(response.statusText);
						})
					};
					

					$scope.adminDeletePlan = function(planId) {
					//	alert(brandId)
						$http(
								{
									method : 'POST',
									url : 'http://localhost:8080/InsuranceMantra/rest/seller/'+planId,
									dataType : 'json',
									headers : {
										'Accept' : 'application/json',
										'Content-Type' : 'application/json'
									}
								})
								.then(
										function successCallback(response) {
											//$scope.orderDetailList = response.data;
											adminCstl
													.openComponentModal("Plan Deleted!"
															+ response.statusText);
										}, function errorCallback(response) {
											console.log(response.statusText);
										});

					}
//=============================DISPLAY ALL SELLER/BRAND AND UPDATE STATUS======================================================================
					$scope.displayPolicyBrandPage = function(flow) {
						$scope.gotopage = flow;
						$scope.adminBrandList = [];
						$http(
								{
									method : 'GET',
									url : 'http://localhost:8080/InsuranceMantra/rest/front/brands'
								}).then(function successCallback(response) {
							$scope.adminBrandList = response.data;
						}, function errorCallback(response) {
							console.log(response.statusText);
						})
					};

					//====================================================active/disable brand by admin =========================

					$scope.adminActivateSeller = function(brandId, status) {
						//alert(brandId)
						$http(
								{
									method : 'POST',
									url : 'http://localhost:8080/InsuranceMantra/rest/admin/'
											+ brandId + '/update',
									dataType : 'json',
									headers : {
										'Accept' : 'application/json',
										'Content-Type' : 'application/json'
									}
								})
								.then(
										function successCallback(response) {
											//$scope.orderDetailList = response.data;
											adminCstl
													.openComponentModal("Seller Status Updated!"
															+ response.statusText);
										}, function errorCallback(response) {
											console.log(response.statusText);
										});

					}

					
//=============================DISPLAY ALL Category AND delete=================================
					$scope.displayDeleteCatPage = function(flow) {
						$scope.gotopage = flow;
						$scope.categoryList = [];
						$http(
								{
									method : 'GET',
									url : 'http://localhost:8080/InsuranceMantra/rest/front/categories'
								}).then(function successCallback(response) {
							$scope.categoryList = response.data;
						}, function errorCallback(response) {
							console.log(response.statusText);
						})
					};


					$scope.adminDeleteCategory = function(categoryId) {
					//	alert(brandId)
						$http(
								{
									method : 'POST',
									url : 'http://localhost:8080/InsuranceMantra/rest/admin/categories/'+categoryId+'/delete',
									dataType : 'json',
									headers : {
										'Accept' : 'application/json',
										'Content-Type' : 'application/json'
									}
								})
								.then(
										function successCallback(response) {
											//$scope.orderDetailList = response.data;
											adminCstl
													.openComponentModal("Category Deleted!"
															+ response.statusText);
										}, function errorCallback(response) {
											console.log(response.statusText);
										});

					}
					adminCstl.openComponentModal = function(msgToDisplay) {
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

app.controller("topPolicyController", function($scope, $http) {

	$scope.topPolicyList = [];
	$http({
		method : 'GET',
		url : 'http://localhost:8080/InsuranceMantra/rest/front/top3policies'

	}).then(function successCallback(response) {
		$scope.topPolicyList = response.data;
	}, function errorCallback(response) {
		console.log(response.statusText);
	});

	$scope.topPolicyBrand;
	$http({
		method : 'GET',
		url : 'http://localhost:8080/InsuranceMantra/rest/seller/topBrand'

	}).then(function successCallback(response) {
		$scope.topPolicyBrand = response.data;
	}, function errorCallback(response) {
		console.log(response.statusText);
	});

});

function _success(response) {
	// _refreshCountryData();
	//_clearFormData()
	console.log("Successful Response" + response);
}

function _error(response) {
	console.log(response.statusText);
}
