

app.controller('ServicoCRUDCtrl', ['$scope', '$http', 'ServicoCRUDService','ClienteCRUDService','EquipamentoCRUDService',
    function ($scope, $http, ServicoCRUDService,ClienteCRUDService,EquipamentoCRUDService) {
        $scope.isExibirSucesso = false;
        $scope.isExibirErro = false;
        $scope.lstClientes = [];
        $scope.equipamentos = [];
        
        $scope.editar = function(servico){
        	$scope.servico = {};
        	$scope.servico = servico;
        	$scope.servico.dtFinalServico = servico.dtFinalServico != null ? new Date(servico.dtFinalServico) : null;
        	$scope.servico.dtInicioServico = servico.dtInicioServico != null ? new Date(servico.dtInicioServico) : null;
        	
        }
        

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
                        $scope.servico.cliente = '';
                        $scope.servico.equipamento = '';
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
        
        $scope.updateServico = function () {
        	$scope.isExibirErro = false;
        	$scope.isExibirSucesso = false;
            $scope.message = '';
            
            if ($scope.servico != null) {
            	ServicoCRUDService.updateServico($scope.servico)
                    .then(function success(response) {
                        $scope.message = ' Serviço alterado!';
                        $scope.isExibirSucesso = true;
                        $scope.errorMessage = '';
                        $scope.servico.descricao = '';
                        $scope.servico.tipo = '';
                        $scope.servico.observacao = '';
                        $scope.servico.dtInicioServico = '';
                        $scope.servico.dtFinalServico = '';
                        $scope.servico.id = '';
                        $scope.servico.cliente = '';
                        $scope.servico.equipamento = '';
                        $scope.getAllServicos();
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

        $scope.getAllServicos = function () {
        	ServicoCRUDService.getAllServicos().then(function success(response) {
                $scope.servicos = response.data.data;
            }, function error(response) {
                $scope.errorMessage = 'Falha na consulta!';
                $scope.message = '';
            });
        }
        
        $scope.deletar = function(servico) {
        	$scope.servico = servico;
		}
        
        $scope.cancelar = function() {
        	$scope.servico = {};
		}
        
        $scope.deleteServico = function (servico) {            
            ServicoCRUDService.deleteServico(servico).then(function success(res){
            	 $scope.errorMessage = '';
                 $scope.message = ' Serviço excluído!';
                 $scope.isExibirSucesso = true;
                 $scope.isExibirErro = false;
                 $scope.getAllServicos();
            	}, function error(res){
            		 $scope.errorMessage = 'Falha na consulta!';
                     $scope.message = '';
                     $scope.isExibirErro = true;
            	});
            }
        
        
        $scope.getAllEquipamentos = function(){
         	EquipamentoCRUDService.getAllEquipamentos().then(function success(response) {
         		debugger;
                $scope.equipamentos = response.data.data;
            }, function error(response) {
            	debugger;
                $scope.errorMessage = 'Falha na consulta!';
                $scope.message = '';
            });	
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

        $scope.getAllEquipamentos();

    }]);

