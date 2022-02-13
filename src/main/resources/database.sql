DROP TABLE Produits CASCADE CONSTRAINTS;

CREATE TABLE Produits
(
    idProduit      NUMBER,
    nom            VARCHAR(16),
    prixUnitaireHT NUMBER,
    qteStock       NUMBER,
    CONSTRAINT pk_produits PRIMARY KEY (idProduit),
    CONSTRAINT un_produits_nom UNIQUE (nom)
);

CREATE SEQUENCE seq_produits;

CREATE OR REPLACE PROCEDURE insert_produit(p_nom PRODUITS.nom%TYPE, p_qteStock PRODUITS.QTESTOCK%TYPE, p_prixHT PRODUITS.PRIXUNITAIREHT%TYPE) IS
BEGIN
    INSERT INTO PRODUITS VALUES(seq_produits.nextval,p_nom,p_prixHT,p_qteStock);
END;

CALL insert_produit('twix',2,2);