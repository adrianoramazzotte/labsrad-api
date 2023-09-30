 create table tenant (
       id integer not null auto_increment,
        descricao varchar(255),
        primary key (id)
    ) engine=InnoDB default charset=utf8MB4; 
	
 create table paciente (
        id int not null auto_increment,
        nome varchar(100),
		datanasc date,
        sexo varchar(10),
		telefone  varchar(16),
		cpf varchar(15),
		tenant_id integer,
		status tinyint not null,
        primary key (id)
    ) engine=InnoDB default charset=utf8MB4; 
	
	
    create table usuario (
	    id int not null auto_increment,
		email varchar(100),
		nome varchar(100),
		login varchar(100),
		senha varchar(100),
        tenant_id integer,
        tenantativo int,
        gtenantativo int,
		status tinyint not null,
		gtenant_id int,
       	primary key (id)
    ) engine=InnoDB default charset=utf8MB4;
   
	
	create table log_sistema (
        id int not null auto_increment,
        comando varchar(5000),
		datagravacao datetime(6),
		email_usuario varchar(50),
		atendimento_id int,
		exame_id int,
		empresa_id integer,
		convenio_id int,
		paciente_id int,
		permissao_id int,
        tenant_id integer,		
		usuario_id int,
		patrimonio_id integer,
		status tinyint not null,
        primary key (id)
    ) engine=InnoDB default charset=utf8MB4;	
	
	
	create table permissao(
        id integer not null auto_increment,
        descricao varchar(100),
        classepermissao_id int,
		primary key (id)
    ) engine=InnoDB default charset=utf8MB4;
	
	create table usuario_permissao (
		id_usuario int,
		id_permissao int,
		primary key (id_usuario, id_permissao)	
	)engine=InnoDB default charset=utf8MB4;
	
	create table atendimento (
        id int not null auto_increment,
        id_ficha integer,
		status tinyint not null,
		formapagamento varchar(100),
		paciente_id int,
		desconto decimal(10,2),
		valortotal decimal(10,2),		
		datalancamento datetime(6),
		tenant_id integer,
		usuario_id int,		
        primary key (id)
    ) engine=InnoDB default charset=utf8MB4;
		
   create table atendimento_itens (
        exame_id integer not null,
        codigo integer not null,
        convenio_id integer not null,
        atendimento_id integer not null,
        data_atendimento date,
        status tinyint not null,
        preco decimal(10,2),
		tenant_id integer,
        desconto decimal(10,2),
        total decimal(10,2),
        acesso Integer,
        primary key (exame_id, atendimento_id, convenio_id, codigo)
    ) engine=InnoDB default charset=utf8MB4;
	   
	   	
   create table exame (
        id int not null auto_increment,
        descricao varchar(100),
		tenant_id integer,
       	status tinyint not null,
        primary key (id)
    ) engine=InnoDB default charset=utf8MB4;
    
   create table convenio (
	    id int not null auto_increment,
		descricao varchar(100),
		tenant_id integer,
		status tinyint not null,
       	primary key (id)
    ) engine=InnoDB default charset=utf8MB4;
	
	create table exame_convenio (
        exame_id integer not null,
        convenio_id integer not null,
        status tinyint not null,
		tenant_id integer,
        preco decimal(10,2),
        primary key (exame_id, convenio_id)
    ) engine=InnoDB default charset=utf8MB4;
    
     create table classepermissao (
        id int not null auto_increment,
        nome varchar(100),
        tenant_id integer,
  		permission varchar(100),
        primary key (id)
    ) engine=InnoDB default charset=utf8MB4;


     	
	alter table permissao
       add constraint FK_permissaoClasse
       foreign key (classepermissao_id) 
       references classepermissao(id);
    
    
	alter table atendimento 
       add constraint FK_usuarioAtendimento
       foreign key (usuario_id) 
       references usuario(id);

       
    alter table usuario_permissao 
       add constraint FK_permissaoUsuPermissao
       foreign key (id_permissao) 
       references permissao(id);
	       
    alter table usuario 
       add constraint UK_usuarioEmail unique (email);   
	
    alter table atendimento 
       add constraint FK_pacienteAtendimento
       foreign key (paciente_id) 
       references paciente(id);
     	
	alter table atendimento_itens
       add constraint FK_atendientoitensConvenio
       foreign key (convenio_id) 
       references convenio(id);
       
    alter table usuario_permissao 
       add constraint FK_usuarioUsuPermissao
       foreign key (id_usuario) 
       references usuario(id);
       
       
    alter table exame_convenio 
       add constraint FK_exameConExame
       foreign key (exame_id) 
       references exame (id);   

    alter table exame_convenio 
       add constraint FK_exameConConvenio
       foreign key (convenio_id) 
       references convenio (id);
       
       
    alter table atendimento_itens
       add constraint FK_atendientoitensAtendimento
       foreign key (atendimento_id) 
       references atendimento(id);
       
              
    alter table atendimento_itens
       add constraint FK_atendientoitensExame
       foreign key (exame_id) 
       references exame(id);
       
    create table empresa (
        id int not null auto_increment,
        cidade varchar(100),
        razaosocial varchar(100),
        cpfoucnpj varchar(100),
        naturezapessoa varchar(100),
        uf varchar(100),
        valor decimal(10,2),
        cep varchar(50),
        logradouro varchar(255),
        numero varchar(10),
        complemento varchar(255),
        bairro varchar(255),
        nomecontato varchar(255),
        telefone varchar(255),
        whats varchar(255),
        email varchar(255),
		tenant_id integer,
		status tinyint not null,
		editar tinyint not null,
        primary key (id)
    ) engine=InnoDB default charset=utf8MB4; 
    
    create table usuario_empresa(
       id_usuario integer not null,
       id_empresa integer not null,
       empresapadrao tinyint,
       tenant_id integer,
       primary key (id_usuario, id_empresa)
    ) engine=InnoDB default charset=utf8MB4;

  create table config(
      idconfig integer not null auto_increment,
      version varchar(255),
      primary key (idconfig)
    ) engine=InnoDB default charset=utf8MB4;
 create table patrimonio (
        id int not null auto_increment,
        descricao varchar(100),
        dataaquisicao datetime(6),
        valor decimal(10,2),
        tenant_id integer,
        empresa_id integer,
        status tinyint not null, 
        primary key (id)
    ) engine=InnoDB default charset=utf8MB4; 
	