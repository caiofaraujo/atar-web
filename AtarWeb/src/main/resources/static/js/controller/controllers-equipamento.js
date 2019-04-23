

app.controller('EquipamentoCRUDCtrl', ['$scope', '$http', 'EquipamentoCRUDService',
    function ($scope, $http, EquipamentoCRUDService) {
        $scope.isExibirSucesso = false;
        $scope.isExibirErro = false;
        
        $scope.editar = function(equipamento){
        	$scope.equipamento = {};
        	$scope.equipamento = equipamento;
        }
        
        
        $scope.addEquipamento = function () {
        	$scope.isExibirErro = false;
        	$scope.isExibirSucesso = false;
            $scope.message = '';
            
            if ($scope.equipamento != null) {

            	EquipamentoCRUDService.addEquipamento($scope.equipamento)
                    .then(function success(response) {
                        $scope.message = ' Equipamento adicionado!';
                        $scope.isExibirSucesso = true;
                        $scope.errorMessage = '';

                        $scope.equipamento.marca = '';
                        $scope.equipamento.modelo = '';
                        $scope.equipamento.id = '';
                        // $scope.getAllEquipamentos();

                    },
                        function error(response) {
                            $scope.errorMessage = response.data.errors[0];
                            $scope.isExibirErro = true;
                            $scope.message = '';
                        });
            }
            else {
                $scope.errorMessage = ' Preencha os campos corretamente';
                $scope.isExibirErro = true;
                $scope.message = '';
            }
        }
        
        $scope.updateEquipamento = function () {
        	$scope.isExibirErro = false;
        	$scope.isExibirSucesso = false;
            $scope.message = '';
            
            if ($scope.equipamento != null) {

            	EquipamentoCRUDService.updateEquipamento($scope.equipamento)
                    .then(function success(response) {
                        $scope.message = ' Equipamento alterado!';
                        $scope.isExibirSucesso = true;
                        $scope.errorMessage = '';

                        $scope.equipamento.marca = '';
                        $scope.equipamento.modelo = '';
                        $scope.equipamento.id = '';
                        $scope.getAllEquipamentos();

                    },
                        function error(response) {
                            $scope.errorMessage = response.data.errors[0];
                            $scope.isExibirErro = true;
                            $scope.message = '';
                        });
            }
            else {
                $scope.errorMessage = ' Preencha os campos corretamente';
                $scope.isExibirErro = true;
                $scope.message = '';
            }
        }

        $scope.getAllEquipamentos = function () {

        	EquipamentoCRUDService.getAllEquipamentos().then(function success(response) {
                $scope.equipamentos = response.data.data;
            }, function error(response) {
                $scope.errorMessage = 'Falha na consulta!';
                $scope.message = '';
            });
        }
        
        $scope.deletar = function(equipamento) {
        	$scope.equipamento = equipamento;
		}
        
        $scope.cancelar = function() {
        	$scope.equipamento = {};
		}
        
        $scope.deleteEquipamento = function (equipamento) {            
        	EquipamentoCRUDService.deleteEquipamento(equipamento).then(function success(res){
	        	 $scope.errorMessage = '';
	             $scope.message = ' Equipamento excluído!';
	             $scope.isExibirSucesso = true;
	             $scope.isExibirErro = false;
	             $scope.getAllEquipamentos();
	        	}, function error(res){
	        		 $scope.errorMessage = res.data.errors[0];
	                 $scope.message = '';
	                 $scope.isExibirErro = true;
	                 $scope.getAllClientes();
	        	});
        }

        
        $scope.getAllEquipamentos();

    }]);

