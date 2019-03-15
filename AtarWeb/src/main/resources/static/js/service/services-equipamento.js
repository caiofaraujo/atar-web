
app.service('EquipamentoCRUDService', ['$http', function ($http) {

    this.addEquipamento = function addEquip(equipamento) {   
        return $http({
            method: 'POST',
            url: '/atar/cadastrar-equipamento',
            data: {
            	marca : equipamento.marca,
            	modelo : equipamento.modelo            	
            }
        });
    }

    this.updateEquipamento = function addEquip(equipamento) {   
        return $http({
            method: 'POST',
            url: '/atar/cadastrar-equipamento/alterar',
            data: {
            	marca : equipamento.marca,
            	modelo : equipamento.modelo,
            	id : equipamento.id
            }
        });
    }
    
    this.getAllEquipamentos = function getAllEquipamentos() {
        return $http({
            method: 'GET',
            url: '/atar/cadastrar-equipamento/listar-equipamento'
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