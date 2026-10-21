package artisteja.domain;

import org.springframework.data.repository.CrudRepository;


public interface Application_userRepository extends CrudRepository<Application_user, Long> {
    Application_user findByUsername(String username);
}
