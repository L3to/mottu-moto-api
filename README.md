<h1 align="center">Mottu Moto MVC  </h1>
<p align="center">
  <img src="https://img.shields.io/badge/version-0.0.3--SNAPSHOT-blue.svg" />
  <img src="https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen.svg" />
  <img src="https://img.shields.io/badge/Java-21-orange.svg" />
  </a>
</p>

## Autores

👤 **Samuel Yariwake**
- RM5556461


👤 **Luiz Felipe**
- RM5555591


👤 **Francesco Monteiro di Benedetto**
- RM557313
<hr>

O Mottu Moto MVC é uma aplicação web desenvolvida em Java com o framework Spring Boot, projetada para gerenciar informações de motos e pátios com uma interface web completa. O sistema oferece funcionalidades de CRUD para motos e pátios, sistema de autenticação e autorização com Spring Security, interface web responsiva com Thymeleaf, e gerenciamento de usuários com diferentes níveis de permissão. A aplicação utiliza Spring Data JPA para persistência de dados, Flyway para migrations, Spring Validation para validação de entradas e está integrada a um banco de dados Oracle. Este projeto foi desenvolvido como parte de um desafio acadêmico em parceria com a FIAP e a Mottu.
<hr>

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.4**
- **Spring Data JPA**
- **Spring Security**
- **Spring Validation**
- **Spring Web**
- **Thymeleaf** (Template Engine)
- **Flyway** (Database Migration)
- **Oracle Database**
- **Lombok** (Code Generation)
- **HikariCP** (Connection Pooling)
- **Maven**

## Funcionalidades Principais

### 🔐 Sistema de Autenticação e Autorização
- Login e registro de usuários
- Controle de acesso baseado em roles (ADMIN/USER)
- Proteção de endpoints sensíveis
- Gerenciamento de permissões de usuário

### 🏍️ Gerenciamento de Motos
- CRUD completo para motos
- Filtros por modelo e status do sensor
- Validação de placa única
- Controle de localização (andar e vaga)
- Status do sensor (ATIVADO, DESATIVADO, MANUTENCAO, ERRO)

### 🅿️ Gerenciamento de Pátios
- CRUD completo para pátios
- Controle de capacidade máxima
- Associação de motos aos pátios
- Filtros por nome e capacidade

### 🌐 Interface Web
- Interface responsiva com Thymeleaf
- Páginas de listagem com paginação
- Formulários de cadastro e edição
- Confirmação de exclusão via modal

### 📊 Recursos Adicionais
- Migrations automáticas com Flyway
- Validação de dados com Bean Validation
- Tratamento global de exceções
- Cache para otimização de performance

## Configuração do Ambiente

### Pré-requisitos

- **Java 21** instalado
- **Maven** instalado
- Banco de dados Oracle configurado

### Configuração do Banco de Dados

O projeto utiliza variáveis de ambiente para configuração do banco de dados. Configure as seguintes variáveis no arquivo `src/main/resources/env.properties`:

```properties
SPRING_DATASOURCE_URL=jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl
SPRING_DATASOURCE_USERNAME=SEU_USUARIO
SPRING_DATASOURCE_PASSWORD=SUA_SENHA
```

**Nota**: O arquivo `env.properties` está incluído no `.gitignore` para segurança. Use o arquivo `env.properties` como exemplo.

