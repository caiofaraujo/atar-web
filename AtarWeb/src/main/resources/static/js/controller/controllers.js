

app.controller('ClienteCRUDCtrl', ['$scope', '$http', 'ClienteCRUDService',
    function ($scope, $http, ClienteCRUDService) {
        $scope.isExibirSucesso = false;
        $scope.isExibirErro = false;
        $scope.addCliente = function () {
        	$scope.isExibirErro = false;
        	$scope.isExibirSucesso = false;
            $scope.message = '';
            
            if ($scope.cliente != null && $scope.cliente.nome) {

                ClienteCRUDService.addCliente($scope.cliente)
                    .then(function success(response) {
                        $scope.message = ' Cliente adicionado!';
                        $scope.isExibirSucesso = true;
                        $scope.errorMessage = '';

                        $scope.cliente.nome = '';
                        $scope.cliente.endereco = '';
                        $scope.cliente.nascimento = '';
                        $scope.cliente.id = '';
                        // $scope.getAllClientes();

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

        $scope.getAllClientes = function () {

            ClienteCRUDService.getAllClientes().then(function success(response) {
                $scope.clientes = response.data.data;
            }, function error(response) {
                $scope.errorMessage = 'Falha na consulta!';
                $scope.message = '';
            });
        }

        $scope.deleteCliente = function (index) {

            $scope.aux = $scope.clientes.shift(index);
            ClienteCRUDService.deleteCliente($scope.aux)
            $scope.getAllClientes();
        }

        $scope.updateCliente = function (cliente) {

            $scope.cliente = cliente;
            $scope.cliente.nascimento = new Date(cliente.nascimento);
            console.log
        }

        // $scope.getAllClientes();

    }]);

