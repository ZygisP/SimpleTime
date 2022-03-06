package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Users type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Users", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Users implements Model {
  public static final QueryField ID = field("Users", "id");
  public static final QueryField USRNAME = field("Users", "usrname");
  public static final QueryField PASSW = field("Users", "passw");
  public static final QueryField EMAIL = field("Users", "email");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String usrname;
  private final @ModelField(targetType="String") String passw;
  private final @ModelField(targetType="String") String email;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getUsrname() {
      return usrname;
  }
  
  public String getPassw() {
      return passw;
  }
  
  public String getEmail() {
      return email;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Users(String id, String usrname, String passw, String email) {
    this.id = id;
    this.usrname = usrname;
    this.passw = passw;
    this.email = email;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Users users = (Users) obj;
      return ObjectsCompat.equals(getId(), users.getId()) &&
              ObjectsCompat.equals(getUsrname(), users.getUsrname()) &&
              ObjectsCompat.equals(getPassw(), users.getPassw()) &&
              ObjectsCompat.equals(getEmail(), users.getEmail()) &&
              ObjectsCompat.equals(getCreatedAt(), users.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), users.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUsrname())
      .append(getPassw())
      .append(getEmail())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Users {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("usrname=" + String.valueOf(getUsrname()) + ", ")
      .append("passw=" + String.valueOf(getPassw()) + ", ")
      .append("email=" + String.valueOf(getEmail()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Users justId(String id) {
    return new Users(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      usrname,
      passw,
      email);
  }
  public interface BuildStep {
    Users build();
    BuildStep id(String id);
    BuildStep usrname(String usrname);
    BuildStep passw(String passw);
    BuildStep email(String email);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String usrname;
    private String passw;
    private String email;
    @Override
     public Users build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Users(
          id,
          usrname,
          passw,
          email);
    }
    
    @Override
     public BuildStep usrname(String usrname) {
        this.usrname = usrname;
        return this;
    }
    
    @Override
     public BuildStep passw(String passw) {
        this.passw = passw;
        return this;
    }
    
    @Override
     public BuildStep email(String email) {
        this.email = email;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String usrname, String passw, String email) {
      super.id(id);
      super.usrname(usrname)
        .passw(passw)
        .email(email);
    }
    
    @Override
     public CopyOfBuilder usrname(String usrname) {
      return (CopyOfBuilder) super.usrname(usrname);
    }
    
    @Override
     public CopyOfBuilder passw(String passw) {
      return (CopyOfBuilder) super.passw(passw);
    }
    
    @Override
     public CopyOfBuilder email(String email) {
      return (CopyOfBuilder) super.email(email);
    }
  }
  
}
