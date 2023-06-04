# Estudo-arquitetura-microservices
Estudo e implementação de segurança em uma arquitetura de microservices

- Discovery - Microserviço para fazer descoberta dos micros services clientes que se registram de forma dinamica assim que o serviço fica UP; Foi utilizado o Spring Clould Eureka Netflix

- Gateway - Microserviço que realiza o roteamento dinamico do clientes que estão registrados no discovery, e tambem serve de porta de entrada principal para acesso os serviços interno; Por ser a porta principal de entrada tambem realizar o repasse de token de autorização para os clientes. Foi utilizado o Spring clould gateway.

- Autorização - Microserviço responsavel por realizar a autenticação e geração de token dos clientes que são acessados pelo gateway; Foi utilizado o Authotization Server;

- Resource server 1 e 2 - Representa todos os microserviços servidores de recursos protegidos.



