
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