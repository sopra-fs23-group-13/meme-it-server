package ch.uzh.ifi.hase.soprafs23.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Internal User Representation
 * This class composes the internal representation of the user and defines how
 * the user is stored in the database.
 * Every variable will be mapped into a database field with the @Column
 * annotation
 * - nullable = false -> this cannot be left empty
 * - unique = true -> this value must be unqiue across the database -> composes
 * the primary key
 */
@Embeddable
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  // ! this caused the problem we need to find another way to set the id for users
  // ! Probably storng users in a seperate table and using one to many
  // relationship
  // @Id
  // @GeneratedValue
  // private Long id;

  @Column(nullable = false)
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // public Long getId() {
  // return id;
  // }

  // public void setId(Long id) {
  // this.id = id;
  // }

  // @Override
  // public boolean equals(Object o) {
  // if (this == o)
  // return true;
  // if (o == null || getClass() != o.getClass())
  // return false;
  // User user = (User) o;
  // return Objects.equals(id, user.id) && Objects.equals(name, user.name);
  // }

  // @Override
  // public int hashCode() {
  // return Objects.hash(id, name);
  // }
}
