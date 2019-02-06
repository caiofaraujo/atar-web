
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

    this.getAllServicos = function getAllServicos() {
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