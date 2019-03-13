

app.controller('ServicoCRUDCtrl', ['$scope', '$http', 'ServicoCRUDService','ClienteCRUDService',
    function ($scope, $http, ServicoCRUDService,ClienteCRUDService) {
        $scope.isExibirSucesso = false;
        $scope.isExibirErro = false;
        $scope.lstClientes = [];
        

        $scope.addServico = function () {
        	$scope.isExibirErro = false;
        	$scope.isExibirSucesso = false;
            $scope.message = '';
            
            if ($scope.servico != null) {

            	ServicoCRUDService.addServico($scope.servico)
                    .then(function success(response) {
                        $scope.message = ' Serviço adicionado!';
                        $scope.isExibirSucesso = true;
                        $scope.errorMessage = '';

                        $scope.servico.descricao = '';
                        $scope.servico.tipo = '';
                        $scope.servico.observacao = '';
                        $scope.servico.dtInicioServico = '';
                        $scope.servico.dtFinalServico = '';
                        $scope.servico.id = '';
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

        $scope.getAllServicos = function () {

        	ServicoCRUDService.getAllServicos().then(function success(response) {
                $scope.servicos = response.data.data;
            }, function error(response) {
                $scope.errorMessage = 'Falha na consulta!';
                $scope.message = '';
            });
        }

        $scope.deleteServico = function (index) {

            $scope.aux = $scope.servicos.shift(index);
            ServicoCRUDService.deleteServico($scope.aux)
            $scope.getAllServicos();
        }

        $scope.updateServico = function (Servico) {

            $scope.servico = servico;
            //$scope.equipamento.marca = new Date(equipamento.marca);
            //console.log
        }

        $scope.getClientes = function(){            
            ClienteCRUDService.getAllClientes().then(function success(res){
                console.log(res);  
                $scope.lstClientes = res.data.data;     
                        
            }, function error(res){
                console.log(res);
                
            });
        }

        $scope.getClientes();
        $scope.getAllServicos();

        // $scope.getAllEquipamentos();

    }]);

