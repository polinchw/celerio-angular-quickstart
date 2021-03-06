/*
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Template pack-angular:src/main/java/domain/Entity.java.e.vm
 */
package com.bpe.monitor.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
@Table(name = "account")
public class Account implements Identifiable<Long>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(Account.class.getName());

    // Raw attributes
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String role;

    private List<Device> devices;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name="account_fk")
    public List<Device> getDevices() {
        if(devices == null) {
            devices = new ArrayList<Device>();
        }
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String entityClassName() {
        return Account.class.getSimpleName();
    }

    // -------------------------------
    // Role names support
    // -------------------------------

    /**
     * Default implementation returns hard coded granted authorities for this account (i.e. "ROLE_USER" and "ROLE_ADMIN").
     * TODO: You should override this method to provide your own custom authorities using your own logic.
     * Or you can follow Celerio Account Table convention. Please refer to Celerio Documentation.
     */
    @Transient
    public List<String> getRoleNames() {
        List<String> roleNames = new ArrayList<String>();
//        if ("user".equalsIgnoreCase(getEmail())) {
//            roleNames.add("ROLE_USER");
//        } else if ("admin".equalsIgnoreCase(getEmail())) {
//            roleNames.add("ROLE_USER");
//            roleNames.add("ROLE_ADMIN");
//        }
        roleNames.add("ROLE_"+role);

        log.warning("Returning hard coded role names. TODO: get the real role names");
        return roleNames;
    }
    // -- [id] ------------------------

    @Override
    @Column(name = "id", precision = 19)
    @GeneratedValue(strategy = IDENTITY)
    @Id
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Account id(Long id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    // -- [email] ------------------------

    @Email
    @NotEmpty
    @Size(max = 255)
    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Account email(String email) {
        setEmail(email);
        return this;
    }
    // -- [firstName] ------------------------

    @NotEmpty
    @Size(max = 255)
    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Account firstName(String firstName) {
        setFirstName(firstName);
        return this;
    }
    // -- [lastName] ------------------------

    @NotEmpty
    @Size(max = 255)
    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Account lastName(String lastName) {
        setLastName(lastName);
        return this;
    }
    // -- [password] ------------------------

    @NotEmpty
    @Size(max = 255)
    @Column(name = "\"password\"", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account password(String password) {
        setPassword(password);
        return this;
    }

    @Size(max = 255)
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Apply the default values.
     */
    public Account withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof Account && hashCode() == other.hashCode());
    }

    private volatile int previousHashCode = 0;

    @Override
    public int hashCode() {
        int hashCode = Objects.hashCode(getEmail());

        if (previousHashCode != 0 && previousHashCode != hashCode) {
            log.warning("DEVELOPER: hashCode has changed!." //
                    + "If you encounter this message you should take the time to carefuly review equals/hashCode for: " //
                    + getClass().getCanonicalName());
        }

        previousHashCode = hashCode;
        return hashCode;
    }

    /**
     * Construct a readable string representation for this Account instance.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("id", getId()) //
                .add("email", getEmail()) //
                .add("firstName", getFirstName()) //
                .add("lastName", getLastName()) //
                .add("password", getPassword()) //
                .toString();
    }
}