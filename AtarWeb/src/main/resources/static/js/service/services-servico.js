
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
            	dtFinalServico : servico.dtFinalServico
            }
        });
    }

    this.getAllEquipamentos = function getAllEquipamentos() {
        return $http({
            method: 'GET',
            url: '/api/student'
        });
    } 

    this.deleteServico = function deleteServico(servico) {
        $http({
            method: 'POST',
            url: 'api/student/delete',
            data: {
                id: student.id
            }
        });
    }


}]);