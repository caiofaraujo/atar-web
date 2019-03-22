
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
    
    this.deleteEquipamento = function deleteEquipamento(equipamento) {
  	  return $http({
            method: 'POST',
            url: '/atar/cadastrar-equipamento/excluir',
            data: {
            	id : equipamento.id
            }
        });
    }



}]);