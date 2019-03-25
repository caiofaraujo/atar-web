
app.service('ServicoCRUDService', ['$http', function ($http) {
	
    this.addServico = function addServ(servico) {   
        return $http({
            method: 'POST',
            url: '/atar/cadastrar-servico',
            data: {
            	descricao : servico.descricao,
            	tipo : servico.tipo,
            	observacao : servico.observacao,
            	dtInicioServico : servico.dtInicioServico,
            	dtFinalServico : servico.dtFinalServico, 
            	cliente : servico.cliente,
            	equipamento : servico.equipamento
            }
        });
    }
    
    this.updateServico = function addServ(servico) {   
        return $http({
            method: 'POST',
            url: '/atar/cadastrar-servico/alterar',
            data: {
            	descricao : servico.descricao,
            	tipo : servico.tipo,
            	observacao : servico.observacao,
            	dtInicioServico : servico.dtInicioServico,
            	dtFinalServico : servico.dtFinalServico,
            	cliente : servico.cliente,
            	equipamento : servico.equipamento,
            	id : servico.id
            }
        });
    }

    this.getAllServicos = function getAllServicos() {
        return $http({
            method: 'GET',
            url: '/atar/cadastrar-servico/listar'
        });
    } 


    this.deleteServico = function deleteServico(servico) {
    	  return $http({
              method: 'POST',
              url: '/atar/cadastrar-servico/excluir',
              data: {
              	id : servico.id
              }
          });
    }


}]);