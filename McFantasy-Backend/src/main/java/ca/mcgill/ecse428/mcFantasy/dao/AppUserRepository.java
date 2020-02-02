package ca.mcgill.ecse428.mcfantasy.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse428.mcfantasy.model.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, String> {

}
