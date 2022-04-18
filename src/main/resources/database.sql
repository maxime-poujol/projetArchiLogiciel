DROP TABLE Produits CASCADE CONSTRAINTS;
DROP TABLE Catalogues CASCADE CONSTRAINTS;

CREATE TABLE Catalogues (
    idCatalogue NUMBER,
    nom         VARCHAR(32),
    CONSTRAINT pk_catalogues PRIMARY KEY (idCatalogue),
    CONSTRAINT un_catalogues_nom UNIQUE (nom)
);

CREATE SEQUENCE seq_catalogues;

CREATE PROCEDURE insert_catalogue(p_nom Catalogues.nom%TYPE) IS
BEGIN
    INSERT INTO Catalogues VALUES (seq_catalogues.nextval,p_nom);
END;


CREATE TABLE Produits
(
    idProduit      NUMBER,
    nom            VARCHAR(16),
    prixUnitaireHT NUMBER,
    qteStock       NUMBER,
    idCatalogue    NUMBER,
    CONSTRAINT pk_produits PRIMARY KEY (idProduit),
    CONSTRAINT un_produits_nom UNIQUE (nom),
    CONSTRAINT fk_produit_idCatalogue FOREIGN KEY (idCatalogue) REFERENCES Catalogues (idCatalogue)
);

CREATE SEQUENCE seq_produits;

CREATE OR REPLACE PROCEDURE insert_produit(p_nom Produits.nom%TYPE, p_qteStock Produits.qtestock%TYPE, p_prixHT Produits.prixunitaireht%TYPE, p_idCatalogue Produits.idCatalogue%TYPE) IS
BEGIN
    INSERT INTO Produits VALUES(seq_produits.nextval,p_nom,p_prixHT,p_qteStock,p_idCatalogue);
END;