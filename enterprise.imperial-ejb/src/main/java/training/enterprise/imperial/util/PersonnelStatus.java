package training.enterprise.imperial.util;

/**
 * Enumeration to showcase the various statuses that can pertain to the personnel.
 * @author Jonathan
 * @version 1.0
 */
public enum PersonnelStatus {
	/**
	 * Unit has passed away
	 */
	DECEASED("Deceased"),
	/**
	 * Unit is sent to a specific location
	 */
	DEPLOYED("Deployed"),
	/**
	 * Unit is mobile but not on a specific mission
	 */
	IN_TRANSIT("In Transit"),
	/**
	 * Unit is actively in a campaign
	 */
	IN_BATTLE("In Battle"),
	/**
	 * Unit is in a recovery status from wouds
	 */
	RECOVERY("Recovery");
	
	private String desc;
	
	private PersonnelStatus(String desc) {
		this.desc = desc;
	}
	
	public String toString() {
		return this.desc;
	}
}
