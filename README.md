## ENDPOINTS DA APLICAÇÃO

-**GET/api/usuarios**: retorna a lista de usuários cadastrados;

-**POST/api/usuarios**: Cadastra um novo usuário;

-**PUT/api/usuarios/{id}**: Atualiza as informações de um usuário existente;

-**DELETE/api/usuarios/{id}**-: Remove um usuário pelo ID;

-**OBS**: o dto deve ser preenchido com os seguintes dados: {"dataHoraEnvio", "nomeDestinatario",
"emailDestinatario", "telefoneDestinatario", "mensagem", "modoDeEnvio", "statusEnvio"}