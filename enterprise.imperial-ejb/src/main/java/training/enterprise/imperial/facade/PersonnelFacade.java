package training.enterprise.imperial.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import training.enterprise.imperial.model.Personnel;

/**
 * Facade class for accessing the database and manipulating the data as required per business logic and workflow.
 * 
 * The Stateless annotation is used to inform the server container that once a method has been completed for a request that it 
 * can return the managed bean to the pool of available beans for a later usage. It will not maintain any state of the data that 
 * it retrieves once out of the scope of the request.
 */
@Stateless
public class PersonnelFacade {
	
	/**
	 * The entity manager that is used to access the database.
	 * 
	 * The PersistenceContext annotation is used to inject into the EntityManager the correct datasource to access the database.
	 * By supplying the unitName value it will pull the correct connection source out of the persistence.xml file and inject 
	 * it into the EntityManager so that the methods can gain access to the database information.
	 */
	@PersistenceContext(unitName = "empire")
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public PersonnelFacade() { }

    /**
     * Obtain the single Personnel object based upon the unique ID field from the database.
     * @param id the unique ID value
     * @return the Personnel object that matches the ID value
     */
    public Personnel findById(long id) {
    	Personnel result = null;
    	//The named query here is found in the Personnel object in the NamedQueries annotation at the top of the class file.
    	TypedQuery<Personnel> query = em.createNamedQuery("Personnel.findById", Personnel.class);
    	result = query.getSingleResult();    	
    	return result;
    }
    
    /**
     * Obtain a list of all Personnel objects from the database. This will use the default order by and sort order option 
     * presented in the named query in the Personnel model class.
     * @return a list of all Personnel objects ordered by their name in ascending order
     */
    public List<Personnel> findAll() {
    	List<Personnel> results = new ArrayList<Personnel>();
    	//The named query here is found in the Personnel object in the NamedQueries annotation at the top of the class file.
    	TypedQuery<Personnel> query = em.createNamedQuery("Personnel.findAll", Personnel.class);
    	results.addAll(query.getResultList());
    	return results;
    }
    
    /**
     * TODO: Currently unimplemented and needs to be added in.
     * @param orderBy the value that we are going to order the results against
     * @param sortOrder the sort order to utilize, ASC and DESC are the only acceptable values
     * @return throws a new UnsupportedOperationException as it is not implemented yet
     */
    public List<Personnel> findAll(String orderBy, String sortOrder) {
    	throw new UnsupportedOperationException("Not implemented yet");
    }
}
