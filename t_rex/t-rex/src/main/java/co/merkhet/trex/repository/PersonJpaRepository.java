package co.merkhet.trex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import co.merkhet.trex.model.Person;

@Repository
@RepositoryRestResource
public interface PersonJpaRepository extends JpaRepository<Person, Long> {

}
