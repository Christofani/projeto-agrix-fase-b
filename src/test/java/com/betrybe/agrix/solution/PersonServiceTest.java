package com.betrybe.agrix.solution;

import static org.mockito.Mockito.when;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.security.Role;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

/**
 * The type Person service test.
 */
@SpringBootTest
@DisplayName("Testando camada service da entidade Person.")
@ActiveProfiles("test")
public class PersonServiceTest {

  /**
   * The Person service.
   */
  @Autowired
  PersonService personService;

  /**
   * The Person repository.
   */
  @MockBean
  PersonRepository personRepository;

  /**
   * Test creation person.
   */
  @Test
  @DisplayName("Test - Criação de um novo Person")
  public void testCreationPerson() {
    // Configurar o objeto Person
    Person person = new Person();
    person.setUsername("Christofani");
    person.setPassword("Rodrigo123");
    person.setRole(Role.USER);

    when(personRepository.save(ArgumentMatchers.any(Person.class))).thenReturn(person);

    Person createdPerson = personService.create(person);

    Mockito.verify(personRepository).save(person);

    Assertions.assertNotNull(createdPerson);
    Assertions.assertEquals("Christofani", createdPerson.getUsername());
    Assertions.assertEquals("Rodrigo123", createdPerson.getPassword());
    Assertions.assertEquals(Role.USER, createdPerson.getRole());
  }

  /**
   * Test person by id.
   */
  @Test
  @DisplayName("Test - Buscando Person por Id")
  public void testPersonById() {
    Person person = new Person();
    person.setId(1L);
    person.setUsername("Christofani");
    person.setPassword("Rodrigo123");
    person.setRole(Role.USER);

    when(personRepository.findById(ArgumentMatchers.eq(1L)))
        .thenReturn(Optional.of(person));

    Person returnedPerson = personService.getPersonById(1L);

    Mockito.verify(personRepository).findById(ArgumentMatchers.eq(1L));

    Assertions.assertEquals(returnedPerson.getId(), person.getId());
    Assertions.assertEquals(returnedPerson.getUsername(), person.getUsername());
    Assertions.assertEquals(returnedPerson.getPassword(), person.getPassword());
    Assertions.assertEquals(Role.USER, returnedPerson.getRole());
  }

  /**
   * Test person by username.
   */
  @Test
  @DisplayName("Test - Buscando Person por Username")
  public void testPersonByUsername() {
    Person person = new Person();
    person.setId(1L);
    person.setUsername("Christofani");
    person.setPassword("Rodrigo123");
    person.setRole(Role.USER);

    when(personRepository.findByUsername(ArgumentMatchers.eq(person.getUsername())))
        .thenReturn(Optional.of(person));

    Person returnedPerson = personService.getPersonByUsername(person.getUsername());

    Mockito.verify(personRepository).findByUsername(ArgumentMatchers.eq(person.getUsername()));

    Assertions.assertEquals(returnedPerson.getId(), person.getId());
    Assertions.assertEquals(returnedPerson.getUsername(), person.getUsername());
    Assertions.assertEquals(returnedPerson.getPassword(), person.getPassword());
    Assertions.assertEquals(Role.USER, returnedPerson.getRole());
  }

  /**
   * Test person not found.
   */
  @Test
  @DisplayName("Test - Lança uma exceção caso não exista um Person buscando por id")
  public void testPersonNotFound() {
    when(personRepository.findById(ArgumentMatchers.any()))
        .thenReturn(Optional.empty());

    Assertions.assertThrows(PersonNotFoundException.class,
        () -> personService.getPersonById(5698L));

    Mockito.verify(personRepository).findById(ArgumentMatchers.eq(5698L));
  }

  /**
   * Test person not found by username.
   */
  @Test
  @DisplayName("Test - Lança uma exceção caso não exista um Person buscando por Username")
  public void testPersonNotFoundByUsername() {
    when(personRepository.findByUsername(ArgumentMatchers.any()))
        .thenReturn(Optional.empty());

    Assertions.assertThrows(PersonNotFoundException.class,
        () -> personService.getPersonByUsername("Joãozinho"));

    Mockito.verify(personRepository).findByUsername(ArgumentMatchers.eq("Joãozinho"));
  }
}
