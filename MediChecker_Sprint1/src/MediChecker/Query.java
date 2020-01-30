package MediChecker;

public class Query {
	String definition = "";
	int providerID = 0;
	String providerName = "";
	String providerAddress = "";
	String providerCity = "";
	String providerState = "";
	String providerZip = "";
	String hospitalReferral = "";
	float avgCoveredCharges = 0;
	float avgTotalPayments = 0;
	float avgMedicarePayments = 0;

	
	public Query(String definition, int providerID, String providerName, String providerAddress, String providerCity, String providerState, String providerZip, String hospitalReferral, float avgCoveredCharges, float avgTotalPayments, float avgMedicarePayments)
	{
		this.definition = definition;
		this.providerID = providerID;
		this.providerName = providerName;
		this.providerAddress = providerAddress;
		this.providerCity = providerCity;
		this.providerState = providerState;
		this.providerZip = providerZip;
		this.hospitalReferral = hospitalReferral;
		this.avgCoveredCharges = avgCoveredCharges;
		this.avgTotalPayments = avgTotalPayments;
		this.avgMedicarePayments = avgMedicarePayments;
	}
	
	public Query(String providerName)
	{
		this.providerName = providerName;
		this.definition = "";
		this.providerID = 0;
		this.providerAddress = "";
		this.providerCity = "";
		this.providerState = "";
		this.providerZip = "";
		this.hospitalReferral = "";
		this.avgCoveredCharges = 0;
		this.avgTotalPayments = 0;
		this.avgMedicarePayments = 0;

	}


	public String getDefinition() {
		return definition;
	}


	public void setDefinition(String definition) {
		this.definition = definition;
	}


	public int getProviderID() {
		return providerID;
	}


	public void setProviderID(int providerID) {
		this.providerID = providerID;
	}


	public String getProviderName() {
		return providerName;
	}


	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}


	public String getProviderAddress() {
		return providerAddress;
	}


	public void setProviderAddress(String providerAddress) {
		this.providerAddress = providerAddress;
	}


	public String getProviderCity() {
		return providerCity;
	}


	public void setProviderCity(String providerCity) {
		this.providerCity = providerCity;
	}


	public String getProviderState() {
		return providerState;
	}


	public void setProviderState(String providerState) {
		this.providerState = providerState;
	}


	public String getProviderZip() {
		return providerZip;
	}


	public void setProviderZip(String providerZip) {
		this.providerZip = providerZip;
	}


	public String getHospitalReferral() {
		return hospitalReferral;
	}


	public void setHospitalReferral(String hospitalReferral) {
		this.hospitalReferral = hospitalReferral;
	}


	public float getAvgCoveredCharges() {
		return avgCoveredCharges;
	}


	public void setAvgCoveredCharges(float avgCoveredCharges) {
		this.avgCoveredCharges = avgCoveredCharges;
	}


	public float getAvgTotalPayments() {
		return avgTotalPayments;
	}


	public void setAvgTotalPayments(float avgTotalPayments) {
		this.avgTotalPayments = avgTotalPayments;
	}


	public float getAvgMedicarePayments() {
		return avgMedicarePayments;
	}


	public void setAvgMedicarePayments(float avgMedicarePayments) {
		this.avgMedicarePayments = avgMedicarePayments;
	}
	
	
	
}