### Executando o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/lemos000/challenge-mottu.git
   cd challenge-mottu
   ```

2. Configure o banco de dados no arquivo `src/main/resources/env.properties`

3. Compile e execute o projeto:
   ```bash
   mvn spring-boot:run
   ```

4. Acesse a aplicação em: [http://localhost:8082](http://localhost:8082)
5. Faça login com as credenciais padrão: `admin@mottu.com` / `admin123`

## Endpoints e Funcionalidades

### 🔐 Autenticação
- **GET** `/login` - Página de login
- **POST** `/login` - Processamento do login
- **GET** `/register` - Página de registro
- **POST** `/register` - Criação de nova conta
- **POST** `/logout` - Logout do sistema

### 🅿️ Pátios (Acesso ADMIN)
- **GET** `/patios` - Lista os pátios com filtros opcionais por nome e capacidade máxima
- **GET** `/patios/{id}` - Detalhes de um pátio específico
- **GET** `/patios/cadastro` - Formulário de cadastro de pátio
- **POST** `/patios/cadastro` - Criação de novo pátio
- **GET** `/patios/{id}/editar` - Formulário de edição de pátio
- **POST** `/patios/{id}/editar` - Atualização de pátio existente
- **GET** `/patios/{id}/deletar` - Exclusão de pátio

### 🏍️ Motos (Acesso ADMIN/USER)
- **GET** `/motos` - Lista as motos com filtros por modelo e status do sensor
- **GET** `/motos/{id}` - Detalhes de uma moto específica
- **GET** `/motos/cadastro` - Formulário de cadastro de moto (ADMIN)
- **POST** `/motos/cadastro` - Criação de nova moto (ADMIN)
- **GET** `/motos/{id}/editar` - Formulário de edição de moto (ADMIN)
- **POST** `/motos/{id}/editar` - Atualização de moto existente (ADMIN)
- **GET** `/motos/{id}/deletar` - Exclusão de moto (ADMIN)

### 👥 Gerenciamento de Usuários (Acesso ADMIN)
- **GET** `/role` - Lista de usuários e gerenciamento de permissões
- **POST** `/role/toggleRole/{id}` - Alteração de permissões de usuário

### 🏠 Interface Principal
- **GET** `/` - Hub principal com navegação para as funcionalidades

## Estrutura do Projeto
![img_1.png](img_1.png)

```
src/
├── main/
│   ├── java/br/com/fiap/mottu/challenge/demo/
│   │   ├── config/           # Configurações de segurança
│   │   ├── controllers/      # Controladores web/MVC
│   │   ├── domain/
│   │   │   ├── dto/         # Data Transfer Objects
│   │   │   └── model/       # Entidades JPA
│   │   ├── exceptions/      # Tratamento de exceções
│   │   ├── repository/      # Repositórios JPA
│   │   ├── services/        # Lógica de negócios
│   │   ├── utils/           # Enums e utilitários
│   │   └── DemoApplication.java
│   └── resources/
│       ├── db/migrations/   # Scripts Flyway
│       ├── static/          # CSS, JS, imagens
│       ├── templates/       # Templates Thymeleaf
│       ├── application.properties
│       └── env.properties
└── test/                    # Testes unitários
```

### Principais Componentes

#### Domain Models
- `User` - Entidade de usuário com roles
- `Moto` - Entidade de motocicleta
- `Patio` - Entidade de pátio
- `Localizacao` - Classe embarcada para localização

#### Controllers
- `LoginController` - Autenticação
- `RegisterController` - Registro de usuários
- `MotoController` - Gerenciamento de motos
- `PatioController` - Gerenciamento de pátios
- `RoleController` - Gerenciamento de permissões

#### Services
- `UserService` - Lógica de usuários e autenticação
- `MotoService` - Lógica de negócio para motos
- `PatioService` - Lógica de negócio para pátios

### Exemplo de Objeto para **Pátio**

#### **POST /patios/cadastro** (via formulário web)
**Campos do formulário:**
```
Nome: "Pátio Central"
Capacidade Máxima: 100
Área Total: 500.0
Observações: "Pátio principal para motos."
```

#### **Estrutura do objeto no banco:**
```json
{
  "id": 1,
  "nome": "Pátio Central",
  "capacidadeMaxima": 100,
  "areaTotal": 500.0,
  "observacoes": "Pátio principal para motos."
}
```

---

### Exemplo de Objeto para **Moto**

#### **POST /motos/cadastro** (via formulário web)
**Campos do formulário:**
```
Modelo: "Honda CG 160"
Placa: "ABC1234"
Chassi: "9C2KC0810R1234567"
Status do Sensor: "ATIVADO"
Andar da Localização: "Térreo"
Vaga da Localização: "A1"
```

#### **Estrutura do objeto no banco:**
```json
{
  "id": 1,
  "modelo": "Honda CG 160",
  "placa": "ABC1234",
  "chassi": "9C2KC0810R1234567",
  "ativa": true,
  "statusSensor": "ATIVADO",
  "localizacaoAtual": {
    "andar": "Térreo",
    "vaga": "A1"
  },
  "patio": {
    "id": 1,
    "nome": "Pátio Central"
  }
}
```

---

## Roles e Permissões

### 👤 USER
- Visualizar motos
- Acessar detalhes das motos
- Visualizar hub principal

### 👑 ADMIN
- Todas as permissões de USER
- Gerenciar pátios (CRUD completo)
- Gerenciar motos (CRUD completo)
- Gerenciar usuários e permissões
- Acesso completo ao sistema

### 🔐 Usuário Padrão
- Email: `admin@mottu.com`
- Senha: `admin123`
- Role: ADMIN

## Database Migrations

O projeto utiliza Flyway para gerenciamento de migrations:

- `V1.0.1__Criando_tabela_user.sql` - Criação da tabela de usuários
- `V1.0.2__Criando_tabelas_motos_patios.sql` - Criação das tabelas principais
- `V1.0.3__Add_sample_data_tb_moto_patio.sql` - Dados de exemplo
- `V1.0.4__Inserindo_admin_inicial.sql` - Usuário administrador padrão

## Características Técnicas

### Performance
- Connection pooling com HikariCP
- Cache habilitado para consultas frequentes
- Paginação em todas as listagens

### Segurança
- Senhas criptografadas com BCrypt
- Proteção CSRF habilitada
- Controle de acesso baseado em roles
- Validação de dados de entrada

### Interface
- Design responsivo com CSS customizado
- Modais de confirmação para ações destrutivas
- Feedback visual para ações do usuário
- Navegação intuitiva entre funcionalidades




## Acadêmico

Este é um projeto acadêmico feito em colaboração com a FIAP e Mottu, como parte do curso de Análise e Desenvolvimento de Sistemas.

## Status Sensor

O sistema suporta os seguintes status de sensor para motos:
- `ATIVADO` - Sensor funcionando normalmente
- `DESATIVADO` - Sensor desligado
- `MANUTENCAO` - Sensor em manutenção
- `ERRO` - Sensor com erro/problema

## Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## Licença

Este projeto é parte de um desafio acadêmico da FIAP em parceria com a Mottu. 
