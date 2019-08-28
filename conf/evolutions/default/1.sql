# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table achat (
  id_achat                      bigserial not null,
  qte_achat                     integer,
  date_achat                    timestamptz,
  id_fournisseur                bigint not null,
  id_utilisateurs               bigint not null,
  id_produit                    bigint not null,
  constraint pk_achat primary key (id_achat)
);

create table cat_produit (
  id_cat_prod                   bigserial not null,
  libelle_cat                   varchar(255),
  desc_cat_prod                 varchar(255),
  constraint pk_cat_produit primary key (id_cat_prod)
);

create table client (
  id_client                     bigserial not null,
  nom                           varchar(255),
  prenom                        varchar(255),
  email                         varchar(255),
  adresse_livraison             varchar(255),
  telephone                     varchar(255),
  constraint pk_client primary key (id_client)
);

create table commande (
  id_cmde                       bigserial not null,
  date_cmde                     timestamptz,
  id_client                     bigint not null,
  constraint pk_commande primary key (id_cmde)
);

create table fournisseur (
  id_fournisseur                bigserial not null,
  raison_sociale                varchar(255),
  tel                           varchar(255),
  adresse_fournisseur           varchar(255),
  constraint pk_fournisseur primary key (id_fournisseur)
);

create table livraison (
  id_livraison                  bigserial not null,
  date_livraison                timestamptz,
  id_commande                   bigint not null,
  constraint uq_livraison_id_commande unique (id_commande),
  constraint pk_livraison primary key (id_livraison)
);

create table panier (
  id_panier                     bigserial not null,
  id_cmde                       bigint not null,
  id_produit                    bigint not null,
  qte_produit                   bigint,
  constraint uq_panier_id_cmde unique (id_cmde),
  constraint pk_panier primary key (id_panier)
);

create table produit (
  id_produit                    bigserial not null,
  designation                   varchar(255),
  est_perissable                boolean default false not null,
  prix_u                        float,
  id_catproduit                 bigint not null,
  constraint pk_produit primary key (id_produit)
);

create table sortie (
  id_sortie                     bigserial not null,
  raison_sortie                 varchar(255),
  qte_sortie                    integer,
  date_sortie                   timestamptz,
  id_produit                    bigint not null,
  constraint pk_sortie primary key (id_sortie)
);

create table stock (
  id_stock                      bigserial not null,
  qte_stock                     integer,
  date_peremption               timestamptz,
  est_valide                    boolean,
  id_achat                      bigint not null,
  constraint pk_stock primary key (id_stock)
);

create table utilisateurs (
  id_utilisateur                bigserial not null,
  nom                           varchar(255),
  prenom                        varchar(255),
  tel                           varchar(255),
  email                         varchar(255),
  pseudo                        varchar(255),
  mdp                           varchar(255),
  type_utilisateur              varchar(255),
  constraint pk_utilisateurs primary key (id_utilisateur)
);

create table vente (
  id_vente                      bigserial not null,
  qte_produit                   integer,
  date_vente                    timestamptz,
  prix_vente                    float,
  id_produit                    bigint not null,
  id_client                     bigint not null,
  constraint pk_vente primary key (id_vente)
);

create index ix_achat_id_fournisseur on achat (id_fournisseur);
alter table achat add constraint fk_achat_id_fournisseur foreign key (id_fournisseur) references fournisseur (id_fournisseur) on delete restrict on update restrict;

create index ix_achat_id_utilisateurs on achat (id_utilisateurs);
alter table achat add constraint fk_achat_id_utilisateurs foreign key (id_utilisateurs) references utilisateurs (id_utilisateur) on delete restrict on update restrict;

create index ix_achat_id_produit on achat (id_produit);
alter table achat add constraint fk_achat_id_produit foreign key (id_produit) references produit (id_produit) on delete restrict on update restrict;

create index ix_commande_id_client on commande (id_client);
alter table commande add constraint fk_commande_id_client foreign key (id_client) references client (id_client) on delete restrict on update restrict;

alter table livraison add constraint fk_livraison_id_commande foreign key (id_commande) references commande (id_cmde) on delete restrict on update restrict;

alter table panier add constraint fk_panier_id_cmde foreign key (id_cmde) references commande (id_cmde) on delete restrict on update restrict;

create index ix_panier_id_produit on panier (id_produit);
alter table panier add constraint fk_panier_id_produit foreign key (id_produit) references produit (id_produit) on delete restrict on update restrict;

create index ix_produit_id_catproduit on produit (id_catproduit);
alter table produit add constraint fk_produit_id_catproduit foreign key (id_catproduit) references cat_produit (id_cat_prod) on delete restrict on update restrict;

create index ix_sortie_id_produit on sortie (id_produit);
alter table sortie add constraint fk_sortie_id_produit foreign key (id_produit) references produit (id_produit) on delete restrict on update restrict;

create index ix_stock_id_achat on stock (id_achat);
alter table stock add constraint fk_stock_id_achat foreign key (id_achat) references achat (id_achat) on delete restrict on update restrict;

create index ix_vente_id_produit on vente (id_produit);
alter table vente add constraint fk_vente_id_produit foreign key (id_produit) references produit (id_produit) on delete restrict on update restrict;

create index ix_vente_id_client on vente (id_client);
alter table vente add constraint fk_vente_id_client foreign key (id_client) references client (id_client) on delete restrict on update restrict;


# --- !Downs

alter table if exists achat drop constraint if exists fk_achat_id_fournisseur;
drop index if exists ix_achat_id_fournisseur;

alter table if exists achat drop constraint if exists fk_achat_id_utilisateurs;
drop index if exists ix_achat_id_utilisateurs;

alter table if exists achat drop constraint if exists fk_achat_id_produit;
drop index if exists ix_achat_id_produit;

alter table if exists commande drop constraint if exists fk_commande_id_client;
drop index if exists ix_commande_id_client;

alter table if exists livraison drop constraint if exists fk_livraison_id_commande;

alter table if exists panier drop constraint if exists fk_panier_id_cmde;

alter table if exists panier drop constraint if exists fk_panier_id_produit;
drop index if exists ix_panier_id_produit;

alter table if exists produit drop constraint if exists fk_produit_id_catproduit;
drop index if exists ix_produit_id_catproduit;

alter table if exists sortie drop constraint if exists fk_sortie_id_produit;
drop index if exists ix_sortie_id_produit;

alter table if exists stock drop constraint if exists fk_stock_id_achat;
drop index if exists ix_stock_id_achat;

alter table if exists vente drop constraint if exists fk_vente_id_produit;
drop index if exists ix_vente_id_produit;

alter table if exists vente drop constraint if exists fk_vente_id_client;
drop index if exists ix_vente_id_client;

drop table if exists achat cascade;

drop table if exists cat_produit cascade;

drop table if exists client cascade;

drop table if exists commande cascade;

drop table if exists fournisseur cascade;

drop table if exists livraison cascade;

drop table if exists panier cascade;

drop table if exists produit cascade;

drop table if exists sortie cascade;

drop table if exists stock cascade;

drop table if exists utilisateurs cascade;

drop table if exists vente cascade;

