USE MedicalProviderCharges;

DROP TABLE IF EXISTS `MedicalProviderCharges`;

CREATE TABLE `MedicalProviderCharges` (
    `UniqueID` int(255) NOT NULL AUTO_INCREMENT,
    `Definition` char(255) NOT NULL,
    `ProviderID` char(6) NOT NULL,
    `ProviderName` char(255) NOT NULL, 
    `ProviderAddress` char(255) NOT NULL, 
    `ProviderCity` char(255) NOT NULL, 
    `ProviderState` char(2) NOT NULL, 
    `ProviderZipCode` int(5) NOT NULL, 
    `HospitalReferral` char(255) NOT NULL,
    `AverageCoveredCharges` decimal(20,2) DEFAULT NULL, 
    `AverageTotalPayments` decimal(20,2) NOT NULL,
    `AverageCMedicarePayments` decimal(20,2) NOT NULL, 
    PRIMARY KEY (`UniqueID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
