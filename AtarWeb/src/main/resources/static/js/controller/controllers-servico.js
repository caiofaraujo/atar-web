

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
        	$scope.servico.dtFinalRet = servico.dtFinalRet != null ? new Date(servico.dtFinalRet) : null;
        	
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
                    		if (response.data.errors[0] != null) {
                    			$scope.errorMessage = response.data.errors[0];
                    		}
                            
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
//                $scope.servicos.forEach(a =>         
//             );
                angular.forEach( $scope.servicos, function(a){
                    if(a.tipo === "MANUTENÇÃO PREVENTIVA"){
                    	a.dtRestante = Math.floor((Math.abs(new Date(a.dtInicioServico) - new Date(a.dtFinalRet)) ) / 86400000)
                    }else{ 
                    	a.dtRestante = Math.floor((Math.abs(new Date(a.dtInicioServico) - new Date(a.dtFinalServico)) ) / 86400000)
                    }
                	});
                
            }, function error(response) {
                $scope.errorMessage = 'Falha na consulta!';
                $scope.message = '';
            });
        	
        	
        	
        }
        
        $scope.deletar = function(servico) {
        	$scope.servico = servico;
		}
        
        $scope.cancelar = function() {
        	$scope.getAllServicos();
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
         		
                $scope.equipamentos = response.data.data;
            }, function error(response) {
             
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
        
        $scope.sortField = undefined;
        $scope.reverse = false;

  	 $scope.isSortUp = function(fieldName){
  	 	return $scope.sortField === fieldName && !$scope.reverse;
  	 };
  	 $scope.isSortDown = function(fieldName){
  	 	return $scope.sortField === fieldName && $scope.reverse;
  	 };
  	 //order data
  	 $scope.sort = function(fieldName){
  	 	if ($scope.sortField === fieldName) {
  	 		$scope.reverse = !$scope.reverse;
  	 	}else{
  	 		$scope.sortField = fieldName;
  	 		$scope.reverse = false;

  	 	};
  	 }

    }]);

