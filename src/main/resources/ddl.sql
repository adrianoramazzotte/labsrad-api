
    create table atendimento (
       id integer not null auto_increment,
        datalancamento date,
        desconto decimal(19,2),
        formapagamento varchar(255),
        id_ficha integer,
        status bit,
        valortotal decimal(19,2),
        paciente_id integer,
        tenant_id integer,
        usuario_id integer,
        primary key (id)
    ) engine=InnoDB

    create table atendimento_itens (
       codigo integer not null,
        acesso integer,
        data_atendimento date,
        desconto decimal(19,2),
        preco decimal(19,2),
        status bit,
        total decimal(19,2),
        atendimento_id integer not null,
        convenio_id integer not null,
        exame_id integer not null,
        tenant_id integer,
        primary key (atendimento_id, codigo, convenio_id, exame_id)
    ) engine=InnoDB

    create table classepermissao (
       id integer not null auto_increment,
        nome varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table cliente (
       id integer not null auto_increment,
        nome varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table convenio (
       id integer not null auto_increment,
        descricao varchar(255),
        status bit,
        tenant_id integer,
        primary key (id)
    ) engine=InnoDB

    create table empresa (
       id integer not null auto_increment,
        bairro varchar(255),
        cep varchar(255),
        cidade varchar(255),
        complemento varchar(255),
        cpfoucnpj varchar(255),
        editar bit,
        email varchar(255),
        logradouro varchar(255),
        naturezapessoa varchar(255),
        nomecontato varchar(255),
        numero varchar(255),
        razaosocial varchar(255),
        status bit,
        telefone varchar(255),
        tenant_id integer,
        uf varchar(255),
        valor decimal(19,2),
        whats varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table exame (
       id integer not null auto_increment,
        descricao varchar(255),
        status bit,
        tenant_id integer,
        primary key (id)
    ) engine=InnoDB

    create table exame_convenio (
       preco decimal(19,2),
        status bit,
        convenio_id integer not null,
        exame_id integer not null,
        tenant_id integer,
        primary key (convenio_id, exame_id)
    ) engine=InnoDB

    create table item (
       quantidade integer,
        status bit,
        pedido_id integer not null,
        produto_id integer not null,
        primary key (pedido_id, produto_id)
    ) engine=InnoDB

    create table log_sistema (
       id integer not null auto_increment,
        comando varchar(255),
        datagravacao datetime,
        email_usuario varchar(255),
        status bit,
        atendimento_id integer not null,
        convenio_id integer not null,
        empresa_id integer not null,
        exame_id integer not null,
        paciente_id integer not null,
        patrimonio_id integer not null,
        permissao_id integer not null,
        tenant_id integer,
        usuario_id integer not null,
        primary key (id)
    ) engine=InnoDB

    create table paciente (
       id integer not null auto_increment,
        cpf varchar(255),
        datanasc date,
        nome varchar(255),
        sexo varchar(255),
        status bit,
        telefone varchar(255),
        tenant_id integer,
        primary key (id)
    ) engine=InnoDB

    create table patrimonio (
       id integer not null auto_increment,
        dataaquisicao datetime(6),
        descricao varchar(255),
        status bit,
        valor decimal(19,2),
        empresa_id integer,
        tenant_id integer,
        primary key (id)
    ) engine=InnoDB

    create table pedido (
       id integer not null auto_increment,
        cliente_id integer,
        primary key (id)
    ) engine=InnoDB

    create table permissao (
       id integer not null auto_increment,
        descricao varchar(255),
        classepermissao_id integer,
        primary key (id)
    ) engine=InnoDB

    create table produto (
       id integer not null auto_increment,
        descricao varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table tenant (
       id integer not null auto_increment,
        descricao varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table usuario (
       id integer not null auto_increment,
        email varchar(255),
        gtenantativo integer,
        login varchar(255),
        nome varchar(255),
        senha varchar(255),
        status bit,
        tenantativo integer,
        tenant_id integer,
        primary key (id)
    ) engine=InnoDB

    create table usuario_permissao (
       id_usuario integer not null,
        id_permissao integer not null
    ) engine=InnoDB

    create table usuario_empresa (
       empresapadrao bit,
        tenant_id integer,
        id_empresa integer not null,
        id_usuario integer not null,
        primary key (id_empresa, id_usuario)
    ) engine=InnoDB

    alter table empresa 
       add constraint UK_ow6ddx531a4abnwv6eah7h3k7 unique (cpfoucnpj)

    alter table atendimento 
       add constraint FK5otidx5k8xbj2jv2lp1ie76n6 
       foreign key (paciente_id) 
       references paciente (id)

    alter table atendimento 
       add constraint FKc0seka8tqrr44heqke6keeu6q 
       foreign key (tenant_id) 
       references tenant (id)

    alter table atendimento 
       add constraint FK27ltr33c0osxb67vm5lfb9iyn 
       foreign key (usuario_id) 
       references usuario (id)

    alter table atendimento_itens 
       add constraint FKbsoe795mbrhuh0dksoyuic8cp 
       foreign key (atendimento_id) 
       references atendimento (id)

    alter table atendimento_itens 
       add constraint FK2lha3fo11ygx12jxqax4lx4o9 
       foreign key (convenio_id) 
       references convenio (id)

    alter table atendimento_itens 
       add constraint FKqjd0ukyb8vw68ckn8lmpw0gxt 
       foreign key (exame_id) 
       references exame (id)

    alter table atendimento_itens 
       add constraint FKsyocduwl78ib9ielgc3p6cj0s 
       foreign key (tenant_id) 
       references tenant (id)

    alter table convenio 
       add constraint FKnqsri64tq01dwo7m038m5foew 
       foreign key (tenant_id) 
       references tenant (id)

    alter table exame 
       add constraint FKsnydpq45iblqhqp0ythw2sx6u 
       foreign key (tenant_id) 
       references tenant (id)

    alter table exame_convenio 
       add constraint FK6nwjkv4h9hrvb4tns91ti5aok 
       foreign key (convenio_id) 
       references convenio (id)

    alter table exame_convenio 
       add constraint FKokpsax6nbxbigkfqowjvjlkgh 
       foreign key (exame_id) 
       references exame (id)

    alter table exame_convenio 
       add constraint FKsklt3rqvf3l6d4knsvtcp1cfr 
       foreign key (tenant_id) 
       references tenant (id)

    alter table item 
       add constraint FK5v3x8uwb51v7l9mk41dy2jtku 
       foreign key (pedido_id) 
       references pedido (id)

    alter table item 
       add constraint FKoya2x5ip1q2t3s0936vgjiyx9 
       foreign key (produto_id) 
       references produto (id)

    alter table log_sistema 
       add constraint FKay7in37vxp5j1kuujmw1pn0hr 
       foreign key (atendimento_id) 
       references atendimento (id)

    alter table log_sistema 
       add constraint FKbocxu4cmmq90g78prbu8yp827 
       foreign key (convenio_id) 
       references convenio (id)

    alter table log_sistema 
       add constraint FKdtxgdxwvuxbb45995iirr9ase 
       foreign key (empresa_id) 
       references empresa (id)

    alter table log_sistema 
       add constraint FK5htiblkmlr85m59h9m88gvxrv 
       foreign key (exame_id) 
       references exame (id)

    alter table log_sistema 
       add constraint FKdq7fxmwvaej6beyirw56ixsps 
       foreign key (paciente_id) 
       references paciente (id)

    alter table log_sistema 
       add constraint FK42nl98mgjy9f3if8s5gi21987 
       foreign key (patrimonio_id) 
       references patrimonio (id)

    alter table log_sistema 
       add constraint FKlu2agpnij2euws8475xbxj5pf 
       foreign key (permissao_id) 
       references permissao (id)

    alter table log_sistema 
       add constraint FK70ecb2jvl5xk791a9ybtw1dsv 
       foreign key (tenant_id) 
       references tenant (id)

    alter table log_sistema 
       add constraint FKsltyfd8mjygt5amoo95i5p9iw 
       foreign key (usuario_id) 
       references usuario (id)

    alter table paciente 
       add constraint FKtby7g5oahoxfp3haifpf2e3y5 
       foreign key (tenant_id) 
       references tenant (id)

    alter table patrimonio 
       add constraint FKrda6lgn70gf7kh3r08g221plw 
       foreign key (empresa_id) 
       references empresa (id)

    alter table patrimonio 
       add constraint FKddwe32t0wd6ipfw0xyci94yxo 
       foreign key (tenant_id) 
       references tenant (id)

    alter table pedido 
       add constraint FK30s8j2ktpay6of18lbyqn3632 
       foreign key (cliente_id) 
       references cliente (id)

    alter table permissao 
       add constraint FKdiyasfskbnh46lmx2ywloji0d 
       foreign key (classepermissao_id) 
       references classepermissao (id)

    alter table usuario 
       add constraint FKa10giac3ef9545ra7eyhmn4q1 
       foreign key (tenant_id) 
       references tenant (id)

    alter table usuario_permissao 
       add constraint FKjvcxjnrmdhdv6eti5d7svm5xw 
       foreign key (id_permissao) 
       references permissao (id)

    alter table usuario_permissao 
       add constraint FKbo8hww1whbpxq8ancjokhnfds 
       foreign key (id_usuario) 
       references usuario (id)

    alter table usuario_empresa 
       add constraint FKkicrvlg5cwo9w2xi83xrq4ver 
       foreign key (id_empresa) 
       references empresa (id)

    alter table usuario_empresa 
       add constraint FK62hh8xjf2yjsncl8ti6vxjm7h 
       foreign key (id_usuario) 
       references usuario (id)

    create table atendimento (
       id integer not null auto_increment,
        datalancamento date,
        desconto decimal(19,2),
        formapagamento varchar(255),
        id_ficha integer,
        status bit,
        valortotal decimal(19,2),
        paciente_id integer,
        tenant_id integer,
        usuario_id integer,
        primary key (id)
    ) engine=InnoDB

    create table atendimento_itens (
       codigo integer not null,
        acesso integer,
        data_atendimento date,
        desconto decimal(19,2),
        preco decimal(19,2),
        status bit,
        total decimal(19,2),
        atendimento_id integer not null,
        convenio_id integer not null,
        exame_id integer not null,
        tenant_id integer,
        primary key (atendimento_id, codigo, convenio_id, exame_id)
    ) engine=InnoDB

    create table classepermissao (
       id integer not null auto_increment,
        nome varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table cliente (
       id integer not null auto_increment,
        nome varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table convenio (
       id integer not null auto_increment,
        descricao varchar(255),
        status bit,
        tenant_id integer,
        primary key (id)
    ) engine=InnoDB

    create table empresa (
       id integer not null auto_increment,
        bairro varchar(255),
        cep varchar(255),
        cidade varchar(255),
        complemento varchar(255),
        cpfoucnpj varchar(255),
        editar bit,
        email varchar(255),
        logradouro varchar(255),
        naturezapessoa varchar(255),
        nomecontato varchar(255),
        numero varchar(255),
        razaosocial varchar(255),
        status bit,
        telefone varchar(255),
        tenant_id integer,
        uf varchar(255),
        valor decimal(19,2),
        whats varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table exame (
       id integer not null auto_increment,
        descricao varchar(255),
        status bit,
        tenant_id integer,
        primary key (id)
    ) engine=InnoDB

    create table exame_convenio (
       preco decimal(19,2),
        status bit,
        convenio_id integer not null,
        exame_id integer not null,
        tenant_id integer,
        primary key (convenio_id, exame_id)
    ) engine=InnoDB

    create table item (
       quantidade integer,
        status bit,
        pedido_id integer not null,
        produto_id integer not null,
        primary key (pedido_id, produto_id)
    ) engine=InnoDB

    create table log_sistema (
       id integer not null auto_increment,
        comando varchar(255),
        datagravacao datetime,
        email_usuario varchar(255),
        status bit,
        atendimento_id integer not null,
        convenio_id integer not null,
        empresa_id integer not null,
        exame_id integer not null,
        paciente_id integer not null,
        patrimonio_id integer not null,
        permissao_id integer not null,
        tenant_id integer,
        usuario_id integer not null,
        primary key (id)
    ) engine=InnoDB

    create table paciente (
       id integer not null auto_increment,
        cpf varchar(255),
        datanasc date,
        nome varchar(255),
        sexo varchar(255),
        status bit,
        telefone varchar(255),
        tenant_id integer,
        primary key (id)
    ) engine=InnoDB

    create table patrimonio (
       id integer not null auto_increment,
        dataaquisicao datetime(6),
        descricao varchar(255),
        status bit,
        valor decimal(19,2),
        empresa_id integer,
        tenant_id integer,
        primary key (id)
    ) engine=InnoDB

    create table pedido (
       id integer not null auto_increment,
        cliente_id integer,
        primary key (id)
    ) engine=InnoDB

    create table permissao (
       id integer not null auto_increment,
        descricao varchar(255),
        classepermissao_id integer,
        primary key (id)
    ) engine=InnoDB

    create table produto (
       id integer not null auto_increment,
        descricao varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table tenant (
       id integer not null auto_increment,
        descricao varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table usuario (
       id integer not null auto_increment,
        email varchar(255),
        gtenantativo integer,
        login varchar(255),
        nome varchar(255),
        senha varchar(255),
        status bit,
        tenantativo integer,
        tenant_id integer,
        primary key (id)
    ) engine=InnoDB

    create table usuario_permissao (
       id_usuario integer not null,
        id_permissao integer not null
    ) engine=InnoDB

    create table usuario_empresa (
       empresapadrao bit,
        tenant_id integer,
        id_empresa integer not null,
        id_usuario integer not null,
        primary key (id_empresa, id_usuario)
    ) engine=InnoDB

    alter table empresa 
       add constraint UK_ow6ddx531a4abnwv6eah7h3k7 unique (cpfoucnpj)

    alter table atendimento 
       add constraint FK5otidx5k8xbj2jv2lp1ie76n6 
       foreign key (paciente_id) 
       references paciente (id)

    alter table atendimento 
       add constraint FKc0seka8tqrr44heqke6keeu6q 
       foreign key (tenant_id) 
       references tenant (id)

    alter table atendimento 
       add constraint FK27ltr33c0osxb67vm5lfb9iyn 
       foreign key (usuario_id) 
       references usuario (id)

    alter table atendimento_itens 
       add constraint FKbsoe795mbrhuh0dksoyuic8cp 
       foreign key (atendimento_id) 
       references atendimento (id)

    alter table atendimento_itens 
       add constraint FK2lha3fo11ygx12jxqax4lx4o9 
       foreign key (convenio_id) 
       references convenio (id)

    alter table atendimento_itens 
       add constraint FKqjd0ukyb8vw68ckn8lmpw0gxt 
       foreign key (exame_id) 
       references exame (id)

    alter table atendimento_itens 
       add constraint FKsyocduwl78ib9ielgc3p6cj0s 
       foreign key (tenant_id) 
       references tenant (id)

    alter table convenio 
       add constraint FKnqsri64tq01dwo7m038m5foew 
       foreign key (tenant_id) 
       references tenant (id)

    alter table exame 
       add constraint FKsnydpq45iblqhqp0ythw2sx6u 
       foreign key (tenant_id) 
       references tenant (id)

    alter table exame_convenio 
       add constraint FK6nwjkv4h9hrvb4tns91ti5aok 
       foreign key (convenio_id) 
       references convenio (id)

    alter table exame_convenio 
       add constraint FKokpsax6nbxbigkfqowjvjlkgh 
       foreign key (exame_id) 
       references exame (id)

    alter table exame_convenio 
       add constraint FKsklt3rqvf3l6d4knsvtcp1cfr 
       foreign key (tenant_id) 
       references tenant (id)

    alter table item 
       add constraint FK5v3x8uwb51v7l9mk41dy2jtku 
       foreign key (pedido_id) 
       references pedido (id)

    alter table item 
       add constraint FKoya2x5ip1q2t3s0936vgjiyx9 
       foreign key (produto_id) 
       references produto (id)

    alter table log_sistema 
       add constraint FKay7in37vxp5j1kuujmw1pn0hr 
       foreign key (atendimento_id) 
       references atendimento (id)

    alter table log_sistema 
       add constraint FKbocxu4cmmq90g78prbu8yp827 
       foreign key (convenio_id) 
       references convenio (id)

    alter table log_sistema 
       add constraint FKdtxgdxwvuxbb45995iirr9ase 
       foreign key (empresa_id) 
       references empresa (id)

    alter table log_sistema 
       add constraint FK5htiblkmlr85m59h9m88gvxrv 
       foreign key (exame_id) 
       references exame (id)

    alter table log_sistema 
       add constraint FKdq7fxmwvaej6beyirw56ixsps 
       foreign key (paciente_id) 
       references paciente (id)

    alter table log_sistema 
       add constraint FK42nl98mgjy9f3if8s5gi21987 
       foreign key (patrimonio_id) 
       references patrimonio (id)

    alter table log_sistema 
       add constraint FKlu2agpnij2euws8475xbxj5pf 
       foreign key (permissao_id) 
       references permissao (id)

    alter table log_sistema 
       add constraint FK70ecb2jvl5xk791a9ybtw1dsv 
       foreign key (tenant_id) 
       references tenant (id)

    alter table log_sistema 
       add constraint FKsltyfd8mjygt5amoo95i5p9iw 
       foreign key (usuario_id) 
       references usuario (id)

    alter table paciente 
       add constraint FKtby7g5oahoxfp3haifpf2e3y5 
       foreign key (tenant_id) 
       references tenant (id)

    alter table patrimonio 
       add constraint FKrda6lgn70gf7kh3r08g221plw 
       foreign key (empresa_id) 
       references empresa (id)

    alter table patrimonio 
       add constraint FKddwe32t0wd6ipfw0xyci94yxo 
       foreign key (tenant_id) 
       references tenant (id)

    alter table pedido 
       add constraint FK30s8j2ktpay6of18lbyqn3632 
       foreign key (cliente_id) 
       references cliente (id)

    alter table permissao 
       add constraint FKdiyasfskbnh46lmx2ywloji0d 
       foreign key (classepermissao_id) 
       references classepermissao (id)

    alter table usuario 
       add constraint FKa10giac3ef9545ra7eyhmn4q1 
       foreign key (tenant_id) 
       references tenant (id)

    alter table usuario_permissao 
       add constraint FKjvcxjnrmdhdv6eti5d7svm5xw 
       foreign key (id_permissao) 
       references permissao (id)

    alter table usuario_permissao 
       add constraint FKbo8hww1whbpxq8ancjokhnfds 
       foreign key (id_usuario) 
       references usuario (id)

    alter table usuario_empresa 
       add constraint FKkicrvlg5cwo9w2xi83xrq4ver 
       foreign key (id_empresa) 
       references empresa (id)

    alter table usuario_empresa 
       add constraint FK62hh8xjf2yjsncl8ti6vxjm7h 
       foreign key (id_usuario) 
       references usuario (id)
