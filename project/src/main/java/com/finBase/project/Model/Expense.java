package com.finBase.project.Model;

import jakarta.persistence.*;
import jdk.jfr.Category;

//@Entity
//@Table(name="expenses")
//public class Expense {
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id; // This will store the user ID
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;
//
//    public Integer getBudget() {
//        return budget;
//    }
//
//    public void setBudget(Integer budget) {
//        this.budget = budget;
//    }
//
//    public Integer getSpend() {
//        return spend;
//    }
//
//    public void setSpend(Integer spend) {
//        this.spend = spend;
//    }
//
//    public Integer getMargin() {
//        return margin;
//    }
//
//    public void setMargin(Integer margin) {
//        this.margin = margin;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    private String  email;
//    private Integer spend;
//    private Integer budget;
//    private Integer margin;
//
//    public Integer getVersion() {
//        return version;
//    }
//
//    public void setVersion(Integer version) {
//        this.version = version;
//    }
//
//    @Version
//    private Integer version;
//}

@Entity
@Table(name="expenses")
public class Expense {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSpend() {
        return spend;
    }

    public void setSpend(Integer spend) {
        this.spend = spend;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Integer getMargin() {
        return margin;
    }

    public void setMargin(Integer margin) {
        this.margin = margin;
    }

    @Id
    private Long id;

    private String email;

    private Integer spend;
    private Integer budget;
    private Integer margin;

}

