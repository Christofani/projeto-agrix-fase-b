package com.betrybe.agrix.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.security.Role;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * The type Person service test.
 */
@SpringBootTest
@DisplayName("Testando camada service da entidade Person.")
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

    when(personRepository.save(any(Person.class))).thenReturn(person);

    Person createdPerson = personService.create(person);

    Mockito.verify(personRepository).save(person);

    assertNotNull(createdPerson);
    assertEquals("Christofani", createdPerson.getUsername());
    assertEquals("Rodrigo123", createdPerson.getPassword());
    assertEquals(Role.USER, createdPerson.getRole());
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

    Mockito.when(personRepository.findById(eq(1L)))
        .thenReturn(Optional.of(person));

    Person returnedPerson = personService.getPersonById(1L);

    Mockito.verify(personRepository).findById(eq(1L));

    assertEquals(returnedPerson.getId(), person.getId());
    assertEquals(returnedPerson.getUsername(), person.getUsername());
    assertEquals(returnedPerson.getPassword(), person.getPassword());
    assertEquals(Role.USER, returnedPerson.getRole());
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

    Mockito.when(personRepository.findByUsername(eq(person.getUsername())))
        .thenReturn(Optional.of(person));

    Person returnedPerson = personService.getPersonByUsername(person.getUsername());

    Mockito.verify(personRepository).findByUsername(eq(person.getUsername()));

    assertEquals(returnedPerson.getId(), person.getId());
    assertEquals(returnedPerson.getUsername(), person.getUsername());
    assertEquals(returnedPerson.getPassword(), person.getPassword());
    assertEquals(Role.USER, returnedPerson.getRole());
  }

  /**
   * Test person not found.
   */
  @Test
  @DisplayName("Test - Lança uma exceção caso não exista um Person buscando por id")
  public void testPersonNotFound() {
    Mockito.when(personRepository.findById(any()))
        .thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(5698L));

    Mockito.verify(personRepository).findById(eq(5698L));
  }

  /**
   * Test person not found by username.
   */
  @Test
  @DisplayName("Test - Lança uma exceção caso não exista um Person buscando por Username")
  public void testPersonNotFoundByUsername() {
    Mockito.when(personRepository.findByUsername(any()))
        .thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class,
        () -> personService.getPersonByUsername("Joãozinho"));

    Mockito.verify(personRepository).findByUsername(eq("Joãozinho"));
  }
}
