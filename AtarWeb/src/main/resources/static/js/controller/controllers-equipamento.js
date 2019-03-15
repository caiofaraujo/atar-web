

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
                $scope.errorMessage = 'Preencha os campos corretamente';
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
                $scope.errorMessage = 'Preencha os campos corretamente';
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

        $scope.deleteEquipamento = function (index) {

            $scope.aux = $scope.equipamentos.shift(index);
            EquipamentoCRUDService.deleteEquipamento($scope.aux)
            $scope.getAllEquipamentos();
        }

        $scope.getAllEquipamentos();

    }]);

