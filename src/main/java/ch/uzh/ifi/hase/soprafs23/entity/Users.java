package ch.uzh.ifi.hase.soprafs23.entity;

import java.util.List;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;

    // @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval =
    // true)
    // @Column(nullable = false)
    @ElementCollection
    private List<User> users;

    public Users() {
        this.users = List.of(new User[] {});
    }

    // public Long getId() {
    // return id;
    // }

    // public void setId(Long id) {
    // this.id = id;
    // }

    public List getUsers() {
        return users;
    }

    public User getUserByName(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean contains(User user) {
        for (User u : this.users) {
            if (u.equals(user)) {
                return true;
            }
        }
        return false;
    }


}
