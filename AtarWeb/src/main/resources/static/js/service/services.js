
app.service('ClienteCRUDService', ['$http', function ($http) {

    this.addCliente = function addUser(cliente) {   
        return $http({
            method: 'POST',
            url: '/atar/cadastrar-cliente',
            data: {
            	nome : cliente.nome,
            	endereco : cliente.endereco,
            	telefone : cliente.telefone
            }
        });
    }

    this.getAllClientes = function getAllClientes() {
        return $http({
            method: 'GET',
            url: '/api/student'
        });
    } 

    this.deleteCliente = function deleteCliente(student) {
        $http({
            method: 'POST',
            url: 'api/student/delete',
            data: {
                id: student.id
            }
        });
    }


}]);