package ca.mcgill.ecse428.project.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.project.model.AppUser;


public interface AppUserRepository extends CrudRepository<AppUser, String> {
	AppUser findByEmail(String email);
}
