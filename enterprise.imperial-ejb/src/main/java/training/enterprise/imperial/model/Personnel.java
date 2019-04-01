package training.enterprise.imperial.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import training.enterprise.imperial.util.PersonnelStatus;

/**
 * Entity implementation class for Entity: Personnel.
 * 
 * The Entity annotation is used to denote that this object will have a tie to a database table (specified in the Table annotation
 * which is optional to have).
 * 
 * The Table annotation as stated is optional to have and really should only be used if the table name is different from the 
 * class name itself. This annotation is how you would be able to call a class something that is meaningful if the table it 
 * is tied to doesn't have quite as meaningful of a name.
 * 
 * The Data annotation is a lombok library annotation that prevents the need to have to make getters/setters for all of the 
 * variables in the class, as well as provide an overridden equals, hashcode, and toString method. While this is very helpful
 * it should still be considered to override the equals, hashcode, and toString as needed.
 * 
 * The NamedQueries annotation will be used to store a listing of all default JPA queries that will be used for this class. 
 * Others that need to be dynamically generated (for example if doing server side pagination or server side sorting) can be 
 * written in the facade equivalent class.
 */
@Entity
@Table(name = "personnel")
@Data
@NamedQueries({
	//Returns the Personnel object from the database that has the matching ID value passed in
	@NamedQuery(name="Personnel.findById", query="SELECT p FROM Personnel p WHERE p.id=:id"),
	//Returns a listing of all Personel objects from the database that are ordered by the name value in ascending order
	@NamedQuery(name="Personnel.findAll", query="SELECT p FROM Personnel p ORDER BY p.name ASC")
})
public class Personnel implements Serializable {
   
	/**
	 * Default serial version UID. Should increment as alterations are made in production.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Unique identifier that is assigned by the database.
	 * 
	 * The Id annotation is used to indicate that this field is the unique identifier for the class.
	 * 
	 * The GeneratedValue annotation is used in conjunction here with the Id annotation to indicate that the value placed 
	 * for this field is not set by the developer or user for a new object but is instead provided by the database upon 
	 * first persistence.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	/**
	 * The name of the person stored.
	 * 
	 * The Column annotation here is used to map a column name from the table to the attribute name. Again this is handy when 
	 * column names are not really meaningful but you want to make the code that links to it meaningful to a developer. Along 
	 * with the name, the Column annotation also allows for more column related descriptors to be added. Here we add in 
	 * qualifiers that state the column also cannot hold a null value and must also be unique from all other values in other 
	 * records.
	 */
	@Column(name = "pers_name", nullable = false, unique=true)
	private String name;
	
	/**
	 * The current status of the person. The Enumerated annotation will store the ordinal of the selected value
	 * in the database and then upon retrieval will auto assign the value based upon the ordinal.
	 * 
	 * The Enumerated annotation is used to tell JPA how to store the enumeration object into the database. What this will do is 
	 * take the ordinal value for the selected enumeration on the object and store that in the database. 
	 */
	@Column(name = "curr_status", nullable = false)
	@Enumerated
	private PersonnelStatus status;
}
