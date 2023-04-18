Projeto Integrador
Título: Sistema de Cadastro Odontológico - NewDente

Descrição:
O Sistema de Cadastro Odontológico é uma aplicação web desenvolvida em Spring MVC,
que permite o gerenciamento de cadastros de objetos relacionados a uma clínica odontológica.
O sistema oferece funcionalidades Login e  CRUD (Create, Read, Update, Delete) para o cadastro de pacientes,dentistas, consultas e usuarios.

Principais Funcionalidades:
Cadastro, edição e exclusão de pacientes, com informações como nome, sobrenome, etc.
Agendamento, consulta e cancelamento de consultas odontológicas, com detalhes como data, dentista responsável, etc.
Autenticação de usuários com diferentes níveis de acesso (por exemplo, usuários com função de "admin" têm acesso a todas as funcionalidades, enquanto usuários com função de "usuario" têm acesso apenas a algumas funcionalidades específicas).
Proteção de dados sensíveis dos pacientes, com uso de técnicas de segurança, como criptografia de senhas e sessões de usuário.

Tecnologias Utilizadas:
    
    JavaJDK (20)
    Spring Boot: framework de desenvolvimento de aplicações Java que simplifica o processo de desenvolvimento e oferece recursos integrados.
    Spring Data JPA: módulo do Spring para integração de aplicativos Java com bancos de dados relacionais usando o JPA.
    Spring Validation: módulo do Spring para validação de dados em aplicativos Java.
    Spring Web: módulo do Spring para desenvolvimento de aplicativos web em Java.
    Spring DevTools: módulo do Spring com recursos para o desenvolvimento em ambiente de desenvolvimento.
    H2 Database: banco de dados em memória para desenvolvimento e teste de aplicações.
    Lombok: biblioteca para redução de código boilerplate em aplicações Java.
    Spring Security: módulo do Spring para segurança e autenticação de aplicativos web.
    Auth0 Java JWT: biblioteca para manipulação de JSON Web Tokens (JWT) em Java.
    SpringDoc: biblioteca para geração de documentação de API baseada em OpenAPI para aplicativos Spring MVC.

Instalação

Siga os passos abaixo para instalar e configurar o projeto em um ambiente local:

    Faça o clone do repositório para o seu ambiente local:


git clone https://github.com/Henrique-Schneider/newdente

    Navegue para o diretório do projeto:



cd nome-do-projeto

    Abra o projeto em sua IDE Java compatível, como o Eclipse, IntelliJ, NetBeans, etc.

    Compile e execute o projeto usando o Maven ou o Gradle, dependendo da sua configuração:

Maven:

mvn clean install
mvn spring-boot:run

Gradle:

gradlew clean build
gradlew bootRun

    O projeto estará disponível em http://localhost:8080 no seu navegador.

Licença

Este projeto está licenciado sob a Licença MIT,
o que significa que é gratuito para uso, modificação e distribuição,
de acordo com os termos da licença. No entanto,
observe que as licenças de software podem variar e
é importante verificar a licença específica do projeto antes de usá-lo em um
ambiente de produção ou comercial.

Propósito do Projeto

Este projeto foi desenvolvido como parte do curso de back-end-I da Digital House Brasil.
Ele foi criado com o propósito específico de aprendizado e cumprimento dos requisitos do curso.
Por favor, note que este projeto não é destinado a uso em produção ou em um ambiente comercial 
sem uma devida revisão e ajuste às necessidades e requisitos específicos do ambiente de produção.

Contato

Você pode entrar em contato comigo por meio das seguintes formas:

    LinkedIn: linkedin.com/in/henrique-bitencourt-ferreira-domene-schneider-b86691231
    E-mail: henriqueschneider1@gmail.com
    GitHub: https://github.com/Henrique-Schneider

Fique à vontade para entrar em contato comigo 
se tiver alguma dúvida, sugestão ou feedback sobre o projeto!

Agradecimentos

Gostaria de agradecer ao Professor Adriano, responsável pela disciplina de Back-end I no curso da Digital House Brasil,
por compartilhar seu conhecimento e experiência durante o aprendizado deste projeto.

Também gostaria de agradecer ao assistente Gabriel pela sua ajuda e suporte durante o desenvolvimento deste projeto.

Obrigado a ambos pelo apoio e orientação ao longo do curso!