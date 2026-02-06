package com.wellsfargo.counceler.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long portfolioId;

    // One-to-one relationship with Client
    @OneToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    // One-to-many relationship with Security
    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Security> securities = new ArrayList<>();

    // Default constructor required by JPA
    public Portfolio() {}

    // Constructor without ID
    public Portfolio(Client client) {
        this.client = client;
    }

    // Getters and setters
    public Long getPortfolioId() {
        return portfolioId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Security> getSecurities() {
        return securities;
    }

    public void setSecurities(List<Security> securities) {
        this.securities = securities;
    }

    // Helper methods to manage bidirectional relationship
    public void addSecurity(Security security) {
        if (!securities.contains(security)) {
            securities.add(security);
            security.setPortfolio(this);
        }
    }

    public void removeSecurity(Security security) {
        if (securities.contains(security)) {
            securities.remove(security);
            security.setPortfolio(null);
        }
    }
}
